
public class AI extends Player{
	
	private int[] guesses;
	private int turns = 0;

	public AI(int xDim, int yDim, int ships) {
		super(xDim, yDim, ships);
		guesses = new int[64];
		for(int i = 0; i < 64; i++) {
			guesses[i] = i;
		}
		scrambleGuesses(guesses);
	}
	
	public void scrambleGuesses(int[] guesses) {
		for (int i = 64-1; i > 0; i--) {
    		int rand = (int)(Math.random()*(i+1));
    		int temp = guesses[i];
    		guesses[i] = guesses[rand];
    		guesses[rand] = temp;
    	}
	}
	
	public void placeAllRandomShips() {
		super.placeRandomShips(2);
		super.placeRandomShips(3);
		super.placeRandomShips(3);
		super.placeRandomShips(4);
		super.placeRandomShips(5);
		System.out.println(getBoard().toString(false));
	}
	
	public void takeGuess(Player opponent) {
		
		int xCoor = getXCoor(String.valueOf(getXChar(guesses[turns]))+String.valueOf(getY(guesses[turns])));
		int yCoor = getYCoor(String.valueOf(getXChar(guesses[turns]))+String.valueOf(getY(guesses[turns])));
		turns++;
		
		if(opponent.getBoard().getGrid(xCoor, yCoor) == 1) {
			opponent.getBoard().setGrid(xCoor, yCoor, 2);
		}
		else {
			opponent.getBoard().setGrid(xCoor, yCoor, -1);
		}
		System.out.println(opponent.getBoard().toString(true));
	}
	
}
