package com.dotmatics.task;

import com.dotmatics.task.statistics.Statistics;
import com.dotmatics.task.statistics.runner.StatisticsRunner;

import java.util.Timer;
import java.util.concurrent.Semaphore;

// Server starting logic
public class ServerStarter {
    private int port;
    private String basePath;

    public ServerStarter(int port, String basePath) {
        this.port = port;
        this.basePath = basePath;
    }
    public void start() {
        Statistics stat = new Statistics();
        Server server = new Server(this.port, stat, this.basePath);
        new Thread(server).start();
        Timer timer = new Timer();
        Semaphore semaphore = new Semaphore(0);

        timer.schedule(new StatisticsRunner(stat), 0, 30000);

        Runtime.getRuntime().addShutdownHook(new Thread(){public void run(){
            server.stop();
            timer.cancel();
            semaphore.release();
            System.out.println("The server is shut down!");
        }});

        semaphore.tryAcquire();

    }
}
