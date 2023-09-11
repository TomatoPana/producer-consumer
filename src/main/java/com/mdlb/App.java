package com.mdlb;

import com.mdlb.backend.Consumer;
import com.mdlb.backend.DataQueue;
import com.mdlb.backend.Producer;
import com.mdlb.frontend.MainWindow;

/**
 * Entry point of the app
 */
public class App {

    public static void main(String[] args) throws Exception {
        // new MainWindow();
        final int MAX_QUEUE_CAPACITY = 10;
        DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);
        final int producerCount = 5;
        final int consumerCount = 5;

        Producer producer = new Producer(dataQueue);
        for (int i = 0; i < producerCount; i++) {
            Thread producerThread = new Thread(producer);
            producerThread.start();
        }

        Consumer consumer = new Consumer(dataQueue);
        for (int i = 0; i < consumerCount; i++) {
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();
        }

        Thread.sleep(120 * 1000);

        producer.stop();
        consumer.stop();
    }
}
