package com.ufg.i4soft.droidrepair.view.windows;

import javax.swing.*;
import java.awt.*;

public class ProgressBarDemo extends JFrame {

    private JProgressBar progressBar;

    public ProgressBarDemo() {
        super("Aguarde");

        progressBar = new JProgressBar(0, 100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        JPanel panel = new JPanel();
        panel.add(progressBar);

        add(panel, BorderLayout.PAGE_START);

        createAndShowGUI();

    }

    private void createAndShowGUI() {
        //Create and set up the window.
        pack();
        setTitle("Escolha da Ferramenta de Reparo");
        setSize(500, 300);
        centerWindows();
    }

    private void centerWindows() {

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int width = (screenSize.width - getWidth()) / 2;
        final int height = (screenSize.height - getHeight()) / 2;
        setLocation(width, height);
    }

}
