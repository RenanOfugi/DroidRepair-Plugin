package com.ufg.i4soft.droidrepair.view.windows;

import javax.swing.*;
import java.awt.*;

public class LoadingConsole extends JFrame {


    public LoadingConsole() {
        super("Aguarde");

        JProgressBar progressBar = new JProgressBar(0, 100);
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
        setTitle("Processando. Aguarde...");
        setSize(500, 0);
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
