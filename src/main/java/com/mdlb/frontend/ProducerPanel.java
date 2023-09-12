package com.mdlb.frontend;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Define the producer panel interface to show all active producers
 */
public class ProducerPanel extends JPanel {

  private ArrayList<ProducerItem> items;

  public ProducerPanel() {
    setLayout(null);
    setBounds(16, 16, 300, 520);
    setBackground(new Color(255, 100, 100));
    items = new ArrayList<>();
  }

  /**
   * Get a desired number of producers and creates it
   * 
   * @param producers
   */
  public void setNumberOfProducers(int producers) {
    for (int i = 0; i < producers; i++) {
      ProducerItem producer = this.createProducerItem();
      producer.setLocation(16, (166 * i) + (16));
      this.items.add(i, producer);
      this.add(producer);
    }
  }

  /**
   * Create a producer graphical instance
   * 
   * @return
   */
  public ProducerItem createProducerItem() {
    return new ProducerItem();
  }
}
