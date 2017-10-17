package dbConnection;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class BinaryDriver {
    private static DataInputStream diStream;              // The input 
    private static DataOutputStream doStream;             // The output file
    private final static String FILE_NAME = "Log.bin";//student file name
    File f = new File(FILE_NAME);
    
    public void writeLog(String textToWrite){
        try {
            FileOutputStream foStream=null;
            foStream = new FileOutputStream(FILE_NAME,true);
            ObjectOutputStream outStream = new ObjectOutputStream(foStream);
            //byte[] toWrite=textToWrite.getBytes();
            outStream.writeBytes(textToWrite);
            outStream.close();
            foStream.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BinaryDriver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(BinaryDriver.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
