package com.dotmatics.task;

import com.dotmatics.task.socket.ClientSocket;
import com.dotmatics.task.statistics.Statistics;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Server logic, opening threads on accept
public class Server implements Runnable {
    private int          serverPort   = 9000;
    private ServerSocket serverSocket = null;
    private boolean      isStopped    = false;
    private Thread       runningThread= null;
    private Statistics stat;
    private String baseDir;

    public Server(int port, Statistics stat, String baseDir){
        this.serverPort = port;
        this.stat = stat;
        this.baseDir = baseDir;
    }

    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            new Thread(
                    new ClientSocket(
                            clientSocket, this.stat, this.baseDir)
            ).start();
        }
        System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.serverPort, e);
        }
    }
}
