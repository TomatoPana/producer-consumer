package com.mdlb;

import java.util.ArrayList;

import com.mdlb.backend.Consumer;
import com.mdlb.backend.DataQueue;
import com.mdlb.backend.Producer;
import com.mdlb.frontend.MainWindow;

/**
 * Entry point of the app
 */
public final class App {
    public static ArrayList<Producer> producers;
    public static ArrayList<Consumer> consumers;

    /**
     * Singleton dedicated to interoperate with the producers threads
     * 
     * @return All the producers created
     */
    public static ArrayList<Producer> getProducersRunners() {
        if (App.producers == null) {
            App.producers = new ArrayList<>();
        }
        return App.producers;
    }

    /**
     * Singleton dedicated to interoperate with the consumers threads
     * 
     * @return All the consumers created
     */
    public static ArrayList<Consumer> getConsumersRunners() {
        if (App.consumers == null) {
            App.consumers = new ArrayList<>();
        }
        return App.consumers;
    }

    public static void main(String[] args) throws Exception {
        final int MAX_QUEUE_CAPACITY = 10;
        DataQueue dataQueue = new DataQueue(MAX_QUEUE_CAPACITY);
        final int producerCount = 3;
        final int consumerCount = 3;
        getConsumersRunners();
        getProducersRunners();

        new MainWindow(producerCount, consumerCount, MAX_QUEUE_CAPACITY);

        for (int i = 0; i < producerCount; i++) {
            Producer producerThread = new Producer(dataQueue, "Producer " + i);
            producerThread.start();
            producers.add(i, producerThread);
        }

        for (int i = 0; i < consumerCount; i++) {
            Consumer consumerThread = new Consumer(dataQueue, "Consumer " + i);
            consumerThread.start();
            consumers.add(i, consumerThread);
        }

        Thread.sleep(120 * 1000);

        producers.forEach(localProducer -> {
            localProducer.safeStop();
        });

        consumers.forEach(localConsumer -> {
            localConsumer.safeStop();
        });
    }
}
