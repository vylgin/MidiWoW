/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vylgin.midiwow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The main frame at which there are widgets
 * @author vylgin
 */
public class MainWindow extends JFrame {
    private final DefaultListModel listModel = new DefaultListModel();
    private DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<String>();
    private static final Pattern patternGameName = Pattern.compile("[a-zA-Z\\d\\s]+");
    private static final String propertiesNameDir = "properties";
    private static final String fileExtension = ".properties";
    private static final String dirSeparator = System.getProperty("file.separator");
    private static final String propertiesDirPath = "." + dirSeparator + propertiesNameDir;
    private static final File dirProperties = new File(propertiesDirPath);
    
    private static final String readyStatusBarText = "Ready.";
    private static final String createGameKeysText = "Create new Game Keys profile.";
    private static final String saveGameKeysText = "Save this Game Keys profile.";
    private static final String saveAsGameKeysText = "Save As this Game Keys profile.";
    private static final String deleteGameKeysText = "Delete this Game Keys profile.";
    
    private static enum MidiKeyEvent {
        KEY_PRESS_EVENT,
        KEY_RELEASE_EVENT
    }
        
    /**
     * 
     * @return this
     */
    public MainWindow getFrame() {
        return this;
    }
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        if (!dirProperties.exists()) {
            dirProperties.mkdir();
            String defaultGame = "default";
            GameKeys gameKeys = GameKeys.getInstance();
            gameKeys.createEmptyKeys(defaultGame);
            gameKeys.saveKeys(defaultGame);
        }
                
