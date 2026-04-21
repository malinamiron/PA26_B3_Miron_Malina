package org.example.lab8;

import javax.swing.*;
import java.awt.*;

public class ConfigPanel extends JPanel {
    private final MazeApp frame;
    private JSpinner rowSpinner, colSpinner;

    public ConfigPanel(MazeApp frame) {
        this.frame = frame;
        setLayout(new FlowLayout());


        add(new JLabel("Rânduri:"));
        rowSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 50, 1));
        add(rowSpinner);

        add(new JLabel("Coloane:"));
        colSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 50, 1));
        add(colSpinner);

        JButton drawBtn = new JButton("Desenează Grila");
        drawBtn.addActionListener(e -> {
            int r = (int) rowSpinner.getValue();
            int c = (int) colSpinner.getValue();
            frame.getCanvas().initGrid(r, c);
            frame.pack();
        });
        add(drawBtn);
    }
}