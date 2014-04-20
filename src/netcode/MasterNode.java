package netcode;

import java.net.*;
import java.io.*;

public class MasterNode extends Thread
{
    private Socket clientSocket;

    public MasterNode(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
        
    }

    public void run()
    {
        try{
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
	    try {
		Thread.sleep(2000);
	    } catch(Exception e) {
		e.printStackTrace();
	    }
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
	    try {
		Thread.sleep(2000);
	    } catch(Exception e) {
		e.printStackTrace();
	    }
	    
            while(true)
            {
                
                Object rec = in.readObject();
                if (rec instanceof DeltaMessage)	{
                    System.out.println((DeltaMessage) rec);
                }

            
    
            }		
        }
        catch(SocketTimeoutException s) {
            System.out.println("Socket timed out!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
