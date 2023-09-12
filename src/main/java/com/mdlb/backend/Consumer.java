package com.mdlb.backend;

import java.util.Random;

public class Consumer extends Thread {

  private final DataQueue dataQueue;

  public Consumer(DataQueue dataQueue, String name) {
    super(name);
    this.dataQueue = dataQueue;
  }

  @Override
  public void run() {
    consume();
  }

  public void consume() {
    while (dataQueue.runFlag) {
      while (dataQueue.isEmpty() && dataQueue.runFlag) {
        try {
          dataQueue.waitOnEmpty();
        } catch (InterruptedException e) {
          e.printStackTrace();
          break;
        }
      }
      if (!dataQueue.runFlag) {
        break;
      }
      Message message = dataQueue.remove();
      dataQueue.notifyAllForFull();
      useMessage(message);
    }
  }

  public void useMessage(Message message) {
    // Create a Random object
    Random random = new Random();
    System.out.println(
        "                                                  " + this.getName() + ": Elemento removido. Indice: "
            + message.id);
    // Generate a random number between 1000 and 5000
    int randomNumber = random.nextInt(4001) + 1000;
    System.out
        .println(
            "                                                  " + this.getName() + ": Consumiendo indice " + message.id
                + ". ETA: " + (randomNumber / 1000) + " segundos");
    try {
      Thread.sleep(randomNumber);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void safeStop() {
    dataQueue.runFlag = false;
    dataQueue.notifyAllForEmpty();
  }

}
