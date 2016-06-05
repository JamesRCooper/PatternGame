package com.cooper.misc;

import java.util.ArrayDeque;
import java.util.Queue;

public class TestThread extends Thread {

    private Queue<String> inQueue = new ArrayDeque<>(2);

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {

                if (inQueue.isEmpty()) {
                    System.out.println("queue is empty");
                } else {
                    System.out.println("Queue says: " + inQueue.remove());
                }
                Thread.sleep(1000);
            }
        } catch (InterruptedException iEx) {
            System.out.println("interrupted");
        }
    }

    public void print() {
         System.out.println("action from inside the queue");
    }

    public void addToQueue(String value) {
        inQueue.add(value);
    }
}
