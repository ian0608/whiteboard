package netcode;

import java.net.*;
import java.io.*;
import java.util.*;

public class MasterNodeWorker extends Thread
{
    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private List<Thread> threads;

    public MasterNodeWorker(Socket clientSocket, List<Thread> threads)
    {
        this.clientSocket = clientSocket;
        synchronized(this) {
            this.threads = threads;
        }

    }

    public void run()
    {
        System.out.println("What is up? MasterNodeWorker in the house\n");
        try{

            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            while(true)
            {
                if (in.available() > 0){
                    System.out.println("We can read shizzz...");
                } else { 
                    System.out.println("Clients connected: "+threads.size());
                }
            }		
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
