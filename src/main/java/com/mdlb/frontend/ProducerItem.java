package com.mdlb.frontend;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProducerItem extends JPanel {
  private JLabel state;

  public ProducerItem() {
    super(null, true);
    setSize(268, 150);
    setBackground(new Color(100, 255, 100));
    JLabel state = new JLabel("Sleeping");
    state.setHorizontalAlignment(JLabel.CENTER);
    state.setBounds(8, 8, 252, 32);
    state.setFont(new Font("Droid Sand", Font.BOLD, 16));
    add(state);
  }

  public void setSleepingState() {
    this.state.setText("Sleeping");
  }

  public void setProducingState() {
    this.state.setText("Producing");
  }

  public void setProducedState() {
    this.state.setText("produced");
  }
}
