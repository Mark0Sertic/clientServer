package com.dotmatics.task.statistics;

// Statistics class
public class Statistics {
    private int numberOfOperations = 0;
    private int numberOfClients = 0;

    public Statistics() {}

    public synchronized int getNumberOfOperations() {
        return this.numberOfOperations;
    }

    public synchronized  int getNumberOfClients() {
        return this.numberOfClients;
    }

    public synchronized  void increaseNumberOfOperations() {
        this.numberOfOperations++;
    }

    public synchronized void decreaseNumberOfOberations() {
        this.numberOfOperations--;
    }

    public synchronized void increaseNumberOfClients() { this.numberOfClients++; }

    public synchronized void decreaseNumberOfClients() {
        this.numberOfClients--;
    }
}
