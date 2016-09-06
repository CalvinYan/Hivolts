import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerControl implements KeyListener{
	
	int PlayerXCoordinate;
	int PlayerYCoordinate;
	
	public PlayerControl() {
		//Create a new Player
		
	}
	
	public void SetRandomPlayerPosition() {
		//Put player at random position on board
		
		PlayerXCoordinate = (int) (1 + (Math.random() * 10));
		
		PlayerYCoordinate = (int) (1 + (Math.random() * 10));
		
	}
	
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		//Q - Up 1, Left 1
		if (key == KeyEvent.VK_Q) {
			setPlayerXCoordinate(getPlayerXCoordinate() - 1);
			setPlayerYCoordinate(getPlayerYCoordinate() - 1);
		}
		
		//W or Up Key - Up 1
		if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
			setPlayerYCoordinate(getPlayerYCoordinate() - 1);
		}
		
		//E - Up 1, Right 1
		if (key == KeyEvent.VK_E) {
			setPlayerXCoordinate(getPlayerXCoordinate() + 1);
			setPlayerYCoordinate(getPlayerYCoordinate() - 1);
		}
		
		//A or Left Key - Left 1
		if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			setPlayerXCoordinate(getPlayerXCoordinate() - 1);
		}
		
		//S - Stay
		if (key == KeyEvent.VK_S) {
			setPlayerXCoordinate(getPlayerXCoordinate());
			setPlayerYCoordinate(getPlayerYCoordinate());
		}
		
		//D or Right Key - Right 1
		if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			setPlayerXCoordinate(getPlayerXCoordinate() + 1);
		}
		
		//Z - Down 1, Left 1
		if (key == KeyEvent.VK_Z) {
			setPlayerXCoordinate(getPlayerXCoordinate() - 1);
			setPlayerYCoordinate(getPlayerYCoordinate() + 1);
		}
		
		//X or Down Key - Down 1
		if (key == KeyEvent.VK_X || key == KeyEvent.VK_DOWN) {
			setPlayerYCoordinate(getPlayerYCoordinate() + 1);
		}
		
		//C - Down 1, Right 1
		if (key == KeyEvent.VK_C) {
			setPlayerXCoordinate(getPlayerXCoordinate() + 1);
			setPlayerYCoordinate(getPlayerYCoordinate() + 1);
		}
		
		//J - Jump to random position
		if (key == KeyEvent.VK_J) {
			SetRandomPlayerPosition();
		}

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