        initComponents();
        selectGameComboBox.setModel(comboBoxModel);
        initializeGameComboBox();
        statusLabel.setText(readyStatusBarText);
    }
    
    /**
     * Set vector of midi devices to devices combo box.
     * @param midiDevices vector of midi devices.
     */
    public void setMidiDeviceNames(Vector<MidiDevice.Info> midiDevices) {
        midiDevicesComboBox.setModel(new DefaultComboBoxModel<MidiDevice.Info>(midiDevices));
        midiMessagesList.setModel(listModel);
    }
    
    public void setStatusBarText(String text) {
        statusLabel.setText(text);
    }
    
    public String getReadyStatusBarText() {
        return readyStatusBarText;
    }
    
    private void initializeGameComboBox() {
        for (String file : dirProperties.list()) {
            int i = file.lastIndexOf('.');
            String fileName = file.substring(0, i);
            String fileExtension = file.substring(i, file.length());
            if (fileExtension.equals(this.fileExtension)) {
                selectGameComboBox.addItem(fileName);   
            }
        }
    }
    
    private void loadSelectedGameKeys() {
        GameKeys gameKeys = GameKeys.getInstance();
        gameKeys.loadKeys((String) selectGameComboBox.getSelectedItem());
    }

    private void setBacklightNotEmptyKeys() {
        VirtualMidiKeyboard vmk = (VirtualMidiKeyboard) pianoKeysPanel;
        vmk.clearBacklight();
        vmk.backlightNotEmptyKeys();
    }
    
    private void createGameKeys() {
        String gameName = JOptionPane.showInputDialog(this, "Input game name (English letters and numbers):");
        Matcher matcher = patternGameName.matcher(gameName);
        if (matcher.matches() && comboBoxModel.getIndexOf(gameName) == -1) {
            GameKeys gameKeys = GameKeys.getInstance();
            gameKeys.createEmptyKeys(gameName);
            selectGameComboBox.addItem(gameName);
            selectGameComboBox.setSelectedItem(gameName);
            setBacklightNotEmptyKeys();
        } else {
            JOptionPane.showMessageDialog(this, "Propertie does'n exist. Use English letters and numbers in name");
        }
    }
    
    private void saveGameKeys() {
        if (selectGameComboBox.getSelectedItem() != null) {
            GameKeys gameKeys = GameKeys.getInstance();
            gameKeys.saveKeys((String) selectGameComboBox.getSelectedItem());
        }
    }
    
    private void saveAsGameKeys() {
        String gameName = JOptionPane.showInputDialog(this, "Input new game name (English letters and numbers):");
        Matcher matcher = patternGameName.matcher(gameName);
        if (matcher.matches() && comboBoxModel.getIndexOf(gameName) == -1) {
            GameKeys gameKeys = GameKeys.getInstance();
            gameKeys.saveKeys(gameName);
            selectGameComboBox.addItem(gameName);
            selectGameComboBox.setSelectedItem(gameName);
            setBacklightNotEmptyKeys();
        } else {
            JOptionPane.showMessageDialog(this, "Propertie does'n save and exist. Use English letters and numbers in name");
        }
    }
    
    private void deleteGameKeys() {
        GameKeys gameKeys = GameKeys.getInstance();
        gameKeys.deleteProperty((String) selectGameComboBox.getSelectedItem());
        selectGameComboBox.removeItemAt(selectGameComboBox.getSelectedIndex());
        setBacklightNotEmptyKeys();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        midiDevicesLabel = new javax.swing.JLabel();
        midiDevicesComboBox = new javax.swing.JComboBox();
        selectMidiDeviceButton = new javax.swing.JButton();
        infoSelectedDeviceLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        midiMessagesList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        pianoKeysPanel = new VirtualMidiKeyboard();
        selectGameComboBox = new javax.swing.JComboBox();
        saveGameKeysButton = new javax.swing.JButton();
        createGameKeysButton = new javax.swing.JButton();
        deleteGameKeysButton = new javax.swing.JButton();
        saveAsButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        statusPanel = new javax.swing.JPanel();
        statusLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        gameKeysMenu = new javax.swing.JMenu();
        createMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        documentationMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        aboutMidiWoWMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MidiWoW");

        midiDevicesLabel.setText("Midi Devices:");

        midiDevicesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        midiDevicesComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                midiDevicesComboBoxMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                midiDevicesComboBoxMouseExited(evt);
            }
        });

        selectMidiDeviceButton.setText("Select Device");
        selectMidiDeviceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectMidiDeviceButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectMidiDeviceButtonMouseExited(evt);
            }
        });
        selectMidiDeviceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectMidiDeviceButtonActionPerformed(evt);
            }
        });

        infoSelectedDeviceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        infoSelectedDeviceLabel.setText("Midi device don't select");

        midiMessagesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        }
    );
    jScrollPane1.setViewportView(midiMessagesList);

    jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

    pianoKeysPanel.setBackground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout pianoKeysPanelLayout = new javax.swing.GroupLayout(pianoKeysPanel);
    pianoKeysPanel.setLayout(pianoKeysPanelLayout);
    pianoKeysPanelLayout.setHorizontalGroup(
        pianoKeysPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 790, Short.MAX_VALUE)
    );
    pianoKeysPanelLayout.setVerticalGroup(
        pianoKeysPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 121, Short.MAX_VALUE)
    );

    jScrollPane2.setViewportView(pianoKeysPanel);

    selectGameComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
    selectGameComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            selectGameComboBoxMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            selectGameComboBoxMouseExited(evt);
        }
    });
    selectGameComboBox.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            selectGameComboBoxActionPerformed(evt);
        }
    });

    saveGameKeysButton.setText("Save");
    saveGameKeysButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            saveGameKeysButtonMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            saveGameKeysButtonMouseExited(evt);
        }
    });
    saveGameKeysButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            saveGameKeysButtonActionPerformed(evt);
        }
    });

    createGameKeysButton.setText("Create");
    createGameKeysButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            createGameKeysButtonMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            createGameKeysButtonMouseExited(evt);
        }
    });
    createGameKeysButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            createGameKeysButtonActionPerformed(evt);
        }
    });

    deleteGameKeysButton.setText("Delete");
    deleteGameKeysButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            deleteGameKeysButtonMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            deleteGameKeysButtonMouseExited(evt);
        }
    });
    deleteGameKeysButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteGameKeysButtonActionPerformed(evt);
        }
    });

    saveAsButton.setText("Save As...");
    saveAsButton.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            saveAsButtonMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            saveAsButtonMouseExited(evt);
        }
    });
    saveAsButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            saveAsButtonActionPerformed(evt);
        }
    });

    jLabel1.setText("Game Keys:");

    statusPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
    statusPanel.setLayout(statusPanelLayout);
    statusPanelLayout.setHorizontalGroup(
        statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(statusPanelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    statusPanelLayout.setVerticalGroup(
        statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(statusLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 12, Short.MAX_VALUE)
    );

    fileMenu.setText("File");
    fileMenu.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            fileMenuMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            fileMenuMouseExited(evt);
        }
    });

    exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
    exitMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cross.png"))); // NOI18N
    exitMenuItem.setText("Exit");
    exitMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            exitMenuItemMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            exitMenuItemMouseExited(evt);
        }
    });
    exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            exitMenuItemActionPerformed(evt);
        }
    });
    fileMenu.add(exitMenuItem);

    menuBar.add(fileMenu);

    gameKeysMenu.setText("Game Keys");
    gameKeysMenu.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            gameKeysMenuMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            gameKeysMenuMouseExited(evt);
        }
    });

    createMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
    createMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/add.png"))); // NOI18N
    createMenuItem.setText("Create");
    createMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            createMenuItemMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            createMenuItemMouseExited(evt);
        }
    });
    createMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            createMenuItemActionPerformed(evt);
        }
    });
    gameKeysMenu.add(createMenuItem);

    saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
    saveMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/page_save.png"))); // NOI18N
    saveMenuItem.setText("Save");
    saveMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            saveMenuItemMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            saveMenuItemMouseExited(evt);
        }
    });
    saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            saveMenuItemActionPerformed(evt);
        }
    });
    gameKeysMenu.add(saveMenuItem);

    saveAsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
    saveAsMenuItem.setText("Save As...");
    saveAsMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            saveAsMenuItemMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            saveAsMenuItemMouseExited(evt);
        }
    });
    saveAsMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            saveAsMenuItemActionPerformed(evt);
        }
    });
    gameKeysMenu.add(saveAsMenuItem);

    deleteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
    deleteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
    deleteMenuItem.setText("Delete");
    deleteMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            deleteMenuItemMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            deleteMenuItemMouseExited(evt);
        }
    });
    deleteMenuItem.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deleteMenuItemActionPerformed(evt);
        }
    });
    gameKeysMenu.add(deleteMenuItem);

    menuBar.add(gameKeysMenu);

    helpMenu.setText("Help");
    helpMenu.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            helpMenuMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            helpMenuMouseExited(evt);
        }
    });

    documentationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
    documentationMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/help.png"))); // NOI18N
    documentationMenuItem.setText("Documentation");
    documentationMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            documentationMenuItemMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            documentationMenuItemMouseExited(evt);
        }
    });
    helpMenu.add(documentationMenuItem);
    helpMenu.add(jSeparator1);

    aboutMidiWoWMenuItem.setText("About MidiWoW");
    aboutMidiWoWMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            aboutMidiWoWMenuItemMouseEntered(evt);
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            aboutMidiWoWMenuItemMouseExited(evt);
        }
    });
    helpMenu.add(aboutMidiWoWMenuItem);

    menuBar.add(helpMenu);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(infoSelectedDeviceLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(statusPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(midiDevicesLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(midiDevicesComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(selectMidiDeviceButton))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(selectGameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(createGameKeysButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(saveGameKeysButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(saveAsButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(deleteGameKeysButton)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)))))))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(midiDevicesLabel)
                .addComponent(midiDevicesComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(selectMidiDeviceButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(infoSelectedDeviceLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(selectGameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(createGameKeysButton)
                        .addComponent(deleteGameKeysButton)
                        .addComponent(saveGameKeysButton)
                        .addComponent(saveAsButton)
                        .addComponent(jLabel1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jScrollPane1))
            .addGap(18, 18, 18)
            .addComponent(statusPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectMidiDeviceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMidiDeviceButtonActionPerformed
        try {
            MidiDevice device = MidiSystem.getMidiDevice((MidiDevice.Info) midiDevicesComboBox.getSelectedItem());
            Transmitter trans = device.getTransmitter();
            trans.setReceiver(new MidiInputReceiver());

            if (!device.isOpen()) {
                device.open();
                infoSelectedDeviceLabel.setText("Midi device \"" + device.getDeviceInfo() + "\" was opened");
            }
        } catch (MidiUnavailableException e1) {
            infoSelectedDeviceLabel.setText("Midi device \"" + midiDevicesComboBox.getSelectedItem() + "\" was don't opened");
        }
    }//GEN-LAST:event_selectMidiDeviceButtonActionPerformed

    private void selectGameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectGameComboBoxActionPerformed
        if (selectGameComboBox.getSelectedItem() != null) {
            loadSelectedGameKeys();
            setBacklightNotEmptyKeys();
        }
    }//GEN-LAST:event_selectGameComboBoxActionPerformed

    private void saveGameKeysButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveGameKeysButtonActionPerformed
        saveGameKeys();
    }//GEN-LAST:event_saveGameKeysButtonActionPerformed

    private void createGameKeysButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGameKeysButtonActionPerformed
        createGameKeys();
    }//GEN-LAST:event_createGameKeysButtonActionPerformed

    private void deleteGameKeysButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGameKeysButtonActionPerformed
        deleteGameKeys();
    }//GEN-LAST:event_deleteGameKeysButtonActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        saveAsGameKeys();
    }//GEN-LAST:event_saveAsButtonActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);    
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void createMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createMenuItemActionPerformed
        createGameKeys();
    }//GEN-LAST:event_createMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        saveGameKeys();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveAsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsMenuItemActionPerformed
        saveAsGameKeys();
    }//GEN-LAST:event_saveAsMenuItemActionPerformed

    private void deleteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMenuItemActionPerformed
        deleteGameKeys();
    }//GEN-LAST:event_deleteMenuItemActionPerformed

    private void midiDevicesComboBoxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_midiDevicesComboBoxMouseEntered
        statusLabel.setText("Change midi device.");
    }//GEN-LAST:event_midiDevicesComboBoxMouseEntered

    private void midiDevicesComboBoxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_midiDevicesComboBoxMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_midiDevicesComboBoxMouseExited

    private void selectMidiDeviceButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMidiDeviceButtonMouseEntered
        statusLabel.setText("Select midi device.");
    }//GEN-LAST:event_selectMidiDeviceButtonMouseEntered

    private void selectMidiDeviceButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMidiDeviceButtonMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_selectMidiDeviceButtonMouseExited

    private void selectGameComboBoxMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectGameComboBoxMouseEntered
        statusLabel.setText("Change Game Keys profile.");
    }//GEN-LAST:event_selectGameComboBoxMouseEntered

    private void selectGameComboBoxMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectGameComboBoxMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_selectGameComboBoxMouseExited

    private void createGameKeysButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createGameKeysButtonMouseEntered
        statusLabel.setText(createGameKeysText);
    }//GEN-LAST:event_createGameKeysButtonMouseEntered

    private void createGameKeysButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createGameKeysButtonMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_createGameKeysButtonMouseExited

    private void saveGameKeysButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveGameKeysButtonMouseEntered
        statusLabel.setText(saveGameKeysText);
    }//GEN-LAST:event_saveGameKeysButtonMouseEntered

    private void saveGameKeysButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveGameKeysButtonMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_saveGameKeysButtonMouseExited

    private void saveAsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveAsButtonMouseEntered
        statusLabel.setText(saveAsGameKeysText);
    }//GEN-LAST:event_saveAsButtonMouseEntered

    private void saveAsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveAsButtonMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_saveAsButtonMouseExited

    private void deleteGameKeysButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteGameKeysButtonMouseEntered
        statusLabel.setText(deleteGameKeysText);
    }//GEN-LAST:event_deleteGameKeysButtonMouseEntered

    private void deleteGameKeysButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteGameKeysButtonMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_deleteGameKeysButtonMouseExited

    private void exitMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuItemMouseEntered
        statusLabel.setText("Close this program.");
    }//GEN-LAST:event_exitMenuItemMouseEntered

    private void exitMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMenuItemMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_exitMenuItemMouseExited

    private void createMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createMenuItemMouseEntered
        statusLabel.setText(createGameKeysText);
    }//GEN-LAST:event_createMenuItemMouseEntered

    private void createMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createMenuItemMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_createMenuItemMouseExited

    private void saveMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMenuItemMouseEntered
        statusLabel.setText(saveGameKeysText);
    }//GEN-LAST:event_saveMenuItemMouseEntered

    private void saveMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMenuItemMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_saveMenuItemMouseExited

    private void saveAsMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveAsMenuItemMouseEntered
        statusLabel.setText(saveAsGameKeysText);
    }//GEN-LAST:event_saveAsMenuItemMouseEntered

    private void saveAsMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveAsMenuItemMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_saveAsMenuItemMouseExited

    private void deleteMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMenuItemMouseEntered
        statusLabel.setText(deleteGameKeysText);
    }//GEN-LAST:event_deleteMenuItemMouseEntered

    private void deleteMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMenuItemMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_deleteMenuItemMouseExited

    private void documentationMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_documentationMenuItemMouseEntered
        statusLabel.setText("View documentation.");
    }//GEN-LAST:event_documentationMenuItemMouseEntered

    private void documentationMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_documentationMenuItemMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_documentationMenuItemMouseExited

    private void aboutMidiWoWMenuItemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMidiWoWMenuItemMouseEntered
        statusLabel.setText("View information about MidiWoW program.");
    }//GEN-LAST:event_aboutMidiWoWMenuItemMouseEntered

    private void aboutMidiWoWMenuItemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMidiWoWMenuItemMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_aboutMidiWoWMenuItemMouseExited

    private void fileMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuMouseEntered
        statusLabel.setText("Open File menu.");
    }//GEN-LAST:event_fileMenuMouseEntered

    private void fileMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_fileMenuMouseExited

    private void gameKeysMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameKeysMenuMouseEntered
        statusLabel.setText("Open Game Keys menu.");
    }//GEN-LAST:event_gameKeysMenuMouseEntered

    private void gameKeysMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gameKeysMenuMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_gameKeysMenuMouseExited

    private void helpMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMenuMouseEntered
        statusLabel.setText("Open Help menu");
    }//GEN-LAST:event_helpMenuMouseEntered

    private void helpMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMenuMouseExited
        statusLabel.setText(readyStatusBarText);
    }//GEN-LAST:event_helpMenuMouseExited
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMidiWoWMenuItem;
    private javax.swing.JButton createGameKeysButton;
    private javax.swing.JMenuItem createMenuItem;
    private javax.swing.JButton deleteGameKeysButton;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenuItem documentationMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu gameKeysMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel infoSelectedDeviceLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JComboBox midiDevicesComboBox;
    private javax.swing.JLabel midiDevicesLabel;
    private javax.swing.JList midiMessagesList;
    private javax.swing.JPanel pianoKeysPanel;
    private javax.swing.JButton saveAsButton;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JButton saveGameKeysButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JComboBox selectGameComboBox;
    private javax.swing.JButton selectMidiDeviceButton;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    /**
    * The handler of the received messages
    * @author vylgin
    */
    public class MidiInputReceiver implements Receiver {
        
        private static final int SYSTEM_MIDI_SIGNAL = 248;
        private static final int NOTE_ON_MIDI_SIGNAL = 144;
        private static final int NOTE_OFF_MIDI_SIGNAL = 128;

        private void eventKeyEmit(ArrayList<Integer> keysEventList, MidiKeyEvent keyPressEvent) {
            switch (keyPressEvent) {
                case KEY_PRESS_EVENT:
                    try {
                        Robot robot = new Robot();
                        for (int key : keysEventList) {
                            if (key != GameKeys.getEmptyNote()) {
                                robot.keyPress(key);
                                listModel.addElement("Pressed key: " + KeyEvent.getKeyText(key));
                            }
                        }                        
                    } catch (AWTException e) { }
                    break;
                case KEY_RELEASE_EVENT:
                    try {
                        Robot robot = new Robot();
                        for (int key : keysEventList) {
                            if (key != GameKeys.getEmptyNote()) {
                                robot.keyRelease(key); 
                                listModel.addElement("Released key: " + KeyEvent.getKeyText(key));
                            }
                        }                        
                    } catch (AWTException e) { }
                    break;
                default:
                    midiMessagesList.repaint();
            }
        }
        
        private void bindKeys(int statusMidiKey, int numberMidiKeyEvent) {
            GameKeys gameKeys = GameKeys.getInstance();
            ArrayList<Integer> listKeys = gameKeys.getKeyboardKeys(numberMidiKeyEvent);
            switch (statusMidiKey) {
                case NOTE_ON_MIDI_SIGNAL:
                    eventKeyEmit(listKeys, MidiKeyEvent.KEY_PRESS_EVENT);
                    break;
                case NOTE_OFF_MIDI_SIGNAL:
                    eventKeyEmit(listKeys, MidiKeyEvent.KEY_RELEASE_EVENT);
                    break;
            }
        }

        @Override
        public void send(MidiMessage message, long timeStamp) {
            ShortMessage event = (ShortMessage) message;

            if (event.getStatus() != SYSTEM_MIDI_SIGNAL) {                              
                if (listModel.getSize() >= 8) {
                    listModel.remove(0);
                }
                
                VirtualMidiKeyboard vmk = (VirtualMidiKeyboard) pianoKeysPanel;
                
                if (event.getStatus() == NOTE_ON_MIDI_SIGNAL) {
                    vmk.backlightOnKey(event.getData1());
                }

                if (event.getStatus() == NOTE_OFF_MIDI_SIGNAL) {
                    vmk.backlightOffKey(event.getData1());
                }
                
                bindKeys(event.getStatus(), event.getData1());
            }
        }

        @Override
        public void close() {
        }
    }
}
