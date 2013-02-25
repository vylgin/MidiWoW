/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vylgin.midiwow;

import java.util.Collections;
import java.util.Vector;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiSystem;
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
public class MidiWoWTest {
    MidiWoW midiWoW;
            
    public MidiWoWTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        midiWoW = new MidiWoW();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of testGetMidiDeviceInfo method, of class MidiWoW.
     */
    @Test
    public void testGetMidiDeviceInfo() {
        Vector<MidiDevice.Info> midiNamesDevicesInfo = midiWoW.getMidiNamesDevicesInfo();
        Vector<MidiDevice.Info> namesMidiDevices = new Vector<MidiDevice.Info>();
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        
        Collections.addAll(namesMidiDevices, infos);
        
        assertEquals(midiNamesDevicesInfo, namesMidiDevices);
    }
}
