package org.example.lab8;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel(MazeApp frame) {
        JButton createBtn = new JButton("Creează");
        JButton resetBtn = new JButton("Resetează");
        JButton exitBtn = new JButton("Ieșire");

        createBtn.setBackground(Color.magenta);
        resetBtn.setBackground(Color.magenta);
        exitBtn.setBackground(Color.magenta);


        createBtn.addActionListener(e -> frame.getCanvas().createRandomMaze());
        resetBtn.addActionListener(e -> frame.getCanvas().resetMaze());
        exitBtn.addActionListener(e -> System.exit(0));

        add(createBtn);
        add(resetBtn);
        add(exitBtn);
    }
}