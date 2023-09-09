package com.mdlb.backend;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Shared queue and related objects
 */
public class DataQueue {
  private final Queue<Message> queue = new LinkedList<>();
  private final int maxSize;
  private final Object FULL_QUEUE = new Object();
  private final Object EMPTY_QUEUE = new Object();
  public boolean runFlag = true;

  DataQueue(int maxSize) {
    this.maxSize = maxSize;
  }

  /**
   * Method called by the producer when the queue is full, waiting to be consumed
   */
  public void waitOnFull() throws InterruptedException, IllegalMonitorStateException {
    synchronized (FULL_QUEUE) {
      // Producers sleep here if the queue is full
      FULL_QUEUE.wait();
    }
  }

  /**
   * Method called by the consumer to notify to the producers to wake up,
   * indicating the queue is ready to consume data
   */
  public void notifyAllForFull() throws IllegalMonitorStateException {
    synchronized (FULL_QUEUE) {
      // Consumers wakes any of the producers sleeping in the FULL_QUEUE object
      FULL_QUEUE.notifyAll();
    }
  }

  /**
   * Method called by the consumers when que queue is empty, waiting items to be
   * produced
   */
  public void waitOnEmpty() throws InterruptedException, IllegalMonitorStateException {
    synchronized (EMPTY_QUEUE) {
      // Consumers sleep here if the queue is empty
      EMPTY_QUEUE.wait();
    }
  }

  /**
   * Method called by the producers to notify to the consumers to wake up,
   * indicating the queue has data ready to be consumed
   */
  public void notifyAllForEmpty() {
    synchronized (EMPTY_QUEUE) {
      // Producers wakes any of the consumers sleeping in the EMPTY_QUEUE object
      EMPTY_QUEUE.notifyAll();
    }
  }

  /**
   * Add a new data object by any producer at a time to the queue
   * 
   * @param message The data object produced
   */
  public void add(Message message) {
    synchronized (queue) {
      queue.add(message);
    }
  }

  /**
   * Retrieve and delete the data by any consumer at a time of the queue
   * 
   * @return The data object consumed
   */
  public Message remove() {
    synchronized (queue) {
      return queue.poll();
    }
  }

  public boolean isFull() {
    return queue.size() == this.maxSize;
  }
}
