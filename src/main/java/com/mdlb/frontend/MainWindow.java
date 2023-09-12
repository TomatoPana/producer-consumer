package com.mdlb.frontend;

import java.awt.Color;
// import java.awt.event.ItemEvent;
// import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
// import javax.swing.JToggleButton;

public class MainWindow extends JFrame /** implements ItemListener */
{
  // private JToggleButton button;

  /**
   * Creates the main window, specifying the required configurations
   * 
   * @param producers The number of graphical producers to be created
   * @param consumers The number of graphical consumers to be created
   * @param queueSize The size of the queue
   */
  public MainWindow(int producers, int consumers, int queueSize) {
    ProducerPanel producersPanel = new ProducerPanel();
    producersPanel.setNumberOfProducers(producers);
    add(producersPanel);

    JPanel consumersPanel = new JPanel();
    consumersPanel.setBounds(708, 16, 300, 520);
    consumersPanel.setBackground(new Color(100, 100, 100));

    add(consumersPanel);

    GridPanel queuePanel = new GridPanel();
    queuePanel.setBounds(16, 552, 992, 200);
    queuePanel.setBackground(new Color(100, 255, 100));

    add(queuePanel);

    setLayout(null);
    setSize(1024, 768);
    setResizable(false);
    setTitle("Producer Consumer");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);

  }

  // private void setJToggleButton() {
  // button = new JToggleButton("ON");
  // add(button);
  // }

  // private void setAction() {
  // button.addItemListener(this);
  // }

  // public void itemStateChanged(ItemEvent eve) {
  // if (button.isSelected())
  // button.setText("OFF");
  // else
  // button.setText("ON");
  // }
}
