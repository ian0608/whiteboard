package netcode;

import java.net.*;
import java.io.*;
import java.util.*;

public class SlaveNodeWorker extends NodeWorker 
{

    public SlaveNodeWorker(Socket socket){
        super(socket);
    }


    public void run()
    {
        System.out.println("ClientNodeWorker starting\n");
        try{

            System.out.println(socket);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            while(true)
            {
                
                System.out.println("Slaves bitches");
                System.out.println("i am a slave bitches");
                
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
