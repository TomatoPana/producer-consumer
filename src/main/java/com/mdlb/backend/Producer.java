package com.mdlb.backend;

import java.util.Random;

/**
 * Implementation of the Producer Thread Class
 */
public class Producer extends Thread {

  private final DataQueue dataQueue;
  private int index = 1;

  public Producer(DataQueue dataQueue, String name) {
    super(name);
    this.dataQueue = dataQueue;
  }

  @Override
  public void run() {
    produce();
  }

  public void produce() {
    while (dataQueue.runFlag) {
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
      try {
        while (dataQueue.isFull() && dataQueue.runFlag) {
          dataQueue.waitOnFull();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
        break;
      }
      int index = dataQueue.add(message);
      System.out.println(this.getName() + ": Elemento agregado. Indice: " + index);
      dataQueue.notifyAllForEmpty();
    }
  }

  private Message generateMessage() {
    // Create a Random object
    Random random = new Random();

    // Generate a random number between 1000 and 5000
    int randomNumber = random.nextInt(2001) + 1000;
    System.out.println(this.getName() + ": Produciendo. ETA: " + (randomNumber / 1000) + " segundos");
    try {
      Thread.sleep(randomNumber);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Message message = new Message(index, 10);
    return message;
  }

  public void safeStop() {
    dataQueue.runFlag = false;
    // The producer needs to wake up to terminate
    dataQueue.notifyAllForFull();
  }

}
