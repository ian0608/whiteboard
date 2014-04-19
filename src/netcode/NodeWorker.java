package netcode;

import java.net.*;
import java.io.*;
import java.util.*;

abstract class NodeWorker extends Thread {

    protected Socket socket;
    protected ObjectInputStream in;
    protected ObjectOutputStream out;
    protected Queue<Object> send; 
    protected Queue<Object> receive;

    public NodeWorker(Socket socket){
        this.socket = socket;
        send = new LinkedList<Object>();
        receive = new LinkedList<Object>();
    }

    public void send(DeltaMessage dmsg){
        send.add(dmsg);
    }

    public void send(Line line){
        send.add(line);
    }

    public boolean hasLine() {
        return receive.peek() instanceof Line;
    }

    public boolean hasDeltaMessage() {
        return receive.peek() instanceof DeltaMessage;
    }

    public Line getLine() {
        if (hasLine()){
            return (Line) receive.remove();
        } else {
            return null;
        }
    }

    public DeltaMessage getDeltaMessage() {
        if (hasDeltaMessage()){
            return (DeltaMessage) receive.remove();
        } else {
            return null;
        }
    }
}
