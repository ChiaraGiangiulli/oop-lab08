package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    
    private static final String TITLE = "First graphical interface";
    private static final int PROPORTION = 2;
    private final JFrame frame = new JFrame(TITLE);
    final Controller cntrl = new Controller(); 
    

    public SimpleGUI(){
        
        final JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());
        frame.setContentPane(newPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTextArea text = new JTextArea();
        newPanel.add(text,BorderLayout.CENTER);
        JButton save = new JButton("save");
        newPanel.add(save, BorderLayout.SOUTH);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent act){
                try{
                    cntrl.writeOnFile(text.getText());
                }
                catch(IOException exc){
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
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
