
public class HivoltsBoard {
	
	private enum Status {
		LOSE, WIN;
	}
	
	// Tracks the location of all objects in the game
	String[][] board = new String[12][12];
	
	// Object that controls player behavior
	PlayerControl pc = new PlayerControl(this);
	
	// List of all mhos in the game
	Mho[] mhos = new Mho[12];
	
	// Instance of the hivolts applet
	Hivolts hivolts;
	
	public HivoltsBoard(Hivolts hivolts) {
		this.hivolts = hivolts;
		init();
	}
	
	/**
	 *  Tell the program if the game has been won or lost.
	 *  The applet then reacts accordingly.
	 */
	public void setStatus(Status status) {
		switch (status) {
		case LOSE:
			break;
		case WIN:
			break;
		}
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
		return !board.equals(" ") && !board.equals("+");
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
	
	public void keyPressed() {
		hivolts.getContentPane().removeKeyListener(pc);
		Thread.sleep(3000);
		mhosTurn();
	}
	
	// Creates and positions all objects on the board
	private void init() {
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
			while (!isTaken(randX = random(), randY = random())) {}
			board[randX][randY] = "F";
		}
		
		// Generate mho positions
		for (int m = 1; m <= 12; m++) {
			Mho mho = new Mho(this);
			board[mho.currentY][mho.currentX] = Integer.toHexString(m);
			mhos[m] = mho;
		}
		
		// Generate player position
		pc.setRandomPlayerPosition();
		board[pc.PlayerYPosition][pc.PlayerXPosition] = "+";
		
		// Start the game
		playerTurn();
	}
	
	private void playerTurn() {
		hivolts.getContentPane().addKeyListener(pc);
	}
	
	private void mhosTurn() {
		for (int i = 0; i < 12; i++) {
			// Current mho
			Mho m = mhos[i];
			
			// Starting position of mho
			int oldX = m.currentX, oldY = m.currentY;
			// Move the mho and erase its original position
			m.MhoMove(pc.PlayerXPosition, pc.PlayerYPosition, this);
			board[oldY][oldX] = " ";
			
			// Object in the mho's new position
			String s = board[m.currentY][m.currentX];
			if (s.equals("F")) {
				// Mho is RIP
				mhos[i] = null;
			} else {
				board[m.currentY][m.currentX] = Integer.toHexString(i + 1);
			}
			if (pc.PlayerXPosition == m.currentX && pc.PlayerYPosition == m.currentY) {
				// Player is RIP
				setStatus(Status.LOSE);
			}
		}
		playerTurn();
	}

	// Random integer from 1 to 11 (the range of valid positions for each object)
	private int random() {
		return 1 + (int) (Math.random() * 11);
	}
	
	// Prints out the state of the board
	private void print() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
}