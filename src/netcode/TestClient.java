package netcode;

import java.net.*;
import java.io.*;

/**
 * @authors Stephen Pardue, Ian Stainbrook, and James Ruiz
 * @date March 19, 2014
 */

public class TestClient
{
   public static void main(String [] args)
   {
      String serverName = args[0];
      int port = Integer.parseInt(args[1]);
		boolean isMaster = false;
		SocketAddress masterAddress = null;

      try
      {
         System.out.println("Connecting to " + serverName
                             + " on port " + port);
         Socket clientSocket = new Socket(serverName, port);
         System.out.println("Just connected to "
                      + clientSocket.getRemoteSocketAddress());

         ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

			System.out.println("Asking to become master");
         out.writeObject(new BootstrapMessage());

         ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
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
				System.out.println("Not accepted as master. Master address is " + masterAddress);
			}

			while(true) { }

			//in.close();
			//out.close();
         //clientSocket.close();

      }catch(Exception e)
      {
         e.printStackTrace();
      }
   }
}
