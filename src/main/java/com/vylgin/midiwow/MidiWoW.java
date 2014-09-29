package com.vylgin.midiwow;

import com.vylgin.midiwow.helper.OsCheck;
import com.vylgin.midiwow.ui.MainWindow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

/**
 * Main class for running program
 * @author vylgin
 */
public class MidiWoW {
    private static Logger log = LoggerFactory.getLogger(MidiWoW.class.getName());
    
    public static void main(String[] args) {
        log.info("MidiWoW starting.");

        OsCheck.OSType ostype= OsCheck.getOperatingSystemType();
        switch (ostype) {
            case MacOS:
                try {
                    System.setProperty("apple.laf.useScreenMenuBar", "true");
                    System.setProperty("apple.laf.useScreenMenuBar", "true");
                    System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Test");
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException e) {
                    log.error("Exception!", e);
                }
                break;
            case Windows:
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
            case Linux:
            case Other: break;
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
