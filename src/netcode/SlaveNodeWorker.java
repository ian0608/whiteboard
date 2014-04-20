package netcode;

import java.net.*;
import java.io.*;
import java.util.*;

/**
Stephen Pardue
4-19-2014
  **/

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

            ObjectReadThread t = new ObjectReadThread();
            t.start();

            while(true)
            {
                if (send.peek() != null){
                    out.writeObject(send.poll());
                    System.out.println("Just sent something\n");
                }
            }		
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    private class ObjectReadThread extends Thread {
        public void run() {
            while (true) {
                try {
                    receive.add(in.readObject());
                    System.out.println("Got an object");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
