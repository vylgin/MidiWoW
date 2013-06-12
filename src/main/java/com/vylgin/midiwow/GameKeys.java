package com.vylgin.midiwow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class to hold the key relationships midi keyboard to PC keyboard keys
 * @author vylgin
 */
public class GameKeys {
    public static final String emptyKeyText = "Empty";
    private volatile static GameKeys gameKeys;
    
    private static final String dirSeparator = System.getProperty("file.separator");
    private static final String fileExtension = ".properties";
    private static final String propertiesNameDir = "properties";
    private static final int midiKeySize = 127;
    private static final int emptyNote = -1;
        
    private static Properties props = new Properties();
    private static String fileName;
    private static File currentDir = new File(".");
    
    private ArrayList<ArrayList<Integer>> midiKeys;
    
    private GameKeys() {
        midiKeys = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= midiKeySize; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(emptyNote);
            midiKeys.add(list);
        }
    }
    
    /**
     * Get GameKeys class object
     * @return GameKeys singleton class object
     */
    public static GameKeys getInstance() {
        if (gameKeys == null) {
            synchronized (GameKeys.class) {
                if (gameKeys == null) {
                    gameKeys = new GameKeys();
                }
            }
        }
        return gameKeys;
    }
    
    /**
     * Sets the PC keyboard keys corresponding key midi keyboard
     * @param note number of note midi keyboard
     * @param keyboardKeys code keys PC keyboard
     */
    public void setKeyboardKeys(int note, ArrayList<Integer> keyboardKeys) {
        midiKeys.remove(note);
        midiKeys.add(note, keyboardKeys);
    }
    
    /**
     * 
     * @return empty note
     */
    public static int getEmptyNote() {
        return emptyNote;
    }
    
    /**
     * Gets the PC keyboard keys corresponding key midi keyboard
     * @param note number of note midi keyboard
     * @return keys of PC keyboard
     */
    public ArrayList<Integer> getKeyboardKeys(int note) {
        return midiKeys.get(note);
    }
    
    /**
     * Create empty keys of midi keyboard for PC Game Name
     * @param gameName PC Game Name
     */
    public boolean createEmptyKeys(String gameName) {
        try {
            String fName = gameName + fileExtension;
            String filePath = currentDir.getCanonicalPath() + dirSeparator 
                    + propertiesNameDir + dirSeparator + fName;
                          
            for (int i = 0; i <= midiKeySize; i++) {
                props.setProperty(String.valueOf(i), String.valueOf(emptyNote));
            }
            
            FileOutputStream out = new FileOutputStream(filePath);
            props.store(out, "Created with createEmptyKeys method");
            out.flush();
            out.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(GameKeys.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    /**
     * Save parameters of PC Game Name from properties folder
     * @param gameName PC Game Name
     */
    public boolean saveKeys(String gameName) {      
        try {
            fileName = gameName + fileExtension;
            String filePath = currentDir.getCanonicalPath() + dirSeparator 
                    + propertiesNameDir + dirSeparator + fileName;    
            
            for (int i = 0; i <= midiKeySize; i++) {
                ArrayList<Integer> list = midiKeys.get(i);
                String keys = "";
                
                for (int key : list) {
                    keys += String.valueOf(key) + " ";
                }
                props.setProperty(String.valueOf(i), keys);
            }
            
            FileOutputStream out = new FileOutputStream(filePath);
            props.store(out, "Created with saveKeys method");
            out.flush();
            out.close();
            return true;
        } catch (IOException e) {
            System.out.println("IO Error in GameKeys class saveKeys method!");
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Load parameters of game name from properties folder
     * @param gameName PC Game Name
     */
    public boolean loadKeys(String gameName) {
        try {
            fileName = gameName + fileExtension;
            String filePath = currentDir.getCanonicalPath() + dirSeparator + propertiesNameDir + dirSeparator + fileName;
                 
            FileInputStream ins = new FileInputStream(filePath);
            props.load(ins);
            ins.close();
            
            for (int i = 0; i <=midiKeySize; i++) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                Scanner scaner = new Scanner(props.getProperty(String.valueOf(i)));
                while (scaner.hasNextInt()) {
                    list.add(scaner.nextInt());
                }
                setKeyboardKeys(i, list);
            }   
            return true;
        } catch (IOException ex) {
            Logger.getLogger(GameKeys.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Delete properties for PC Game Name
     * @param gameName PC Game Name
     * @return <code>true</code>, if properties file deleted
     */
    public boolean deleteKeys(String gameName) {
        try {
            String fName = gameName + fileExtension;
            String filePath = currentDir.getCanonicalPath() + dirSeparator 
                    + propertiesNameDir + dirSeparator + fName;
            File file = new File(filePath);
            file.setWritable(true);
            if (file.delete()) {
                return true;   
            }
        } catch (IOException ex) {
            Logger.getLogger(GameKeys.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
}
