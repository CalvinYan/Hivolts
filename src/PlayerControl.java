import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class PlayerControl implements KeyListener{
	
	int PlayerXCoordinate;
	int PlayerYCoordinate;
	
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
		
		//Q - Up 1, Left 1
		if (key == KeyEvent.VK_Q) {
			PlayerXCoordinate --;
			PlayerYCoordinate --;
		}
		
		//W or Up Key - Up 1
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			PlayerYCoordinate --;
		}
		
		//E - Up 1, Right 1
		if (key == KeyEvent.VK_E) {
			PlayerXCoordinate ++;
			PlayerYCoordinate --;
		}
		
		//A or Left Key - Left 1
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			PlayerXCoordinate --;
		}
		
		//S - Stay
		if (key == KeyEvent.VK_S) {
			PlayerXCoordinate = PlayerXCoordinate;
			PlayerYCoordinate = PlayerYCoordinate;
		}
		
		//D or Right Key - Right 1
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			PlayerXCoordinate ++;
		}
		
		//Z - Down 1, Left 1
		if (key == KeyEvent.VK_Z) {
			PlayerXCoordinate --;
			PlayerYCoordinate ++;
		}
		
		//X or Down Key - Down 1
		if (key == KeyEvent.VK_X || key == KeyEvent.VK_DOWN) {
			PlayerYCoordinate ++;
		}
		
		//C - Down 1, Right 1
		if (key == KeyEvent.VK_C) {
			PlayerXCoordinate ++;
			PlayerYCoordinate ++;
		}
		
		//J - Jump to random position
		if (key == KeyEvent.VK_J) {
			SetRandomPlayerPosition();
		}
		
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
