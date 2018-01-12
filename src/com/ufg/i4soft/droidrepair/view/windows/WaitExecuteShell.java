package com.ufg.i4soft.droidrepair.view.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WaitExecuteShell extends JDialog {
    private JPanel contentPane;
    private JProgressBar progressbar;

    public WaitExecuteShell() {
        setContentPane(contentPane);
        setModal(true);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void centerWindows() {

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int width = (screenSize.width - getWidth()) / 2;
        final int height = (screenSize.height - getHeight()) / 2;
        setLocation(width, height);
    }

    public static WaitExecuteShell showCommand() {

        WaitExecuteShell dialog = new WaitExecuteShell();
        dialog.pack();
        dialog.setTitle("Aguarde");
        dialog.setSize(400, 200);
        dialog.centerWindows();

        return dialog;
    }
}
