package netcode;
/**
Stephen Pardue
4-19-2014
  **/

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
        synchronized(this) {
            this.socket = socket;
        }
        send = new LinkedList<Object>();
        receive = new LinkedList<Object>();
    }

    public synchronized void send(Object o){
        getSendBuffer().add(o);
    }

    public Queue<Object> getReceiveBuffer() {
        return receive;
    }

    public Queue<Object> getSendBuffer() {
        return send;
    }

    public synchronized boolean hasLine() {
        return getReceiveBuffer().peek() instanceof Line;
    }

    public synchronized void addToReceiveBuffer(Object o) {
        getReceiveBuffer().add(o);
    }

    public synchronized boolean hasDeltaMessage() {
        return getReceiveBuffer().peek() instanceof DeltaMessage;
    }

    public synchronized Line getLine() {
        if (hasLine()){
            return (Line) getReceiveBuffer().remove();
        } else {
            return null;
        }
    }

    public synchronized DeltaMessage getDeltaMessage() {
        if (hasDeltaMessage()){
            return (DeltaMessage) getReceiveBuffer().remove();
        } else {
            return null;
        }
    }
}
