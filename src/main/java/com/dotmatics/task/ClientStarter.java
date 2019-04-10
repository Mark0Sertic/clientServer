package com.dotmatics.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

// Client starter logic
public class ClientStarter {
    private String hostName ;
    private int portNumber ;

    public ClientStarter(String hostName, int portNumber) {
        this.hostName = hostName;
        this.portNumber = portNumber;
    }

    public void start()  {
        String command = "";
        Scanner sc = new Scanner(System.in);
        String serverResponse;

        try (Socket kkSocket = new Socket(hostName, portNumber);
             OutputStream out = kkSocket.getOutputStream();
             InputStream input = kkSocket.getInputStream())
        {

            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            while (!"quit".equals(command)) {
                System.out.print(">");
                command = sc.nextLine();
                out.write((command + "\n").getBytes());
                while ((serverResponse = in.readLine()) != null) {
                    if("<end>".equals(serverResponse)) break;
                    System.out.println(serverResponse);

                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
