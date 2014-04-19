package netcode;

import java.net.*;
import java.io.*;
import java.util.*;

public class SlaveNodeWorker extends Thread
{
    private Socket serverSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SlaveNodeWorker(Socket serverSocket) 
    {
        this.serverSocket = serverSocket;
    }

    public void run()
    {
        System.out.println("ClientNodeWorker starting\n");
        try{

            System.out.println(serverSocket);
            out = new ObjectOutputStream(serverSocket.getOutputStream());
            in = new ObjectInputStream(serverSocket.getInputStream());
            out = new ObjectOutputStream(serverSocket.getOutputStream());
            while(true)
            {
                
                System.out.println("Slaves bitches");
                System.out.println("i am a slave bitches");
                
                //for (Thread thread : threads) {
                    //System.out.println(thread);
                //}
                //Object rec = in.readObject();
                //if (rec instanceof DeltaMessage)	{
                    //System.out.println((DeltaMessage) rec);
                //}

            
    
            }		
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
