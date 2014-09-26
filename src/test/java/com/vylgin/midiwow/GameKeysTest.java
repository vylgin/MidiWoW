package com.vylgin.midiwow;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.vylgin.midiwow.settings.Config;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vylgin
 */
public class GameKeysTest {
    private static String gameKeysDirPropertiesPath = String.format(
            "%s%s%s",
            ".",
            Config.DIR_SEPARATOR,
            GameKeys.GAME_KEYS_DIR_NAME_PROPERTIES);
    private static File dirProperties = new File(gameKeysDirPropertiesPath);

    private static final int NOTE_1 = 5;
    private static final int NOTE_2 = 3;

    private static final String GAME_NAME_1 = "WoW_1";
    private static final String GAME_NAME_2 = "WoW_2";
    private static final String GAME_NAME_3 = "WoW_3";

    private static ArrayList<Integer> keyboardKeys1 = null;
    private static ArrayList<Integer> keyboardKeys2 = null;

    private static GameKeys gameKeys;
    private static List<String> gameNamesForDeleteAfterTests;

    public GameKeysTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        gameNamesForDeleteAfterTests = new ArrayList<>();
        gameKeys = GameKeys.getInstance();
        if (!dirProperties.exists()) {
            dirProperties.mkdir();
        }

        keyboardKeys1 = new ArrayList<>();
        keyboardKeys1.add(5);
        keyboardKeys1.add(6);
        keyboardKeys1.add(7);

        keyboardKeys2 = new ArrayList<>();
        keyboardKeys2.add(3);
        keyboardKeys2.add(4);
        keyboardKeys2.add(5);
        keyboardKeys2.add(6);
    }
    
    @AfterClass
    public static void tearDownClass() {
        keyboardKeys1 = null;
        keyboardKeys2 = null;

        List<String> gameNamesForDelete = new ArrayList<>();
        gameNamesForDelete.add(GAME_NAME_1);
        gameNamesForDelete.add(GAME_NAME_2);
        gameNamesForDelete.add(GAME_NAME_3);
        gameKeys.deleteKeys(gameNamesForDelete);
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void createClass() {
        assertNotNull(gameKeys);
    }
    
    @Test
    public void checkSingleClass() {
        GameKeys gk = GameKeys.getInstance();
        assertEquals(gameKeys, gk);
    }
    
    @Test
    public void setAndGetKeyboardKeys() {
        gameKeys.setKeyboardKeys(NOTE_1, keyboardKeys1);
        assertArrayEquals(keyboardKeys1.toArray(), gameKeys.getKeyboardKeys(NOTE_1).toArray());
    }
    
    @Test
    public void saveAndLoadKeys() {
        gameKeys.createEmptyKeys(GAME_NAME_1);
        gameKeys.setKeyboardKeys(NOTE_1, keyboardKeys1);
        gameKeys.saveKeys(GAME_NAME_1);
        
        gameKeys.createEmptyKeys(GAME_NAME_2);
        gameKeys.loadKeys(GAME_NAME_2);
        
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(GameKeys.EMPTY_NOTE);
        
        gameKeys.setKeyboardKeys(NOTE_2, keyboardKeys2);
        gameKeys.saveKeys(GAME_NAME_2);
        gameKeys.loadKeys(GAME_NAME_2);
        assertArrayEquals(keyboardKeys2.toArray(), gameKeys.getKeyboardKeys(NOTE_2).toArray());
        assertArrayEquals(gameKeys.getKeyboardKeys(NOTE_1).toArray(), test.toArray());
        
        gameKeys.loadKeys(GAME_NAME_1);
        assertArrayEquals(keyboardKeys1.toArray(), gameKeys.getKeyboardKeys(NOTE_1).toArray());
        assertArrayEquals(gameKeys.getKeyboardKeys(NOTE_2).toArray(), test.toArray());
    }

    @Test
    public void deleteKeysForName() {
        gameKeys.createEmptyKeys(GAME_NAME_3);
        gameKeys.loadKeys(GAME_NAME_3);
        assertTrue(gameKeys.deleteKeys(GAME_NAME_3));
    }
}