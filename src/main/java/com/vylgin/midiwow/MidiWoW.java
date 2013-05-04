package com.vylgin.midiwow;

import java.util.Collections;
import java.util.Vector;
import javax.sound.midi.*;
import javax.swing.*;

/**
 * Main class for runing program
 * @author vylgin
 */
public class MidiWoW {
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        MidiWoW midiWoW = new MidiWoW();
        midiWoW.go();
    }

    public Vector<MidiDevice.Info> getMidiNamesDevicesInfo() {
        MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
        Vector<MidiDevice.Info> namesMidiDevices = new Vector<MidiDevice.Info>();
        Collections.addAll(namesMidiDevices, infos);
        return namesMidiDevices;
    }
    
    private void go() {
        MainJFrame frame = new MainJFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 350);
        frame.setVisible(true);
        frame.setMidiDeviceNames(getMidiNamesDevicesInfo());
    }
 }
