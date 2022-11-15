package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private static final String TITLE = "First graphical interface";
    private static final int PROPORTION = 2;
    private final JFrame frame = new JFrame(TITLE);
    final Controller cntrl = new Controller();

    public SimpleGUIWithFileChooser(){
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JPanel newPanel = new JPanel();
        newPanel.setLayout(new BorderLayout());

        JButton save = new JButton("save");
        mainPanel.add(save, BorderLayout.SOUTH);
        JButton browse = new JButton("Browse...");
        newPanel.add(browse,BorderLayout.LINE_END);
        mainPanel.add(newPanel,BorderLayout.NORTH);
        final JTextField text = new JTextField();
        newPanel.add(text,BorderLayout.CENTER);
        JTextArea textArea = new JTextArea();
        mainPanel.add(textArea,BorderLayout.CENTER);

        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        text.setText(cntrl.getCurrentPath());
        text.setEditable(false);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent act){
                try{
                    cntrl.writeOnFile(textArea.getText());
                }
                catch(IOException exc){
                    JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });

        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent act){
                JFileChooser files = new JFileChooser("Where do you want to save?");
                files.setSelectedFile(cntrl.getCurrentFile());
                if(files.showSaveDialog(frame) == JFileChooser.APPROVE_OPTION){
                    final File newFile = files.getSelectedFile();
                    cntrl.setCurrentFile(newFile); 
                    text.setText(newFile.getPath());
                }
                else if (files.showSaveDialog(frame) != JFileChooser.CANCEL_OPTION){
                    JOptionPane.showMessageDialog(frame, files.showSaveDialog(frame), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }



    public static void main(final String... args) {
        new SimpleGUIWithFileChooser().display();
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
