package netcode;

import java.net.*;
import java.io.*;

/**
 * @authors Stephen Pardue, Ian Stainbrook, and James Ruiz
 * @date March 19, 2014
 */

public class Node
{

	private String serverName;
	private int serverPort;
	private boolean isMaster;
	private SocketAddress masterAddress;

	public Node(String serverName, int serverPort)
	{
		this.serverName = serverName;
		this.serverPort = serverPort;
		isMaster = false;
		masterAddress = null;
	}

	public boolean bootstrap(boolean wantsMaster)
	{
		try
      {
         System.out.println("Connecting to " + serverName
                             + " on port " + serverPort);
         Socket clientSocket = new Socket(serverName, serverPort);
         System.out.println("Just connected to "
                      + clientSocket.getRemoteSocketAddress());

         ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
			
			if(wantsMaster)
			{
				System.out.println("Asking to become master");
         	out.writeObject(new BootstrapMessage());

         	
         	Object rec = in.readObject();
				if (rec instanceof BootstrapMessage)
				{
					isMaster = true;
					System.out.println("Accepted as master");
				}
				else if (rec instanceof JoinMessage)
				{
					isMaster = false;
					masterAddress = ((JoinMessage)rec).getPayload();
					if (masterAddress == null)	//should never happen here
					{
						System.out.println("No active master");
						return false;
					}
					System.out.println("Not accepted as master. Master address is " + masterAddress);

					//CONNECT TO MASTER
					System.out.println("(This is where we would attempt a connection to the master node");
				}
				else
				{
					System.out.println("Unexpected response from server");
					return false;
				}
			}
			else	//doesn't want to be master
			{
				isMaster = false;
				System.out.println("Asking to join session");
				out.writeObject(new JoinMessage());

         	Object rec = in.readObject();
				if (rec instanceof JoinMessage)
				{
					masterAddress = ((JoinMessage)rec).getPayload();
					if (masterAddress == null)
					{
						System.out.println("No active master");
						return false;
					}
					//CONNECT TO MASTER
					System.out.println("Master address is " + masterAddress);
					System.out.println("(This is where we would attempt a connection to the master node)");
				}
				else
				{
					System.out.println("Unexpected response from server");
					return false;
				}
			}

			//in.close();
			//out.close();

			return true;

         //clientSocket.close();

      }catch(Exception e)
      {
         e.printStackTrace();
			return false;
      }
	}

	public boolean getIsMaster()
	{
		return isMaster;
	}


   public static void main(String [] args)
   {
		//Command line args if node wants to be master:					[serverName] [serverPort] M
		//Command line args if node does not want to be master:		[serverName] [serverPort]

		if (args.length < 2)
		{
			System.out.println("Improper number of arguments");
			return;
		}
      String serverName = args[0];
		int serverPort = -1;
		try
		{
      	serverPort = Integer.parseInt(args[1]);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		if (serverPort == -1)
		{
			System.out.println("Invalid port number specified");
			return;
		}

		Node node = new Node(serverName, serverPort);
		if(!node.bootstrap(args.length == 3 && args[2].equals("M")))
		{
			System.out.println("Bootstrapping failed");
			return;
		}
		else
		{
			System.out.println("Bootstrapping succeeded");
			if (node.getIsMaster())
				System.out.println("This node IS master");
			else
				System.out.println("This node IS NOT master");
		}

		while(true) {}
	}

}
