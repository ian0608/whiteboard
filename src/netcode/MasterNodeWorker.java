package netcode;

import java.net.*;
import java.io.*;
import java.util.*;

public class MasterNodeWorker extends NodeWorker
{
    private List<Thread> threads;

    public MasterNodeWorker(Socket socket, List<Thread> threads)
    {
        super(socket);

        synchronized(this) {
            this.threads = threads;
        }

    }

    public void run()
    {
        System.out.println("What is up? MasterNodeWorker in the house\n");
        try{

            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
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
