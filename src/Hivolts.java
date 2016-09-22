import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class Hivolts extends JFrame {
        
	private HivoltsBoard hb;
	private String[][] board;
    private BufferedImage player, mho, fence;
    
    private static final int LENGTH = 90;
    
    public Hivolts() {
        init();
    }
    
    public void init() {
        setResizable(false);
        setPreferredSize(new Dimension(LENGTH * 12, LENGTH * 12));
        setBackground(Color.WHITE);
        pack();
        hb = new HivoltsBoard(this);
        setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void paint(Graphics g) {
        board = hb.board;
        int xPos = 0, yPos = 0;
        try {
        	player = ImageIO.read(new File("trump.jpg"));
            mho = ImageIO.read(new File("clinton.jpg"));
            fence = ImageIO.read(new File("troll.jpg"));
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