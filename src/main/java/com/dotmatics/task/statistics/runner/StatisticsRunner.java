package com.dotmatics.task.statistics.runner;

import com.dotmatics.task.statistics.Statistics;

import java.util.TimerTask;

// Statistics runner task, runs evry 30 secs prints out
public class StatisticsRunner extends TimerTask {
    private Statistics stat;

    public StatisticsRunner(Statistics stat) {
        this.stat = stat;
    }

    @Override
    public void run() {
        System.out.print("Operations: ");
        System.out.println(this.stat.getNumberOfOperations());
        System.out.print("Clients: ");
        System.out.println(this.stat.getNumberOfClients());
    }
}
