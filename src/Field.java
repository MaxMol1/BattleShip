
public class Field {
	
	private int xDimensions;
	private int yDimensions;
	private int numShips;
	private int[][] grid;
	
	public Field(int xDimensions, int yDimensions, int numShips) {
		this.xDimensions = xDimensions;
		this.yDimensions = yDimensions;
		this.numShips = numShips;
		grid = new int[xDimensions][yDimensions];
		for(int i = 0; i < xDimensions; i++) {
			for(int j = 0; j < yDimensions; j++) {
				grid[i][j] = 0;
			}
		}
	}
	
	public int getGrid(int i, int j) {
		return grid[i][j];
	}
	
	public void setGrid(int xCoor, int yCoor, int value) {
		grid[xCoor][yCoor] = value;
	}
	
	public void placeShips(int xCoor, int yCoor, char direction, int shipLength) {

		grid[xCoor][yCoor] = 1;
			
		if(direction == 'h') 
			for(int i = 1; i < shipLength; i++) 
				grid[xCoor+i][yCoor] = 1;
		else 
			for(int i = 1; i < shipLength; i++) 
				grid[xCoor][yCoor+i] = 1;
	}
	
	public boolean checkIfAvailable(int xCoor, int yCoor, char direction, int shipLength) {
		if(grid[xCoor][yCoor] == 1)
			return false;
		
		if(direction == 'h') 
			for(int i = 1; i < shipLength; i++) {
				if(grid[xCoor+i][yCoor] == 1) {
					return false;
				}
			}
		else 
			for(int i = 1; i < shipLength; i++) {
				if(grid[xCoor][yCoor+i] == 1) {
					return false;
				}
			}
		return true;
	}
	
	public String toString(boolean showShip) {
		String string;
		if(showShip)
			string = "\n\nYour board\n\n  A B C D E F G H";
		else
			string = "\n\nOpponent board\n\n  A B C D E F G H";
		char holder;
		for(int i = 0; i < xDimensions; i++) {
			string = string + "\n" + (i+1) + " ";
			for(int j = 0; j < yDimensions; j++) {
				if(grid[j][i] == 1 && showShip) 		
					holder = 'o';
				else if(grid[j][i] == 2)
					holder = 'X';
				else if(grid[j][i] == -1)
					holder = '*';
				else
					holder = '-';
				string = string + holder + " ";
			}
		}
		return string;
	}
	
}
