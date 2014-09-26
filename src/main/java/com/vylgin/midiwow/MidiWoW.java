package com.vylgin.midiwow;

import javax.swing.*;

import com.vylgin.midiwow.ui.MainWindow;
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
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        try {
            System.setProperty("apple.laf.useScreenMenuBar", "true");    
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
            log.error("Exception!", e);
        }
        
        MidiWoW midiWoW = new MidiWoW();
        midiWoW.go();
        log.info("MidiWoW started.");
    }

    private void go() {
        MainWindow frame = new MainWindow();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 365);
        frame.setVisible(true);
    }
 }
