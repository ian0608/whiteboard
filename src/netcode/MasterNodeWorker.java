package netcode;

import java.net.*;
import java.io.*;
import java.util.*;

/**
Stephen Pardue
4-19-2014
  **/

public class MasterNodeWorker extends NodeWorker
{
    private List<MasterNodeWorker> threads;
    private static Queue<Object> masterClientQueue = new LinkedList<Object>();

    

    public MasterNodeWorker(Socket socket, List<MasterNodeWorker> threads)
    {
        super(socket);

        synchronized(this) {
            this.threads = threads;
        }
    }



    public synchronized Queue<Object> getReceiveBuffer() {
        return masterClientQueue;
    }

    public synchronized void send(Object o){
        System.out.println("Sending Object...");
        for (MasterNodeWorker node : threads){
            node.getSendBuffer().add(o);
        }
    }

    public void run()
    {
        System.out.println("What is up? MasterNodeWorker in the house\n");
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            ObjectReadThread rt = new ObjectReadThread(this);
            rt.start();
            while(true){
                while (send.peek() != null){
                    System.out.println("Sending Objects");
                    Object toSend = send.poll();
                    out.writeObject(toSend);
                }
            }		
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    private class ObjectReadThread extends Thread {
        private NodeWorker master;

        public ObjectReadThread(NodeWorker master) {
            this.master = master;
        }

        public void run() {
            while (true) {
                try {
                    Object recv = in.readObject();
                    System.out.println("Forwarding object to other master threads");
                    for (NodeWorker thread : threads) {
                        if (thread != master){
                            thread.getSendBuffer().add(recv);
                        }
                    }
                    addToReceiveBuffer(recv);
                    System.out.println(getReceiveBuffer());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
