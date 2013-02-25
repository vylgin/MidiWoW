/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vylgin.midiwow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Vector;
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

/**
 * The main frame at which there are widgets
 * @author vylgin
 */
public class MainJFrame extends JFrame {
    
    final DefaultListModel listModel = new DefaultListModel();
    
//    public void setMidiMessageToList(String midiMessage) {
//        System.out.println(midiMessage);
//        listModel.addElement(midiMessage);
//    }
    
    public MainJFrame getFrame() {
        return this;
    }
    
    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        midiDevicesLabel.setText("Midi Devices:");

        midiDevicesComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        selectMidiDeviceButton.setText("Select Device");
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
        .addGap(0, 125, Short.MAX_VALUE)
    );
    pianoKeysPanelLayout.setVerticalGroup(
        pianoKeysPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 121, Short.MAX_VALUE)
    );

    jScrollPane2.setViewportView(pianoKeysPanel);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(infoSelectedDeviceLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(jScrollPane2)))
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
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(infoSelectedDeviceLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(46, Short.MAX_VALUE))
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectMidiDeviceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectMidiDeviceButtonActionPerformed
        try {
            MidiDevice device = MidiSystem.getMidiDevice((MidiDevice.Info) midiDevicesComboBox.getSelectedItem());
            Transmitter trans = device.getTransmitter();
            trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

            if (!device.isOpen()) {
                device.open();
                System.out.println(device.getDeviceInfo() + " was opened");
                infoSelectedDeviceLabel.setText("Midi device \"" + device.getDeviceInfo() + "\" was opened");
            }
        } catch (MidiUnavailableException e1) {
//                    e1.printStackTrace();
            System.out.println("Don't use " + midiDevicesComboBox.getSelectedItem());
            infoSelectedDeviceLabel.setText("Midi device \"" + midiDevicesComboBox.getSelectedItem() + "\" was don't opened");
        }
    }//GEN-LAST:event_selectMidiDeviceButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }
    
    public void setMidiDeviceNames(Vector<MidiDevice.Info> midiDevices) {
        midiDevicesComboBox.setModel(new DefaultComboBoxModel<MidiDevice.Info>(midiDevices));
        midiMessagesList.setModel(listModel);
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel infoSelectedDeviceLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox midiDevicesComboBox;
    private javax.swing.JLabel midiDevicesLabel;
    private javax.swing.JList midiMessagesList;
    private javax.swing.JPanel pianoKeysPanel;
    private javax.swing.JButton selectMidiDeviceButton;
    // End of variables declaration//GEN-END:variables

    private static enum MidiKeyEvent {
        KEY_PRESS_EVENT,
        KEY_RELEASE_EVENT
    }

    /**
    * The handler of the received messages
    * @author vylgin
    */
    public class MidiInputReceiver implements Receiver {
        
        private static final int SYSTEM_MIDI_SIGNAL = 248;
        private static final int NOTE_ON_MIDI_SIGNAL = 144;
        private static final int NOTE_OFF_MIDI_SIGNAL = 128;
        private String name;

        public MidiInputReceiver(String name) {
            this.name = name;
        }

        private void eventKeyEmit(int keyEvent, MidiKeyEvent keyPressEvent) {
            switch (keyPressEvent) {
                case KEY_PRESS_EVENT:
                    try {
                        Robot robot = new Robot();
                        robot.keyPress(keyEvent);
                        
                        System.out.println("Pressed key: " + KeyEvent.getKeyText(keyEvent));
                        listModel.addElement("Pressed key: " + KeyEvent.getKeyText(keyEvent));
                        midiMessagesList.repaint();
                        
                    } catch (AWTException e) {
//                        e.printStackTrace();
                    }
                    break;
                case KEY_RELEASE_EVENT:
                    try {
                        Robot robot = new Robot();
                        robot.keyRelease(keyEvent);
                        
                        System.out.println("Released key: " + KeyEvent.getKeyText(keyEvent));
                        listModel.addElement("Released key: " + KeyEvent.getKeyText(keyEvent));
                        midiMessagesList.repaint();
                    } catch (AWTException e) {
//                        e.printStackTrace();
                    }
                    break;
            }
        }

        @Override
        public void send(MidiMessage message, long timeStamp) {
            ShortMessage event = (ShortMessage) message;

            if (event.getStatus() != SYSTEM_MIDI_SIGNAL) {
                String midiMessage = "chanel = " + event.getChannel()
                        + ", command = " + event.getCommand()
                        + ", data1 = " + event.getData1()
                        + ", data2 = " + event.getData2()
                        + ", status = " + event.getStatus()
                        + ", length = " + event.getLength();

                System.out.println(midiMessage);
                
//                listModel.addElement(midiMessage);
                
//                int lastIndex = midiMessagesList.getModel().getSize() - 1;               
//                if (lastIndex >= 0) {
//                    midiMessagesList.ensureIndexIsVisible(lastIndex);
//                }
                
                if (listModel.getSize() >= 10) {
                    listModel.remove(0);
                }
                
                if (event.getStatus() == NOTE_ON_MIDI_SIGNAL) {
                    VirtualMidiKeyboard vmk = (VirtualMidiKeyboard) pianoKeysPanel;
                    vmk.backlightOnKey(event.getData1());
                }

                if (event.getStatus() == NOTE_OFF_MIDI_SIGNAL) {
                    VirtualMidiKeyboard vmk = (VirtualMidiKeyboard) pianoKeysPanel;
                    vmk.backlightOffKey(event.getData1());
                }
                
                //----------------------------------------
                
                if (event.getStatus() == NOTE_ON_MIDI_SIGNAL && event.getData1() == 73) {
                    eventKeyEmit(KeyEvent.VK_W, MidiKeyEvent.KEY_PRESS_EVENT);
                }

                if (event.getStatus() == NOTE_OFF_MIDI_SIGNAL && event.getData1() == 73) {
                    eventKeyEmit(KeyEvent.VK_W, MidiKeyEvent.KEY_RELEASE_EVENT);
                }
                
                //----------------------------------------
                
                if (event.getStatus() == NOTE_ON_MIDI_SIGNAL && event.getData1() == 72) {
                    eventKeyEmit(KeyEvent.VK_S, MidiKeyEvent.KEY_PRESS_EVENT);
                }

                if (event.getStatus() == NOTE_OFF_MIDI_SIGNAL && event.getData1() == 72) {
                    eventKeyEmit(KeyEvent.VK_S, MidiKeyEvent.KEY_RELEASE_EVENT);
                }
                
                //----------------------------------------
                
                if (event.getStatus() == NOTE_ON_MIDI_SIGNAL && event.getData1() == 71) {
                    eventKeyEmit(KeyEvent.VK_A, MidiKeyEvent.KEY_PRESS_EVENT);
                }

                if (event.getStatus() == NOTE_OFF_MIDI_SIGNAL && event.getData1() == 71) {
                    eventKeyEmit(KeyEvent.VK_A, MidiKeyEvent.KEY_RELEASE_EVENT);
                }
                
                //----------------------------------------
                
                if (event.getStatus() == NOTE_ON_MIDI_SIGNAL && event.getData1() == 74) {
                    eventKeyEmit(KeyEvent.VK_D, MidiKeyEvent.KEY_PRESS_EVENT);
                }

                if (event.getStatus() == NOTE_OFF_MIDI_SIGNAL && event.getData1() == 74) {
                    eventKeyEmit(KeyEvent.VK_D, MidiKeyEvent.KEY_RELEASE_EVENT);
                }
            }
        }

        @Override
        public void close() {
        }
    }
}
