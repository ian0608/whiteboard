package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import netcode.Line;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetBoard extends JPanel {

	ConcurrentLinkedQueue<Line> QueueOfDeltas = new ConcurrentLinkedQueue<Line>();
    int x, y; // axis position for oval
    //JMenuBar Variables
    JMenuBar menuBar;
    JMenu file;
    JMenuItem newBoard;
    JMenuItem clearBoard;
    JMenuItem exitBoard;
    Font font = new Font("Arial", Font.ITALIC, 30);


    // CONSTRUCTOR
    public NetBoard() {
        // Window Properties
        JFrame frame = new JFrame();
        frame.setTitle("Network Whiteboard");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBackground(Color.CYAN);

        
        // JMenuBar
        menuBar = new JMenuBar();
        file = new JMenu("File");
        newBoard = new JMenuItem("New Board");
        clearBoard = new JMenuItem("Clear Board");
        exitBoard = new JMenuItem("Close Program");

        menuBar.add(file);
        file.add(newBoard);
        
        file.add(clearBoard);
        file.addSeparator();
        file.add(exitBoard);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g.setFont(font);
                g.drawString("White Board", 100, 200);

                g.setColor(Color.red);
               
                QueueLines(); //Queue Lines Objects for Testing to simulate new network deltas
                while(!QueueOfDeltas.isEmpty()){
                Line newLine = QueueOfDeltas.poll();
                g.drawLine(newLine.getX1(),newLine.getX2(),newLine.getY1(),newLine.getY2());
                }//end whle loop
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 400);
            }
        };
        

        frame.add(panel);

        frame.setJMenuBar(menuBar);

        frame.pack();
        // Display frame after all components added
        frame.setVisible(true);

        // default position for oval
        x = 150;
        y = 150;

        
  
    }//end NetBoard Class
    public Line drawMyLine(){
    int x1, x2, y1, y2;
    x1=10;
    x2=80;
    y1=30;
    y2=40;
  	  Line myLine = new Line(x1++, x2++, y1++,y2++);
  	  
  	  return myLine;
    }
    
    /**
     * This is a test method I've been using to queue line objects in a thread-safe queue
     */
    public void QueueLines(){
        
      	Line myLine = new Line(10, 30, 70,90);
      	Line myLine2 = new Line(100, 130, 270,290);
      	Line myLine3 = new Line(105, 100, 300,400);
      	
      	QueueOfDeltas.add(myLine);
      	QueueOfDeltas.add(myLine2);
      	QueueOfDeltas.add(myLine3);
      	 
        }
    
    
    // MAIN METHOD
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NetBoard();
            }
        });
    }
}
