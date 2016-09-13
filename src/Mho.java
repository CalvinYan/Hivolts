import java.awt.*;
import java.math.*;

public class Mho {
	
	public int currentX = 0, currentY = 0;
	
	public Mho(HivoltsBoard board) {
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
			// Horizontal and vertical distance from player to mho
			int xDist = playerX - currentX, yDist = playerY - currentY;
			// Optimal diagonal direction to move in (both values are either 1 or -1)
			int xDirection = xDist / Math.abs(xDist), yDirection = yDist / Math.abs(yDist);
			// Coordinates of new diagonal position
			int[] diagPos = {currentX + xDirection, currentY + yDirection};
			/* If diagonal movement is impossible, move horizontally if
			 * horizontal distance is greater than or equal to vertical distance,
			 * and vertically otherwise.
			 */
			int[] orthogonal = new int[2];
			if (Math.abs(xDist) >= Math.abs(yDist)) {
				orthogonal[0] = xDirection;
			} else {
				orthogonal[1] = yDirection;
			}
			// Coordinates of new orthogonal position
			int[] orthoPos = {currentX + orthogonal[0], currentY + orthogonal[1]};
			
			if (!board.isTaken(diagPos[0], diagPos[1])) {
				// Move diagonally if landing on an empty space
				currentX = diagPos[0];
				currentY = diagPos[1];
			} else if (!board.isTaken(orthoPos[0], orthoPos[1])) {
				// Move orthogonally if landing on an empty space
				currentX = orthoPos[0];
				currentY = orthoPos[1];				
			} else if (!board.isMho(diagPos[0], diagPos[1])) {
				// Move diagonally if landing on a fence
				currentX = diagPos[0];
				currentY = diagPos[1];
			} else if (!board.isMho(orthoPos[0], orthoPos[1])) {
				// Move orthogonally if landing on a fence
				currentX = orthoPos[0];
				currentY = orthoPos[1];	
			}
		}
	}
}