package com.dotmatics.task.socket;

import com.dotmatics.task.command.resolver.CommandResolver;
import com.dotmatics.task.statistics.Statistics;

import java.io.*;
import java.net.Socket;

// Client socket logic, on server side
public class ClientSocket implements Runnable {
    private Socket clientSocket;
    private Statistics stat;
    private CommandResolver commandResolver;

    public ClientSocket(Socket clientSocket,  Statistics stat, String baseDir) {
        this.clientSocket = clientSocket;
        this.stat = stat;
        this.commandResolver = new CommandResolver(baseDir, stat);

    }

    @Override
    public void run() {
        try (InputStream input = clientSocket.getInputStream();
             OutputStream output = clientSocket.getOutputStream()) {

            stat.increaseNumberOfClients();
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String clientInput;

            while ((clientInput = in.readLine()) != null) {
                String commandResult = this.commandResolver.executeCommand(clientInput);
                output.write((commandResult + "\n").getBytes());
                output.write("<end>\n".getBytes());

                if (commandResult.equals("quit")) {
                    stat.decreaseNumberOfClients();
                    clientSocket.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
