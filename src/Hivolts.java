import javax.swing.JFrame;
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
	
    private JPanel panel;
    private BufferedImage player, mho, fence;
    
    private static final int LENGTH = 90;
    
    public Hivolts() {
        init();
    }
    
    public void init() {
        //add(panel);
        setResizable(false);
        setPreferredSize(new Dimension(LENGTH * 12, LENGTH * 12));
        setBackground(Color.WHITE);
        pack();
        hb = new HivoltsBoard(this);
        setVisible(true);
    }
    
    public void updateBoard(int[][] oldMhoPos, int[][] newMhoPos, int[] oldPlayerPos, int[] newPlayerPos) {
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
    }
    
    public void paint(Graphics g) {
    	System.out.println("Begin");
    	panel = new JPanel();
        board = hb.board;
        int xPos = 0, yPos = 0;
        try {
        	player = ImageIO.read(new File("trump player.jpg"));
            mho = ImageIO.read(new File("clinton mho.jpg"));
            fence = ImageIO.read(new File("troll wall.jpg"));
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
        }
        
    }
}