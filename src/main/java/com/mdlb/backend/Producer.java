package com.mdlb.backend;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Implementation of the Producer Class
 */
public class Producer implements Runnable {

  private final DataQueue dataQueue;
  final ReentrantLock lock = new ReentrantLock();

  public Producer(DataQueue dataQueue) {
    this.dataQueue = dataQueue;
  }

  @Override
  public void run() {
    produce();
  }

  public void produce() {
    while (dataQueue.runFlag) {
      try {
        lock.lock();
        try {
          while (dataQueue.isFull() && dataQueue.runFlag) {
            dataQueue.waitOnFull();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
          break;
        }
        if (!dataQueue.runFlag) {
          break;
        }
        Message message = generateMessage();
        dataQueue.add(message);
        dataQueue.notifyAllForEmpty();
      } finally {
        lock.unlock();
      }
    }
  }

  private Message generateMessage() {
    return new Message(1, 10);
  }

}
