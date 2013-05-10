package com.vylgin.midiwow;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author vylgin
 */
public class GameKeysTest {
    GameKeys gameKeys;
    
    public GameKeysTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        gameKeys = GameKeys.getInstance();
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
    public void setAndGetKeybordKeys() {
        int note = 5;
        
        ArrayList<Integer> keyboardKeys = new ArrayList<Integer>();
        keyboardKeys.add(5);
        keyboardKeys.add(6);
        keyboardKeys.add(7);
        
        gameKeys.setKeyboardKeys(note, keyboardKeys);
        
        assertArrayEquals(keyboardKeys.toArray(), gameKeys.getKeyboardKeys(note).toArray());
    }
    
    @Test
    public void saveAndLoadProperties() {
        int note1 = 5;
        int note2 = 3;
        
        ArrayList<Integer> keyboardKeys1 = new ArrayList<Integer>();
        keyboardKeys1.add(5);
        keyboardKeys1.add(6);
        keyboardKeys1.add(7);
               
        gameKeys.setKeyboardKeys(note1, keyboardKeys1);
        gameKeys.saveKeys("WoW");
        
        gameKeys.createEmptyKeys("WoW2");
        gameKeys.loadKeys("WoW2");        
        ArrayList<Integer> keyboardKeys2 = new ArrayList<Integer>();
        keyboardKeys2.add(3);
        keyboardKeys2.add(4);
        keyboardKeys2.add(5);
        keyboardKeys2.add(6);
        
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(-1);
        
        gameKeys.setKeyboardKeys(note2, keyboardKeys2);
        gameKeys.saveKeys("WoW2");    
        gameKeys.loadKeys("WoW2");
        assertArrayEquals(keyboardKeys2.toArray(), gameKeys.getKeyboardKeys(note2).toArray());       
        assertArrayEquals(gameKeys.getKeyboardKeys(note1).toArray(), test.toArray());
        
        gameKeys.loadKeys("WoW");
        assertArrayEquals(keyboardKeys1.toArray(), gameKeys.getKeyboardKeys(note1).toArray());
        assertArrayEquals(gameKeys.getKeyboardKeys(note2).toArray(), test.toArray());
    }
}