package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {
    
    private File currentFile = new File(System.getProperty("user.home") + 
                                System.getProperty("file.separator") + "output.txt");
     
    public void setCurrentFile(File file){
        this.currentFile = file;
    }

    public File getCurrentFile(){
        return this.currentFile;
    }

    public String getCurrentPath(){
        return this.currentFile.getPath();
    }

    public void writeOnFile(String s) throws IOException{
        try(PrintStream out = new PrintStream(currentFile)){
            out.print(s);
        }
    }

}
