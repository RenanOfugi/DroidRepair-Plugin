package com.ufg.i4soft.droidrepair.view.windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChooseRepair extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton angelixRadioButton;
    private JRadioButton astor4androidRadioButton;

    private static String typerepair;

    public static String getTyperepair() {
        return typerepair;
    }

    private ChooseRepair() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // centerWindows();
        OneChoise();

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {

        if (angelixRadioButton.isSelected()) {
            typerepair = "angelix";
        } else if (astor4androidRadioButton.isSelected()) {
            typerepair = "astor4android";
        }

        dispose();
    }

    private void onCancel() {
        typerepair = "typeCancel";
        dispose();
    }

    private void OneChoise() {

        ButtonGroup group = new ButtonGroup();
        group.add(angelixRadioButton);
        group.add(astor4androidRadioButton);
    }

    private void centerWindows() {

        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int width = (screenSize.width - getWidth()) / 2;
        final int height = (screenSize.height - getHeight()) / 2;
        setLocation(width, height);
    }

    public static void showTypeRepair() {
        ChooseRepair dialog = new ChooseRepair();
        dialog.pack();
        dialog.setTitle("Escolha da Ferramenta de Reparo");
        dialog.setSize(500, 300);
        dialog.centerWindows();
        dialog.setVisible(true);

    }
}
