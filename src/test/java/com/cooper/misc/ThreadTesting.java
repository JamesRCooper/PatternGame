package com.cooper.misc;

import org.junit.Test;

public class ThreadTesting {

    @Test
    public void testThreadStuff() throws InterruptedException {

        TestThread t = new TestThread();
        System.out.println("Thread started");
        t.start();

        Thread.sleep(5000);

        t.print();
        t.addToQueue("outside the thread");

        Thread.sleep(10000);
    }

}
