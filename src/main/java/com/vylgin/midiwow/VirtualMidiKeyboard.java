package com.vylgin.midiwow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author vylgin
 */
public class VirtualMidiKeyboard extends JPanel{
    
    private JLabel[] pianoKeys = new JLabel[128];
    private final String whiteKeysName = "white";
    private final String blackKeysName = "black";

    public VirtualMidiKeyboard() {
        createKeys();
        setPreferredSize(new Dimension(1500, 140));
        
        for (JLabel label : pianoKeys) {
            label.addMouseListener(new keysMouseListener());
        }
    }    
    
    private void insertWhiteKey(int index, String name) {
//        JLabel key = new JLabel("<html>" + String.valueOf(i) + "<br>" + "D" + "</html>", SwingConstants.CENTER);
        JLabel key = new JLabel(name, SwingConstants.CENTER);
        key.setName(whiteKeysName);
        key.setToolTipText(name);
        key.setVerticalAlignment(SwingConstants.BOTTOM);
        key.setOpaque(true);
        key.setFont(new Font("Serif", Font.PLAIN, 10));
        key.setBorder(BorderFactory.createLineBorder(Color.black));
        key.setBackground(Color.WHITE);
        key.setLocation(index * 20, 0);
        key.setSize(20, 120);
        add(key);
        setComponentZOrder(key, index);
        pianoKeys[Integer.parseInt(name)] = key;
    }
    
    private void insertBlackKey(int index, String name) {
        JLabel key = new JLabel(name, SwingConstants.CENTER);
        key.setName(blackKeysName);
        key.setToolTipText(name);
        key.setVerticalAlignment(SwingConstants.BOTTOM);
        key.setOpaque(true);
        key.setFont(new Font("Serif", Font.PLAIN, 10));
        key.setBackground(Color.BLACK);
        key.setForeground(Color.WHITE);
        key.setLocation(index * 20 + 12, 0);
        key.setSize(16, 80);
        add(key);
        setComponentZOrder(key, 0);
        pianoKeys[Integer.parseInt(name)] = key;
    }
      
    private void createKeys() {        
        Vector<Integer> wKeys = new Vector<Integer>();
        wKeys.add(0); 
        wKeys.add(2);
        wKeys.add(4);
        wKeys.add(5);
        wKeys.add(7);
        wKeys.add(9);
        wKeys.add(11);
        
        Vector<Integer> bKeys = new Vector<Integer>();
        bKeys.add(1);
        bKeys.add(3);
        bKeys.add(6);
        bKeys.add(8);
        bKeys.add(10);
        
        for (int i = 0; i < 75; i++) {
            Integer firstElement = wKeys.firstElement();
            insertWhiteKey(i, String.valueOf(firstElement));
            wKeys.add(firstElement + 12);
            wKeys.remove(0);
        }

        for (int i = 0; i < 74; i++) {
            int j = i % 7;
            if (j == 2 || j == 6) {
                continue;
            }
            Integer firstElement = bKeys.firstElement();
            insertBlackKey(i, String.valueOf(firstElement));
            bKeys.add(firstElement + 12);
            bKeys.remove(0);
        }
    }
    
    public void backlightOnKey(int key) {
        pianoKeys[key].setBackground(Color.GREEN);
        repaint();
    }
    
    public void backlightOffKey(int key) {
        if (pianoKeys[key].getName().equals(whiteKeysName)) {
            pianoKeys[key].setBackground(Color.WHITE);
        } else if (pianoKeys[key].getName().equals(blackKeysName)) {
            pianoKeys[key].setBackground(Color.BLACK);
        }
        repaint();
    }
    
    public class keysMouseListener implements MouseListener{

        private Color keyColor;
        
          public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            label.setBackground(Color.RED);
            VirtualMidiKeyboard.this.repaint();
        }

        public void mousePressed(MouseEvent e) {
            
        }

        public void mouseReleased(MouseEvent e) {
            
        }

        public void mouseEntered(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            keyColor = label.getBackground();
            label.setBackground(Color.LIGHT_GRAY);
            VirtualMidiKeyboard.this.repaint();
        }

        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            label.setBackground(keyColor);
            VirtualMidiKeyboard.this.repaint();
        }
        
    }
}
