package com.vylgin.midiwow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import com.vylgin.midiwow.ui.BindKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.vylgin.midiwow.settings.Config.*;

/**
 * Singleton class to hold the key relationships midi keyboard to PC keyboard keys
 * @author vylgin
 */
public class GameKeys {
    public static final String EMPTY_KEY_TEXT = "Empty";
    public static final String GAME_KEYS_FILE_PROPERTIES = ".properties";
    public static final String GAME_KEYS_DIR_NAME_PROPERTIES = "properties";
    public static final int EMPTY_NOTE = -1;

    private static final int MIDI_KEY_SIZE = 127;

    private volatile static GameKeys gameKeys;

    private static Logger log = LoggerFactory.getLogger(BindKeys.class.getName());
    private static Properties props = new Properties();
    private static String fileName;
    private static File currentDir = new File(".");
    
    private Map<Integer, ArrayList<Integer>> midiKeyMap;
    
    private GameKeys() {
        log.debug("Creating Game Keys.");
        midiKeyMap = new HashMap<>();
        for (int i = 0; i <= MIDI_KEY_SIZE; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(EMPTY_NOTE);
            midiKeyMap.put(i, list);
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
        midiKeyMap.remove(note);
        midiKeyMap.put(note, keyboardKeys);
    }
    
    /**
     * Gets the PC keyboard keys corresponding key midi keyboard
     * @param note number of note midi keyboard
     * @return keys of PC keyboard
     */
    public ArrayList<Integer> getKeyboardKeys(int note) {
        return midiKeyMap.get(note);
    }
    
    /**
     * Create empty keys of midi keyboard for PC Game Name
     * @param gameName PC Game Name
     */
    public boolean createEmptyKeys(String gameName) {
        log.debug("Creating empty keys \"{}\".", gameName);
        try {
            String fName = gameName + GAME_KEYS_FILE_PROPERTIES;
            String filePath = currentDir.getCanonicalPath() + DIR_SEPARATOR
                    + GAME_KEYS_DIR_NAME_PROPERTIES + DIR_SEPARATOR + fName;
                          
            for (int i = 0; i <= MIDI_KEY_SIZE; i++) {
                props.setProperty(String.valueOf(i), String.valueOf(EMPTY_NOTE));
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
            fileName = gameName + GAME_KEYS_FILE_PROPERTIES;
            String filePath = currentDir.getCanonicalPath() + DIR_SEPARATOR
                    + GAME_KEYS_DIR_NAME_PROPERTIES + DIR_SEPARATOR + fileName;
            
            for (int i = 0; i <= MIDI_KEY_SIZE; i++) {
                ArrayList<Integer> list = midiKeyMap.get(i);
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
            fileName = gameName + GAME_KEYS_FILE_PROPERTIES;
            String filePath = currentDir.getCanonicalPath() +
                    DIR_SEPARATOR + GAME_KEYS_DIR_NAME_PROPERTIES + DIR_SEPARATOR + fileName;
                 
            FileInputStream ins = new FileInputStream(filePath);
            props.load(ins);
            ins.close();
            
            for (int i = 0; i <= MIDI_KEY_SIZE; i++) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                Scanner scanner = new Scanner(props.getProperty(String.valueOf(i)));
                while (scanner.hasNextInt()) {
                    list.add(scanner.nextInt());
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
     * Delete properties for Game Name
     * @param gameName Game Name
     * @return <code>true</code>, if properties file deleted
     */
    public boolean deleteKeys(String gameName) {
        log.debug("Deliting keys \"{}\".", gameName);
        try {
            String fName = gameName + GAME_KEYS_FILE_PROPERTIES;
            String filePath = currentDir.getCanonicalPath() + DIR_SEPARATOR
                    + GAME_KEYS_DIR_NAME_PROPERTIES + DIR_SEPARATOR + fName;
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

    /**
     * Delete properties for list of Game Names
     * @param gameKeys list of Game Names
     */
    public void deleteKeys(List<String> gameKeys) {
        for (String gameKey : gameKeys) {
            deleteKeys(gameKey);
        }

    }

    /**
     * Delete all properties for Game Names
     */
    public void deleteAllKeys() {
        List<String> gameNames = getGameNames();
        for (String gameName : gameNames) {
            deleteKeys(gameName);
        }
    }

    /**
     * Method scan game keys properties directory and return list of all files names
     * @return gameNames
     */
    public static List<String> getGameNames() {
        List<String> gameNames = new ArrayList<>();
        for (String file : DIR_PROPERTIES.list()) {
            int i = file.lastIndexOf('.');
            String gameName = file.substring(0, i);
            String fileExtension = file.substring(i, file.length());
            if (fileExtension.equals(GameKeys.GAME_KEYS_FILE_PROPERTIES)) {
                gameNames.add(gameName);
                log.debug("Added \"{}\" Game Keys in combo box.", gameName);
            }
        }

        return gameNames;
    }
}
