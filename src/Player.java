
public class Player {
	
	private Field board;
	
	public Player(int xDim, int yDim, int ships) {
		board = new Field(8, 8, 5);
	}
	
	public Field getBoard() {
		return board;
	}
	
	public void setBoard(Field board){
		this.board = board;
	}

	public void placeAllCustomShips() {
		placeCustomShips("length 5 Carrier?", 5);
		System.out.println(board.toString(true));
		placeCustomShips("length 4 Battleship?", 4);
		System.out.println(board.toString(true));
		placeCustomShips("length 3 Submarine?", 3);
		System.out.println(board.toString(true));
		placeCustomShips("length 3 Cruiser?", 3);
		System.out.println(board.toString(true));
		placeCustomShips("length 2 Destoyer?", 2);
		System.out.println(board.toString(true));
	}
	
	public void placeAllRandomShips() {
		placeRandomShips(2);
		placeRandomShips(3);
		placeRandomShips(3);
		placeRandomShips(4);
		placeRandomShips(5);
		System.out.println(board.toString(true));
	}
	
	public void placeCustomShips(String ship, int length) {
		String coordinate = "";
		char direction; 
		while(true) {
			while(true) {
				System.out.println("\nCoordinate for your " + ship);
				coordinate = TextIO.getlnString(); 
				if (coordinate.length() == 2) {
					System.out.println("What direction v or h?");
					direction = TextIO.getlnChar();
			
					if(direction == 'h' && getXCoor(coordinate) + length <= 7
							&& getYCoor(coordinate) <= 7) 
							break;
					else if(direction == 'v' && getYCoor(coordinate) + length <= 7
							&& getYCoor(coordinate) <= 7) 
							break;
				}
				System.out.println("You can't have a ship off the grid!!!");
			}
			
			if (board.checkIfAvailable(getXCoor(coordinate), getYCoor(coordinate), 
										direction, length)) {
				board.placeShips(getXCoor(coordinate), getYCoor(coordinate), direction, length);
				System.out.println("Ship placed!");
				break;
			}
			else {
				System.out.println("Please select an empty area!");
			}
		}
	}
	
	public void placeRandomShips(int length) {
		int randXCoor;
		int randYCoor;
		char randDir;
		boolean canSet = true;
		
		randXCoor = (int)(Math.random()*8);
		randYCoor = (int)(Math.random()*8);
		switch ((int)(Math.random()*2)) {
			case 0: 
				randDir = 'h';
				break;
			default: 
				randDir = 'v';
				break;
		}
		switch(randDir) {
			case 'h':
				if(randXCoor + length - 1 > 7) {
					placeRandomShips(length);
					canSet = false;
				}
				break;
			default:
				if(randYCoor + length - 1 > 7) {
					placeRandomShips(length);
					canSet = false;
				}
				break;
		}
		
		if(canSet) {
			if (board.checkIfAvailable(randXCoor, randYCoor, randDir, length)) 
				board.placeShips(randXCoor, randYCoor, randDir, length);
			else
				placeRandomShips(length);
		}
	}
	
	public static int getXCoor(String coordinate) {
		int xCoor;
		char xCoorChar = coordinate.charAt(0);
		switch (xCoorChar) {
			case 'a': 
				xCoor = 0;	
				break;
			case 'b': 
				xCoor = 1;	
				break;
			case 'c': 
				xCoor = 2;	
				break;
			case 'd': 
				xCoor = 3;	
				break;
			case 'e': 
				xCoor = 4;	
				break;
			case 'f': 
				xCoor = 5;	
				break;
			case 'g': 
				xCoor = 6;	
				break;
			case 'h':
				xCoor = 7;
				break;
			default: 
				xCoor = -1;	
				break;
		}
		return xCoor;
	}
	
	public static int getYCoor(String coordinate) {
		return Integer.parseInt(coordinate.substring(1, 2)) - 1;
	}
	
	public static char getXChar(int i) {
		if(i < 8)
			return 'a';
		else if(i < 16)
			return 'b';
		else if(i < 24)
			return 'c';
		else if(i < 32)
			return 'd';
		else if(i < 40)
			return 'e';
		else if(i < 48)
			return 'f';
		else if(i < 56)
			return 'g';
		else
			return 'h';
	}
	
	public static int getY(int i) {
		while(i + 1 > 8)
			i -= 8;
		return i+1;
	}
	
	public void takeGuess(String coordinate, AI opponent) {
		if(opponent.getBoard().getGrid(getXCoor(coordinate), getYCoor(coordinate)) == 1) {
			opponent.getBoard().setGrid(getXCoor(coordinate), getYCoor(coordinate), 2);
		}
		else {
			opponent.getBoard().setGrid(getXCoor(coordinate), getYCoor(coordinate), -1);
		}
		System.out.println(opponent.getBoard().toString(false));
	}
	
	public boolean sunkenShipCheck() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(getBoard().getGrid(i, j) == 1)
					return true;
			}
		}
		return false;
	}
}
