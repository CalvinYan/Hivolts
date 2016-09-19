import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class HivoltsBoard{
	
	private enum Status {
		LOSE, WIN;
	}
	
	Hivolts game;
	
	// Tracks the location of all objects in the game
	String[][] board = new String[12][12];
	
	// Object that controls player behavior
	PlayerControl pc = new PlayerControl(this);
	
	// List of all mhos in the game
	Mho[] mhos = new Mho[12];
	
	// Number of mhos still alive
	int mhosLeft = 12;
	
	// Instance of the hivolts applet
	Hivolts hivolts;
	
	// Is the player alive?
	private boolean alive = true;
	
	public HivoltsBoard(Hivolts game) {
		this.game = game;
		init();
	}
	
	/**
	 *  Tell the program if the game has been won or lost.
	 *  The applet then displays a message (or two messages if the player
	 *  jumped onto a mho) and resets the game.
	 */
	public void setStatus(Status status, boolean jumped) {
		print();
		if (jumped) System.out.println("You jumped onto a mho!");
		switch (status) {
		case LOSE:
			System.out.println("LOL FUCKING LOSER");
			alive = false;
			break;
		case WIN:
			System.out.println("WOW WHAT A TRYHARD");
			break;
		}
		System.out.println("Play again? Press R to restart, ESC to exit");
		game.addKeyListener(pc);
	}
	
	/**
	 * Is there already an object in this position?
	 * (The player doesn't count, because the mhos use isTaken() to determine
	 * where they can move. Mhos should be able to move onto the player.
	 * @param x - The x-coordinate of the cell to be checked
	 * @param y - The y-coordinate of the cell to be checked
	 * @return true if an object that is is at (x,y), false otherwise
	 */
	public boolean isTaken(int x, int y) {
		return !(board[y][x].equals(" ") || board[y][x].equals("+"));
	}
	
	/**
	 * Is there an electric fence in this position?
	 * @param x - The x-coordinate of the cell to be checked
	 * @param y - The y-coordinate of the cell to be checked
	 * @return true if a fence is at (x, y), false otherwise
	 */
	public boolean isFence(int x, int y) {
		return board[y][x].equals("F");
	}
	
	/**
	 * Is there a mho in this position?
	 * @param x - The x-coordinate of the cell to be checked
	 * @param y - The y-coordinate of the cell to be checked
	 * @return true if a mho is at (x, y), false otherwise
	 */
	public boolean isMho(int x, int y) {
		String s = board[y][x];
		return !s.equals(" ") && !s.equals("F") && !s.equals("+");
	}
	
	/**
	 * Retrieves the object at (x, y)
	 * @param x - The x-coordinate of the cell to be checked
	 * @param y - The y-coordinate of the cell to be checked
	 * @return The string
	 */
	public String getObject(int x, int y) {
		return board[y][x];
	}
	
	/**
	 * Is the player still alive?
	 * @return whether or not the player is alive
	 */
	public boolean playerAlive() { return alive; }
	
	public void keyPressed(KeyEvent e) {
		game.removeKeyListener(pc);
		board[pc.PlayerYCoordinate][pc.PlayerXCoordinate] = " ";
		if (isTaken(pc.PlayerXCoordinate, pc.PlayerYCoordinate)) {
			// Player moved onto a fence or mho
			if (e.getKeyChar() == 'j') {
				// Display a special message if the player jumps on a mho
				setStatus(Status.LOSE, true);
				return;
			}
			setStatus(Status.LOSE, false);
			return;
		}
		board[pc.PlayerYCoordinate][pc.PlayerXCoordinate] = "+";
		print();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// Player moves again after a jump
		if (e.getKeyChar() == 'j') {
			// Check if player jumped on a Mho
			playerTurn();
		} else {
			mhosTurn();
		}
	}
	
	// Creates and positions all objects on the board
	public void init() {
		// Remove key listener if game was reset
		game.removeKeyListener(pc);
		
		alive = true;
		
		mhosLeft = 12;
		
		// Create border fences and set inside cells to blank spaces
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				// If (i, j) is on the border
				if (i % 11 == 0 || j % 11 == 0) {
					board[i][j] = "F";
				} else board[i][j] = " ";
			}
		}
		
		// Generate inside fences
		for (int f = 0; f < 20; f++) {
			int randX, randY;
			do {
				randX = random();
				randY = random();
			}
			while (isFence(randX, randY));
			board[randY][randX] = "F";
		}
		
		// Generate mho positions
		for (int m = 1; m <= 12; m++) {
			Mho mho = new Mho(this);
			board[mho.currentY][mho.currentX] = Integer.toHexString(m);
			mhos[m - 1] = mho;
		}
		
		// Generate player position
		pc.SetRandomPlayerPosition();
		board[pc.PlayerYCoordinate][pc.PlayerXCoordinate] = "+";
		
		print();
		game.repaint();
		// Start the game
		playerTurn();
	}
	
	private void playerTurn() {
		game.addKeyListener(pc);
	}
	
	private void mhosTurn() {
		// Are there any mhos left?
		for (int i = 0; i < 12; i++) {
			// Current mho
			Mho m = mhos[i];
			
			if (m == null) continue;
			
			// Starting position of mho
			int oldX = m.currentX, oldY = m.currentY;
			// Move the mho and erase its original position
			m.MhoMove(pc.PlayerXCoordinate, pc.PlayerYCoordinate, this);
			board[oldY][oldX] = " ";
			
			// Object in the mho's new position
			String s = board[m.currentY][m.currentX];
			if (s.equals("F")) {
				// Mho is RIP
				mhos[i] = null;
				mhosLeft--;
			} else {
				board[m.currentY][m.currentX] = Integer.toHexString(i + 1);
			}
			if (pc.PlayerXCoordinate == m.currentX && pc.PlayerYCoordinate == m.currentY) {
				// Player is RIP
				setStatus(Status.LOSE, false);
				return;
			}
		}
		print();
		if (mhosLeft == 0){
			setStatus(Status.WIN, false);
			return;
		}
		playerTurn();
	}

	// Random integer from 1 to 11 (the range of valid positions for each object)
	private int random() {
		return 1 + (int) (Math.random() * 10);
	}
	
	// Prints out the state of the board
	private void print() {
		game.repaint();
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
}
