package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.SwingUtilities;

//import com.sun.corba.se.impl.orbutil.graph.Node;

import netcode.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetBoard extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Line> LineList =new ArrayList<Line>();
	List<Line> safeLineList = Collections.synchronizedList(LineList);
	ConcurrentLinkedQueue<Line> QueueOfDeltas = new ConcurrentLinkedQueue<Line>();
	
	String credits="CS 4251 Networking White Board Project\n\n"+
	"Developers: Stephen Pardue, Ian Stainbrook, James Ruiz\n\nProfessor: Mostafa Ammar";
	
    int x, y; // axis position for oval
    //JMenuBar Variables
    JMenuBar menuBar;
    JMenu file, start, help;
    JMenuItem newBoard;
    JMenuItem clearBoard;
    JMenuItem exitBoard;
    JMenuItem masterNode,clientNode;
    JMenuItem about;
    Node ultimateNode; //It's called ultimateNode, because NodeWorker.java abstracts it for me
    Font font = new Font("Arial", Font.ITALIC, 30);


    // CONSTRUCTOR
    public NetBoard() {
        // Window Properties
       final JFrame frame = new JFrame();
        frame.setTitle("Network Whiteboard");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBackground(Color.CYAN);

       
        
        // JMenuBar
        menuBar = new JMenuBar();
        //Add category
        file = new JMenu("File");
        //Add menu items to a category
       // newBoard = new JMenuItem("New Board");
        clearBoard = new JMenuItem("Clear Board");
        exitBoard = new JMenuItem("Exit Program");

        //My ghetto actionListeners
        exitBoard.setToolTipText("Exit Application");
        exitBoard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }

        });

        clearBoard.setToolTipText("Erases all drawn lines");
        clearBoard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
                safeLineList.clear();
                repaint();
//                SwingUtilities.invokeLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        new NetBoard();
//                    }
//                }); //end invokeLater
            
            }//end actionPerformed

        });
        

        start=new JMenu("Start");
        masterNode=new JMenuItem("Master");
        masterNode.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
            	ultimateNode = new Node("127.0.0.1",1100);
            	ultimateNode.bootstrap(true);
            }

        });
        clientNode=new JMenuItem("Client");
        clientNode.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
            	ultimateNode = new Node("127.0.0.1",1100);
            	ultimateNode.bootstrap(false);
            }

        });
 
        
        help=new JMenu("Help");
        about=new JMenuItem("About");
        about.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event) {
            	JOptionPane.showMessageDialog(frame,credits,"Credits",JOptionPane.PLAIN_MESSAGE);
            }

        });
        
        
        //I'm done creating menu bar junk
        menuBar.add(file);
       // file.add(newBoard);
        //after this, make a pop-up dialogue that asks if you want to be Host or Client
        file.add(clearBoard);
        file.addSeparator();
        file.add(exitBoard);
        
        menuBar.add(start);
        start.add(masterNode);
        start.add(clientNode);
        
        menuBar.add(help);
        help.add(about);

        JPanel panel = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                g.setFont(font);
                //g.drawString("Hold mouse in one position and release in another", 100, 200);
//                g.drawString("Hold mouse in one position", 25, 50);
//                g.drawString("and release in another", 25, 150);
                g.setColor(Color.red);
               
               // QueueLines(); //I queue Line objects into thread-safe queue
                
              //Stephen! This is where I plan checking the networkqueue for new deltas and then I would
              //add them to my safeLineList
                for(Line newLine:safeLineList){
                
                g.drawLine(newLine.getX1(),newLine.getX2(),newLine.getY1(),newLine.getY2());
                }//end while loop
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(400, 400);
            }
        };
        
      
       //Using MouseListeners Here
        panel.addMouseListener(new MyMouseListener());
        //addMouseListener(new MyMouseListener());
        
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
     * This is a test method I've been using to queue line objects in a thread-safe ArrayList
     */
    public void QueueLines(){
        
      	Line myLine = new Line(10, 30, 70,90);
      	Line myLine2 = new Line(100, 130, 270,290);
      	Line myLine3 = new Line(105, 100, 300,400);
      	
//      	QueueOfDeltas.add(myLine);
//      	QueueOfDeltas.add(myLine2);
//      	QueueOfDeltas.add(myLine3);
      	safeLineList.add(myLine);
      	safeLineList.add(myLine2);
      	safeLineList.add(myLine3);
      	 
        }
    
   /**
    * 
    * Lines are created by holding down the mouse in one position and releasing in another position
    *
    */
    class MyMouseListener extends MouseAdapter {
    	private int deltaX1, deltaY1, deltaX2, deltaY2;
    	
    	

    	@Override
    	public void mousePressed(MouseEvent e) {
    		deltaX1=e.getX();
    		deltaY1=e.getY();
    		
    	}

    	@Override
    	public void mouseReleased(MouseEvent e) {
    		deltaX2=e.getX();
    		deltaY2=e.getY();
    		Line deltaLine = new Line(deltaX1, deltaY1, deltaX2, deltaY2);
    		System.out.println("X1: "+deltaX1+ " Y1: "+deltaY1+" X2: "+deltaX2+" Y2: "+deltaY2);
    		safeLineList.add(deltaLine); //for use by local client only
    		//Stephen! This is where I plan on adding the new line object to the network queue!
    		
    		
    		
    		//Queue a different datastructure that the new Line object to be sent
    		//QueueOfDeltas.add(deltaLine); //Queue this line for the local client
    		repaint();
    		
//    		SwingUtilities.invokeLater(new Runnable() {
//                @Override
//                public void run() {
//                    new NetBoard();
//                }
//            });//end invokeLater
        
    		
    		
    	}//end mouseReleased

    	
    }  //end MouseListener
    
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
