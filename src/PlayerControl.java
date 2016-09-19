import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PlayerControl implements KeyListener{
	
	int PlayerXCoordinate;
	int PlayerYCoordinate;
	
	private final String movementKeys = "qweasdzxcj";
	
	HivoltsBoard board;
	
	//Create a new Player
	public PlayerControl(HivoltsBoard board) {
		this.board = board;
	}
	
	public void SetRandomPlayerPosition() {
		//Put player at random position on board
		do {
			PlayerXCoordinate = (int) (1 + (Math.random() * 10));	
			PlayerYCoordinate = (int) (1 + (Math.random() * 10));
		} while (board.isTaken(PlayerXCoordinate, PlayerYCoordinate));
	}
	
	public void jump() {
		do {
			PlayerXCoordinate = (int) (1 + (Math.random() * 10));	
			PlayerYCoordinate = (int) (1 + (Math.random() * 10));
		} while (board.isFence(PlayerXCoordinate, PlayerYCoordinate));
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		// Movement keys - only work if the player is alive
		if (movementKeys.indexOf(e.getKeyChar()) != -1 && board.playerAlive()) {
			//Q - Up 1, Left 1
			if (key == KeyEvent.VK_Q) {
				PlayerXCoordinate --;
				PlayerYCoordinate --;
			}
			
			//W or Up Key - Up 1
			else if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
				PlayerYCoordinate --;
			}
			
			//E - Up 1, Right 1
			else if (key == KeyEvent.VK_E) {
				PlayerXCoordinate ++;
				PlayerYCoordinate --;
			}
			
			//A or Left Key - Left 1
			else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
				PlayerXCoordinate --;
			}
			
			//S - Stay
			else if (key == KeyEvent.VK_S) {
				PlayerXCoordinate = PlayerXCoordinate;
				PlayerYCoordinate = PlayerYCoordinate;
			}
			
			//D or Right Key - Right 1
			else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
				PlayerXCoordinate ++;
			}
			
			//Z - Down 1, Left 1
			else if (key == KeyEvent.VK_Z) {
				PlayerXCoordinate --;
				PlayerYCoordinate ++;
			}
			
			//X or Down Key - Down 1
			else if (key == KeyEvent.VK_X || key == KeyEvent.VK_DOWN) {
				PlayerYCoordinate ++;
			}
			
			//C - Down 1, Right 1
			else if (key == KeyEvent.VK_C) {
				PlayerXCoordinate ++;
				PlayerYCoordinate ++;
			}
			
			//J - Jump to random position
			else if (key == KeyEvent.VK_J) {
				jump();
			}
		}
		
		//R - Reset the game
		else if (key == KeyEvent.VK_R) {
			board.init();
			return;
		}
		
		//ESC - Close the window
		else if (key == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else {
			// None of these keys have been pressed, so don't end the turn
			return;
		}
		
		// Call the board method that ends the turn
		board.keyPressed(e);

	}
	

	public int getPlayerXCoordinate() {
		return PlayerXCoordinate;
	}
	
	public void setPlayerXCoordinate(int NewX) {
		PlayerXCoordinate = NewX;
	}
	
	public int getPlayerYCoordinate() {
		return PlayerYCoordinate;
	}
	
	public void setPlayerYCoordinate(int NewY) {
		PlayerXCoordinate = NewY;
	}

	
	
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
