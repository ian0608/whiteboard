package gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

/**
 * Main launcher to start graphical user interface, run this file to see GUI
 * @authors Stephen Pardue, Ian Stainbrook, and James Ruiz
 * @date March 19, 2014
 */

public class Main extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L; //get rid of warning with default serial ID
	private JLabel statusbar;

    public Main() {
        
        initUI();
    }

    private void initUI() {

        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenu view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);

        JCheckBoxMenuItem sbar = new JCheckBoxMenuItem("Show StatuBar");
        sbar.setState(true);

        sbar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
              if (statusbar.isVisible()) {
                  statusbar.setVisible(false);
              } else {
                  statusbar.setVisible(true);
              }
            }

        });

        view.add(sbar);

        menubar.add(file);
        menubar.add(view);

        setJMenuBar(menubar);

        statusbar = new JLabel(" Statusbar");
        statusbar.setBorder(BorderFactory.createEtchedBorder(
                EtchedBorder.RAISED));
        add(statusbar, BorderLayout.SOUTH);

        setTitle("CheckBox menu item");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}