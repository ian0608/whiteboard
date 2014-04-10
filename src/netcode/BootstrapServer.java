package netcode;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 * @authors Stephen Pardue, Ian Stainbrook, and James Ruiz
 * @date March 19, 2014
 */

public class BootstrapServer
{
   private ServerSocket serverSocket;
	private SocketAddress masterAddress;
	private ArrayList<SocketAddress> joinedAddresses;
   
   public BootstrapServer(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
      //serverSocket.setSoTimeout(20000);
		masterAddress = null;
		joinedAddresses = new ArrayList<SocketAddress>();
   }

	public void run()
	{
			while(true)
      	{
         	try
         	{
            	System.out.println("Waiting for client on port " +
            	serverSocket.getLocalPort() + "...");
            	Socket clientSocket = serverSocket.accept();
            	System.out.println("Just connected to "
            	      + clientSocket.getRemoteSocketAddress());

					Thread t = new WorkerThread(clientSocket);
					t.start();
				}
				catch(SocketTimeoutException s)
         	{
            	System.out.println("Socket timed out!");
            	break;
         	}
				catch(Exception e)
         	{
            	e.printStackTrace();
            	break;
         	}

			}
	}

	public class WorkerThread extends Thread
	{
				private Socket clientSocket;

				public WorkerThread(Socket clientSocket)
				{
					this.clientSocket = clientSocket;
					
				}

				public void run()
   			{
					try{
					ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
					ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

					while(true)
					{
						
						Object rec = in.readObject();
						if (rec instanceof BootstrapMessage)	//client wishes to be master
						{
							if (masterAddress != null)
							{
								System.out.println("Already have a master - relaying the master's address");
								out.writeObject(new JoinMessage(masterAddress));
							}
							else
							{
								masterAddress = clientSocket.getRemoteSocketAddress();
								System.out.println("telling node it's the master");
								out.writeObject(new BootstrapMessage());
							}
						}
						else if (rec instanceof JoinMessage) //client wishes to join existing session
						{
							out.writeObject(new JoinMessage(masterAddress));
						}
					
						//in.close();
						//out.close();
			
					}		
	
            		//clientSocket.close();
	         	}
					catch(SocketTimeoutException s)
         		{
            		System.out.println("Socket timed out!");
         		}
					catch(Exception e)
         		{
            		e.printStackTrace();
         		}

					//if socket closes, return (close thread)
				}


	      }

   
   public static void main(String [] args)
   {
      int port = Integer.parseInt(args[0]);
      try
      {
         BootstrapServer server = new BootstrapServer(port);
			server.run();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}
