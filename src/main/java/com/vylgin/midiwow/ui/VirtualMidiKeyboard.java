package com.vylgin.midiwow.ui;

import com.vylgin.midiwow.GameKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Panel virtual piano keyboard
 * @author vylgin
 */
public class VirtualMidiKeyboard extends JPanel{
    private static Logger log = LoggerFactory.getLogger(VirtualMidiKeyboard.class.getName());
    private JLabel[] pianoKeys = new JLabel[128];
    private static final String whiteKeysName = "white";
    private static final String blackKeysName = "black";
    private static final Color backgroindKeysBeforePressed[] = new Color[128];
    private static final Color backgroundWhiteKeyUsed = new Color(193, 205, 205);
    private static final Color backgroundBlackKeyUsed = new Color(108, 123, 139);
    
    public VirtualMidiKeyboard() {
        log.info("Creating Virtual Midi Keyboard panel.");
        createKeys();
        setPreferredSize(new Dimension(1500, 140));
        
        for (JLabel label : pianoKeys) {
            label.addMouseListener(new keysMouseListener());
        }
        
        setToolTipKeys();
        log.info("Virtual Midi Keyboard panel created.");
    }
    
    /**
     * Method changes the background color of the key
     * @param key key of midi keyboard
     */
    public void backlightOnKey(int key) {
        backgroindKeysBeforePressed[key] = pianoKeys[key].getBackground();
        pianoKeys[key].setBackground(Color.GREEN);
        repaint();
        log.debug("Backlight ON note number: {}.", key);
    }
    
    /**
     * Method changes the background color of the key on the color that was
     * @param key key of midi keyboard
     */
    public void backlightOffKey(int key) {
         if (pianoKeys[key].getName().equals(whiteKeysName)) {
            pianoKeys[key].setBackground(Color.WHITE);
            pianoKeys[key].setBorder(BorderFactory.createLineBorder(Color.black));
        } else if (pianoKeys[key].getName().equals(blackKeysName)) {
            pianoKeys[key].setBackground(Color.BLACK);
            pianoKeys[key].setBorder(BorderFactory.createLineBorder(Color.black));
        }

        if (backgroindKeysBeforePressed[key] == backgroundWhiteKeyUsed 
                || backgroindKeysBeforePressed[key] == backgroundBlackKeyUsed) {
            if (pianoKeys[key].getName().equals(whiteKeysName)) {
                pianoKeys[key].setBackground(backgroundWhiteKeyUsed);
            } else if (pianoKeys[key].getName().equals(blackKeysName)) {
                pianoKeys[key].setBackground(backgroundBlackKeyUsed);
            }
            backgroindKeysBeforePressed[key] = pianoKeys[key].getBackground();
            pianoKeys[key].setBorder(BorderFactory.createLineBorder(Color.black));
        } 
        
        repaint();
        log.debug("Backlight OFF note number: {}.", key);
    }
    
