package org.example.lab8;

import javax.swing.*;
import java.awt.*;

public class MazeApp extends JFrame {
    private ConfigPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    public MazeApp() {
        super("Maze Generator - Compulsory");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        canvas = new DrawingPanel();
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);


        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MazeApp::new);
    }
}