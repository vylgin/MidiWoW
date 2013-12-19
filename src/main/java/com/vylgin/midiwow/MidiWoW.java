package com.vylgin.midiwow;

import java.util.Collections;
import java.util.Vector;
import javax.sound.midi.*;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main class for running program
 * @author vylgin
 */
public class MidiWoW {
    private static Logger log = LoggerFactory.getLogger(MidiWoW.class.getName());
    
    public static void main(String[] args) {
        log.info("MidiWoW starting.");
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            log.error("Exception!", e);
        }
        
        MidiWoW midiWoW = new MidiWoW();
        midiWoW.go();
        log.info("MidiWoW started.");
    }

    /**
     * Method return connected midi devices to PC
     * @return vector of connected midi devices
     */
    public Vector<MidiDevice.Info> getMidiNamesDevicesInfo() {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        Vector<MidiDevice.Info> namesMidiDevices = new Vector<MidiDevice.Info>();
        Collections.addAll(namesMidiDevices, infos);
        log.info("Getted midi devices info from OS.");
        return namesMidiDevices;
    }
    
    private void go() {
        MainWindow frame = new MainWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 365);
        frame.setVisible(true);
        frame.setMidiDeviceNames(getMidiNamesDevicesInfo());
    }
 }
