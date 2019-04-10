package com.dotmatics.task;

// Starting point of application, console argument parsing logic
public class ClientServerStarter {
    public static void main(String[] args) {
        if(args.length != 3) {
            header();
            clientMessage();
            serverMessage();
        } else {
            if ("client".equals(args[0]) && args.length == 3) {
                ClientStarter clientStarter = new ClientStarter(args[1], Integer.parseInt(args[2]));
                clientStarter.start();
            } else if (("server".equals(args[0]) && args.length == 3)) {
                String basePath = args[2];
                if ("/".equals(basePath.substring(basePath.length() - 1)))
                    basePath = basePath.substring(0, basePath.length() - 1);
                ;
                ServerStarter serverStarter = new ServerStarter(Integer.parseInt(args[1]), basePath);
                serverStarter.start();
            } else {
                header();
                clientMessage();
                serverMessage();
            }
        }

    }

    private static void clientMessage() {
        System.out.println("To run client use command: ");
        System.out.println("java -jar ClientServerStarter.jar <server_IP> <port>");
    }

    private static void serverMessage() {
        System.out.println("To run client use command: ");
        System.out.println("java -jar ClientServerStarter.jar <port> <base_path>");
    }

    private static void header() {
        System.out.println("This is client server executor.");
    }
}