    /**
     * Method install the background color for keys that is used
     */
    public void backlightNotEmptyKeys() {
        log.debug("Backlighting not empty keys.");
        GameKeys gameKeys = GameKeys.getInstance();
        for (int i = 0; i < pianoKeys.length; i++) {
            ArrayList<Integer> list = gameKeys.getKeyboardKeys(i);
            if (!list.get(0).equals(GameKeys.EMPTY_NOTE)) {
                if (pianoKeys[i].getName().equals(whiteKeysName)) {
                    pianoKeys[i].setBackground(backgroundWhiteKeyUsed);
                } else if (pianoKeys[i].getName().equals(blackKeysName)) {
                    pianoKeys[i].setBackground(backgroundBlackKeyUsed);
                }
                pianoKeys[i].setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }
        repaint();
        setToolTipKeys();
        log.debug("Backlighted not empty keys.");
    }
    
    /**
     * Clear all background except for the initial
     */
    public void clearBacklight() {
        log.debug("Clearning backlight color.");
        for (JLabel pianoKey : pianoKeys) {
            if (pianoKey.getName().equals(whiteKeysName)) {
                pianoKey.setBackground(Color.WHITE);
                pianoKey.setBorder(BorderFactory.createLineBorder(Color.black));
            } else if (pianoKey.getName().equals(blackKeysName)) {
                pianoKey.setBackground(Color.BLACK);
                pianoKey.setBorder(BorderFactory.createLineBorder(Color.black));
            }
        }
        repaint();
        log.debug("Cleared backlight color.");
    }
    
    private void insertWhiteKey(int index, String name) {
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
        key.setFont(new Font("Serif", Font.PLAIN, 8));
        key.setBackground(Color.BLACK);
        key.setForeground(Color.WHITE);
        key.setLocation(index * 20 + 12, 0);
        key.setSize(16, 80);
        add(key);
        setComponentZOrder(key, 0);
        pianoKeys[Integer.parseInt(name)] = key;
    }
      
    private void createKeys() { 
        log.debug("Creating keys.");
        ArrayList<Integer> wKeys = new ArrayList<>();
        wKeys.add(0); 
        wKeys.add(2);
        wKeys.add(4);
        wKeys.add(5);
        wKeys.add(7);
        wKeys.add(9);
        wKeys.add(11);
        
        ArrayList<Integer> bKeys = new ArrayList<>();
        bKeys.add(1);
        bKeys.add(3);
        bKeys.add(6);
        bKeys.add(8);
        bKeys.add(10);
        
        for (int i = 0; i < 75; i++) {
            Integer firstElement = wKeys.get(0);
            insertWhiteKey(i, String.valueOf(firstElement));
            wKeys.add(firstElement + 12);
            wKeys.remove(0);
        }

        for (int i = 0; i < 74; i++) {
            int j = i % 7;
            if (j == 2 || j == 6) {
                continue;
            }
            Integer firstElement = bKeys.get(0);
            insertBlackKey(i, String.valueOf(firstElement));
            bKeys.add(firstElement + 12);
            bKeys.remove(0);
        }
        log.debug("Keys created.");
    }
    
    private void setToolTipKeys() {
        GameKeys gameKeys = GameKeys.getInstance();
        
        for (int i = 0; i < pianoKeys.length; i++) {
            ArrayList<Integer> list = gameKeys.getKeyboardKeys(i);
            String result = "";

            for (int j = 0; j < list.size(); j++) {
                if (j == list.size() - 1 || list.size() == 1) {
                    result += (list.get(j) == GameKeys.EMPTY_NOTE)
                            ? GameKeys.EMPTY_KEY_TEXT
                            : String.valueOf(KeyEvent.getKeyText(list.get(j)));
                } else {
                    result += (list.get(j) == GameKeys.EMPTY_NOTE)
                            ? GameKeys.EMPTY_KEY_TEXT + " + "
                            : String.valueOf(KeyEvent.getKeyText(list.get(j))) + " + ";
                }
            }
            
            pianoKeys[i].setToolTipText(result);
        }
    }
    
    /**
     * Mouse listener for every virtual piano key
     */
    public class keysMouseListener implements MouseListener{
        private Color keyColor;
        
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            label.setBackground(Color.RED);
            VirtualMidiKeyboard.this.repaint();
            
            log.debug("Mouse clicked on \"{}\" midi key", label.getText());
            
            BindKeys bind = new BindKeys(Integer.valueOf(label.getText()), VirtualMidiKeyboard.this);
            Point position = getParent().getLocationOnScreen();
            position = new Point(position.x, position.y + getParent().getHeight() + 20);
            bind.setLocation(position);
            bind.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            bind.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            keyColor = label.getBackground();
            label.setBackground(Color.LIGHT_GRAY);
            VirtualMidiKeyboard.this.repaint();
            MainWindow topFrame = (MainWindow) getTopLevelAncestor();
            topFrame.setStatusBarText("Midi key number: " + label.getText() + ", PC keyboard keys: " + label.getToolTipText() + "");
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JLabel label = (JLabel) e.getSource();
            label.setBackground(keyColor);
            VirtualMidiKeyboard.this.repaint();
            MainWindow topFrame = (MainWindow) getTopLevelAncestor();
            topFrame.setStatusBarText(topFrame.getReadyStatusBarText());
        }
    }
}
