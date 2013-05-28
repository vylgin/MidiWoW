/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vylgin.midiwow;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * Class for binding midi keyboard keys with PC keyboard keys
 * @author vylgin
 */
public class BindKeys extends javax.swing.JFrame {
    private int number;
    private ArrayList<Integer> newKeysList = new ArrayList<Integer>();
    private VirtualMidiKeyboard virtualMidiKeyboard;

    /**
    * Creates new form BindKeys
    */
    public BindKeys(int number, VirtualMidiKeyboard vmk) {
        initComponents();
        setNumber(number);
        virtualMidiKeyboard = vmk;
        setTitle("Note: " + String.valueOf(this.number));
        showKeys();
        
        newKeysLabel.setFocusable(true);
        newKeysLabel.setFocusTraversalKeysEnabled(false);
        
        currentKeysTextArea.setFocusable(false);
        newKeysTextArea.setFocusable(false);
        okButton.setFocusable(false);
        clearButton.setFocusable(false);
        emptyNoteButton.setFocusable(false);
        cancelButton.setFocusable(false);
        
        currentKeysLabel.setFocusTraversalKeysEnabled(false);
        newKeysTextArea.setFocusTraversalKeysEnabled(false);
        okButton.setFocusTraversalKeysEnabled(false);
        clearButton.setFocusTraversalKeysEnabled(false);
        emptyNoteButton.setFocusTraversalKeysEnabled(false);
        cancelButton.setFocusTraversalKeysEnabled(false);
    }
    
    private void setNumber(int number) {
        this.number = number;
    }
    
    private void showKeys() {
        currentKeysTextArea.setText(getNamesOfGameKeys());
    }
       
    private String getNamesOfGameKeys() {
        GameKeys gameKeys = GameKeys.getInstance();
        ArrayList<Integer> list = gameKeys.getKeyboardKeys(number);
        String result = "";

        for (int j = 0; j < list.size(); j++) {
            if (j == list.size() - 1 || list.size() == 1) {
                result += (list.get(j) == GameKeys.getEmptyNote())
                        ? GameKeys.emptyKeyText
                        : String.valueOf(KeyEvent.getKeyText(list.get(j)));
            } else {
                result += (list.get(j) == GameKeys.getEmptyNote())
                        ? GameKeys.emptyKeyText + "\n"
                        : String.valueOf(KeyEvent.getKeyText(list.get(j))) + "\n";
            }
        }

        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        currentKeysLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        emptyNoteButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        currentKeysTextArea = new javax.swing.JTextArea();
        newKeysLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        newKeysTextArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        currentKeysLabel.setText("Current Keys:");

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        emptyNoteButton.setText("Empty Note");
        emptyNoteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emptyNoteButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        currentKeysTextArea.setEditable(false);
        currentKeysTextArea.setColumns(20);
        currentKeysTextArea.setRows(7);
        jScrollPane1.setViewportView(currentKeysTextArea);

        newKeysLabel.setText("New Keys:");
        newKeysLabel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newKeysLabelKeyReleased(evt);
            }
        });

        newKeysTextArea.setEditable(false);
        newKeysTextArea.setColumns(20);
        newKeysTextArea.setRows(7);
        jScrollPane2.setViewportView(newKeysTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newKeysLabel)
                    .addComponent(currentKeysLabel)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(emptyNoteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentKeysLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newKeysLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton)
                    .addComponent(emptyNoteButton)
                    .addComponent(clearButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        if (!newKeysList.isEmpty()) {
            GameKeys gameKeys = GameKeys.getInstance();
            gameKeys.setKeyboardKeys(number, newKeysList);
            virtualMidiKeyboard.backlightNotEmptyKeys();
        }
        
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void emptyNoteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emptyNoteButtonActionPerformed
        newKeysTextArea.setText("");
        
        GameKeys gameKeys = GameKeys.getInstance();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(gameKeys.getEmptyNote());
        gameKeys.setKeyboardKeys(number, list);
        
        virtualMidiKeyboard.backlightNotEmptyKeys();
        
        WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    }//GEN-LAST:event_emptyNoteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        newKeysTextArea.setText("");
        newKeysList = new ArrayList<Integer>();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void newKeysLabelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newKeysLabelKeyReleased
        int keyCode = evt.getKeyCode();
        String keyName = KeyEvent.getKeyText(keyCode);
        newKeysTextArea.append(keyName + "\n");

        newKeysList.add(keyCode);
        
    }//GEN-LAST:event_newKeysLabelKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel currentKeysLabel;
    private javax.swing.JTextArea currentKeysTextArea;
    private javax.swing.JButton emptyNoteButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel newKeysLabel;
    private javax.swing.JTextArea newKeysTextArea;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

}