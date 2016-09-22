import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
public class Hivolts extends JFrame {
        
	private HivoltsBoard hb;
	private String[][] board;
	
	private JPanel panel = new JPanel();
	
	private JLabel[][] images = new JLabel[12][12];
	
    private ImageIcon player, fence, blank, mho;
    
    private static final int LENGTH = 90;
    
    public Hivolts() {
        init();
    }
    
    public void init() {
    	loadImages();
        initDisplay();
        hb = new HivoltsBoard(this);
        pack();
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        updateBoard();
        System.out.println(getSize());
    }
    
    /*public void updateBoard() {
    	Graphics g = getGraphics();
    	for (int[] o : oldMhoPos) {
    		int xPos = o[0], yPos = o[1];
    		int xCoord = LENGTH * xPos, yCoord = LENGTH * yPos;
    		g.clearRect(xCoord, yCoord, LENGTH, LENGTH);
    	}
    	for (int[] n : newMhoPos) {
    		int xPos = n[0], yPos = n[1];
    		int xCoord = LENGTH * xPos, yCoord = LENGTH * yPos;
    		g.drawImage(mho, xCoord, yCoord, LENGTH, LENGTH, null);
    	}
    	int oldPlayerX = oldPlayerPos[0]. oldPlayerY = oldPlayerPos
    }*/
    
    public void paint(Graphics g) {
    	System.out.println("Begin");
        /*board = hb.board;
        int xPos = 0, yPos = 0;
        try {
        	
        } catch (IOException e) {
            System.out.println("ABORT ABORT ABORT");
            e.printStackTrace();
        }       
        
        for(yPos = 0; yPos < 12; yPos++) {
            for(xPos = 0; xPos < 12; xPos++) {
            	String object = board[yPos][xPos];
            	int xCoord = LENGTH * xPos, yCoord = LENGTH * yPos;
                if(object.equals("+")) {
                    g.drawImage(player, xCoord, yCoord, LENGTH, LENGTH, null);
                } else if(object.equals("F")) {
                	g.drawImage(fence, xCoord, yCoord, LENGTH, LENGTH, null);
                } else if(object.equals(" ")) {
                	g.clearRect(xCoord, yCoord, LENGTH, LENGTH);
                } else {
                	g.drawImage(mho, xCoord, yCoord, LENGTH, LENGTH, null);
                }
            }
        }*/
    }
    
    public void updateBoard() {
    	if (hb == null) return;
    	System.out.println(this.getLayout());
    	board = hb.board;
    	for (int i = 0; i < 12; i++) {
    		for (int j = 0; j < 12; j++) {
    			if (images[i][j] == null) {
    				images[i][j] = new JLabel("What");
    			}
    			JLabel image = images[i][j];
    			//image.setForeground(Color.BLACK);
    			//System.out.println(image.getForeground());
    			switch(board[i][j]) {
    			case ("+"):
    				image.setIcon(player);
    				break;
    			case ("F"):
    				image.setIcon(fence);
    				break;
    			case (" "):
    				image.setIcon(blank);
    				break;
    			default:
    				image.setIcon(mho);
    				break;
    			}
    			image.setSize(LENGTH, LENGTH);
    			//System.out.println(image.getAlignmentX());
    			add(image);
    		}
    	}
    	pack();
    	validate();
    	setVisible(true);
    }
    
    private void loadImages() {
    	System.out.println(System.getProperty("java.class.path"));
    	player = new ImageIcon("trump.jpg");
    	fence = new ImageIcon("troll.jpg");
    	blank = new ImageIcon("blank.jpg");
        mho = new ImageIcon("clinton.jpg");
    }
    
    private void initDisplay() {
    	setResizable(false);
        setPreferredSize(new Dimension(LENGTH * 12, LENGTH * 12));
        //setBackground(Color.BLACK);
        setLayout(new GridLayout(12, 12));
    }
    
    
    
}