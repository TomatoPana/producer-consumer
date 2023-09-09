package com.mdlb.frontend;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GridPanel extends JPanel {

    public GridPanel() {
        for (int index = 0; index < 20; index++) {
            // Cambiar por elemento el cual representara si un elemento esta producido o
            // consumido.
            JButton button = new JButton("Botón" + (index + 1));
            add(button);
        }
        setLayout(new GridLayout(2, 10, 16, 16));
        setVisible(true);
    }
}