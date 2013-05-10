package com.vylgin.midiwow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vylgin
 */
public class GameKeys {
    private volatile static GameKeys gameKeys;
    private ArrayList<ArrayList<Integer>> midiKeys;
    private static final int midiKeySize = 127;
    private static final int emptyNote = -1;
    
    private static Properties props = new Properties();
    private static String dirSeparator = System.getProperty("file.separator");
    private static String fileName;
    
    private GameKeys() {
        midiKeys = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i <= midiKeySize; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(emptyNote);
            midiKeys.add(list);
        }
    }
    
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
    
    public void setKeyboardKeys(int note, ArrayList<Integer> keyboardKeys) {
        midiKeys.remove(note);
        midiKeys.add(note, keyboardKeys);
        
//        saveKeys("WoW");
    }
    
    public ArrayList<Integer> getKeyboardKeys(int note) {
        return midiKeys.get(note);
    }
    
    public void saveKeys(String gameName) {      
        try {
            gameKeys = null;
            
            fileName = gameName + ".properties";
            File currentDir = new File(".");
            String filePath = currentDir.getCanonicalPath() + dirSeparator + fileName;    
            
            for (int i = 0; i <= midiKeySize; i++) {
                ArrayList<Integer> list = midiKeys.get(i);
                String keys = "";
                
                for (int key : list) {
                    keys += String.valueOf(key) + " ";
                }
                props.setProperty(String.valueOf(i), keys);
            }
            
            FileOutputStream out = new FileOutputStream(filePath);
            props.store(out, "This is an optional header comment string");
        } catch (IOException e) {
            System.out.println("IO Error!");
            e.printStackTrace();
        }
    }
    
    public void loadKeys(String gameName) {
        gameKeys = null;
        
        try {
            fileName = gameName + ".properties";
            File currentDir = new File(".");
            String filePath = currentDir.getCanonicalPath() + dirSeparator + fileName;
                 
            FileInputStream ins = new FileInputStream(filePath);
            props.load(ins);
            
            for (int i = 0; i <=midiKeySize; i++) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                Scanner scaner = new Scanner(props.getProperty(String.valueOf(i)));
                while (scaner.hasNextInt()) {
                    list.add(scaner.nextInt());
                }
                setKeyboardKeys(i, list);
            }   
        } catch (IOException ex) {
            Logger.getLogger(GameKeys.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
