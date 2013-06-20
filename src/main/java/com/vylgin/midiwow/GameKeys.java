package com.vylgin.midiwow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Singleton class to hold the key relationships midi keyboard to PC keyboard keys
 * @author vylgin
 */
public class GameKeys {
    public static final String emptyKeyText = "Empty";
    private volatile static GameKeys gameKeys;
    
    private static final String dirSeparator = System.getProperty("file.separator");
    private static final String gameKeysFileExtension = ".properties";
    private static final String gameKeysDirName = "properties";
    private static final int midiKeySize = 127;
    private static final int emptyNote = -1;
    
    private static Logger log = LoggerFactory.getLogger(BindKeys.class.getName());
    private static Properties props = new Properties();
    private static String fileName;
    private static File currentDir = new File(".");
    
    private ArrayList<ArrayList<Integer>> midiKeys;
    
    private GameKeys() {
        log.debug("Creating Game Keys.");
        midiKeys = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= midiKeySize; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(emptyNote);
            midiKeys.add(list);
        }
        log.debug("Game Keys created.");
    }
    
    /**
     * Get GameKeys class object
     * @return GameKeys singleton class object
     */
    public static GameKeys getInstance() {
        log.debug("Geting instance.");
        if (gameKeys == null) {
            synchronized (GameKeys.class) {
                if (gameKeys == null) {
                    log.debug("Creating new Game Keys.");
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
     * @return Game Keys file extension
     */
    public static String getGameKeysFileExtension() {
        return gameKeysFileExtension;
    }
    
     /**
     * 
     * @return Game Keys dir name
     */
    public static String getGameKeysDirName() {
        return gameKeysDirName;
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
        log.debug("Creating empty keys \"{}\".", gameName);
        try {
            String fName = gameName + gameKeysFileExtension;
            String filePath = currentDir.getCanonicalPath() + dirSeparator 
                    + gameKeysDirName + dirSeparator + fName;
                          
            for (int i = 0; i <= midiKeySize; i++) {
                props.setProperty(String.valueOf(i), String.valueOf(emptyNote));
            }
            
            FileOutputStream out = new FileOutputStream(filePath);
            props.store(out, "Created with createEmptyKeys method");
            out.flush();
            out.close();
            log.debug("Empty keys created \"{}\".", gameName);
            return true;
        } catch (IOException e) {
            log.debug("Empty keys don't created \"{}\".", gameName);
            return false;
        }
    }
    
    /**
     * Save parameters of PC Game Name from properties folder
     * @param gameName PC Game Name
     */
    public boolean saveKeys(String gameName) {    
        log.debug("Saving keys \"{}\".", gameName);
        try {
            fileName = gameName + gameKeysFileExtension;
            String filePath = currentDir.getCanonicalPath() + dirSeparator 
                    + gameKeysDirName + dirSeparator + fileName;    
            
            for (int i = 0; i <= midiKeySize; i++) {
                ArrayList<Integer> list = midiKeys.get(i);
                StringBuilder keys = new StringBuilder("");
                
                for (int key : list) {
                    keys.append(String.valueOf(key));
                    keys.append(" ");
                }
                props.setProperty(String.valueOf(i), keys.toString());
            }
            
            FileOutputStream out = new FileOutputStream(filePath);
            props.store(out, "Created with saveKeys method");
            out.flush();
            out.close();
            log.debug("Keys saved \"{}\".", gameName);
            return true;
        } catch (IOException e) {
            log.debug("Keys don't saved \"{}\".", gameName);
            return false;
        }
    }
    
    /**
     * Load parameters of game name from properties folder
     * @param gameName PC Game Name
     */
    public boolean loadKeys(String gameName) {
        log.debug("Loading keys \"{}\".", gameName);
        try {
            fileName = gameName + gameKeysFileExtension;
            String filePath = currentDir.getCanonicalPath() + dirSeparator + gameKeysDirName + dirSeparator + fileName;
                 
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
            log.debug("Keys loaded \"{}\".", gameName);
            return true;
        } catch (IOException e) {
            log.debug("Keys don't loaded \"{}\".", gameName);
            return false;
        }
    }
    
    /**
     * Delete properties for PC Game Name
     * @param gameName PC Game Name
     * @return <code>true</code>, if properties file deleted
     */
    public boolean deleteKeys(String gameName) {
        log.debug("Deliting keys \"{}\".", gameName);
        try {
            String fName = gameName + gameKeysFileExtension;
            String filePath = currentDir.getCanonicalPath() + dirSeparator 
                    + gameKeysDirName + dirSeparator + fName;
            File file = new File(filePath);
            file.setWritable(true);
            if (file.delete()) {
                return true;   
            }
            log.debug("Keys delited \"{}\".", gameName);
        } catch (IOException e) {
            log.debug("Keys don't delited \"{}\".", gameName);
            return false;
        }
        return false;
    }
}
