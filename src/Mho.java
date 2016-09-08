import java.awt.*;
import java.math.*;

public class Mho {
	
	public int currentX = 0, currentY = 0;
	
	public void Mho(HivoltsBoard board) {
		//Randomizes Mho starting position
		currentX = (int) (Math.random() * 11 + 1);
		currentY = (int) (Math.random() * 11 + 1);
		
		//If Mho has the same starting position as Player, randomize again
		while(board.isTaken(currentX, currentY)) {
			currentX = (int) (Math.random() * 11 + 1);
			currentY = (int) (Math.random() * 11 + 1);
		}

	}

	public void MhoMove(int playerX, int playerY, HivoltsBoard board) {
		
		//If Player is directly vertical or horizontal to Mho, move straight towards player
		if(currentX == playerX) {
			if(currentY > playerY) {
				currentY--;
			} else {
				currentY++;
			}
		} 
		else if(currentY == playerY) {
			if(currentX > playerX) {
				currentX--;
			} else {
				currentX++;
			}
		} 
		//If Mho is not directly vertical or horizontal to Mho
		else {
			//If Mho is down and to the right of the player, move up and to the left
			if(currentX > playerX && currentY > playerY && (board.isTaken(currentX-1, currentY-1)) == false) {
				currentX--;
				currentY--;
			}
			//If Mho is up and to the right of the player, move down and to the left
			else if(currentX > playerX && currentY < playerY && (board.isTaken(currentX-1, currentY+1)) == false) {
				currentX--;
				currentY++;
			}
			//If Mho is down and to the left of the player, move up and to the right
			else if(currentX < playerX && currentY > playerY && (board.isTaken(currentX+1, currentY-1)) == false) {
				currentX++;
				currentY--;
			}
			//If Mho is up and to the left of the player, move down and to the right
			else if(currentX <  playerX && currentY < playerY && (board.isTaken(currentX+1, currentY+1)) == false) {
				currentX++;
				currentY++;
			}
			/* If Mho is down and to the right of the player, and the diagonal space is 
			 * taken, move horizontally or vertically based on farther distance.
			 */
			else if(currentX > playerX && currentY > playerY) {
				if(currentX-playerX < currentY-playerY && (board.isTaken(currentX-1, currentY)) == false) {
					currentY--;
				} else if(currentX-playerX > currentY-playerY && (board.isTaken(currentX, currentY-1)) == false) {
					currentX--;
				}
			}
			/* If Mho is down and to the left of the player, and the diagonal space 
			 * is taken, move horizontally or vertically based on farther distance.
			 */
			else if(currentX < playerX && currentY > playerY) {
				if(playerX-currentX < currentY-playerY && (board.isTaken(currentX, currentY-1)) == false) {
					currentY--;
				} else if(playerX-currentX > currentY-playerY && (board.isTaken(currentX+1, currentY)) == false) {
					currentX++;
				}
			}
			/* If Mho is up and to the right of the player, and the diagonal space is 
			 * taken, move horizontally or vertically based on farther distance.
			 */
			else if(currentX > playerX && currentY < playerY) {
				if(currentX-playerX < playerY-currentY && (board.isTaken(currentX, currentY+1)) == false) {
					currentY++;
				} else if(currentX-playerX > playerY-currentY && (board.isTaken(currentX-1, currentY)) == false) {
					currentX--;
				}
			}
			/* If Mho is up and to the left of the player, and the diagonal space
			 * is taken, move horizontally or vertically based on farther distance.
			 */
			else if(currentX < playerX && currentY < playerY) {
				if(playerX-currentX < playerY-currentY && (board.isTaken(currentX, currentY+1)) == false) {
					currentY++;
				} else if(playerX-currentX > playerY-currentY && (board.isTaken(currentX+1, currentY)) == false) {
					currentX++;
				}
			}
			
			
			
			/* If all above options are not executed, the code will attempt to have the
			*  the mho move onto an electric fence in the same order of priority.
			*/
			
			
			
			// If Mho is down and to the right of the player, move up and to the left
			else if(currentX > playerX && currentY > playerY && (board.isMho(currentX-1, currentY-1)) == false) {
				currentX--;
				currentY--;
			}
			//If Mho is up and to the right of the player, move down and to the left
			else if(currentX > playerX && currentY < playerY && (board.isMho(currentX-1, currentY+1)) == false) {
				currentX--;
				currentY++;
			}
			//If Mho is down and to the left of the player, move up and to the right
			else if(currentX < playerX && currentY > playerY && (board.isMho(currentX+1, currentY-1)) == false) {
				currentX++;
				currentY--;
			}
			//If Mho is up and to the left of the player, move down and to the right
			else if(currentX <  playerX && currentY < playerY && (board.isMho(currentX+1, currentY+1)) == false) {
				currentX++;
				currentY++;
			}
			/* If Mho is down and to the right of the player, and the diagonal space is 
			 * taken, move horizontally or vertically based on farther distance.
			 */
			else if(currentX > playerX && currentY > playerY) {
				if(currentX-playerX < currentY-playerY && (board.isMho(currentX-1, currentY)) == false) {
					currentY--;
				} else if(currentX-playerX > currentY-playerY && (board.isMho(currentX, currentY-1)) == false) {
					currentX--;
				}
			}
			/* If Mho is down and to the left of the player, and the diagonal space 
			 * is taken, move horizontally or vertically based on farther distance.
			 */
			else if(currentX < playerX && currentY > playerY) {
				if(playerX-currentX < currentY-playerY && (board.isMho(currentX, currentY-1)) == false) {
					currentY--;
				} else if(playerX-currentX > currentY-playerY && (board.isMho(currentX+1, currentY)) == false) {
					currentX++;
				}
			}
			/* If Mho is up and to the right of the player, and the diagonal space is 
			 * taken, move horizontally or vertically based on farther distance.
			 */
			else if(currentX > playerX && currentY < playerY) {
				if(currentX-playerX < playerY-currentY && (board.isMho(currentX, currentY+1)) == false) {
					currentY++;
				} else if(currentX-playerX > playerY-currentY && (board.isMho(currentX-1, currentY)) == false) {
					currentX--;
				}
			}
			/* If Mho is up and to the left of the player, and the diagonal space
			 * is taken, move horizontally or vertically based on farther distance.
			 */
			else if(currentX < playerX && currentY < playerY) {
				if(playerX-currentX < playerY-currentY && (board.isMho(currentX, currentY+1)) == false) {
					currentY++;
				} else if(playerX-currentX > playerY-currentY && (board.isMho(currentX+1, currentY)) == false) {
					currentX++;
				}
			}
			/* If Mho cannot move towards player due to being blocked by other Mho,
			 * the mho will not move.
			 */
			else {
				;
			}
		}
		
		
		
		
	}
}