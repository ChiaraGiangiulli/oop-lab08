package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;

/**
 * A very simple program using a graphical interface.
 * 
 */

public final class SimpleGUI {
    
    private static final String TITLE = "Strings";
    private static final int PROPORTION = 2;
    private final JFrame frame = new JFrame(TITLE);
    final Controller cntrl = new SimpleController(); 

    public SimpleGUI(){
        
        final JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        newPanel.add(buttonsPanel,BorderLayout.SOUTH);

        frame.setContentPane(newPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea();
        newPanel.add(textArea, BorderLayout.CENTER);

        JTextField textField = new JTextField();
        newPanel.add(textField, BorderLayout.NORTH);

        JButton print = new JButton("Print");
        buttonsPanel.add(print, BorderLayout.LINE_START);

        JButton history = new JButton("Show history");
        buttonsPanel.add(history, BorderLayout.LINE_END);

        print.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent act){
                cntrl.setNextString(textField.getText());
                cntrl.print();
            }
        });

        history.addActionListener ( new ActionListener() {
            public void actionPerformed(final ActionEvent act){
                textArea.setText(cntrl.printedStrings().toString() + "\n");
            }
                
        });
    
    }

    public static void main(final String... args) {
        new SimpleGUI().display();
    }


    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
