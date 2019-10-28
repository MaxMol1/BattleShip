
public class BattleShip {
	
	public static void main(String[] args) {
		
		Player player = new Player(8, 8, 5);
		AI AI = new AI(8, 8, 5);
		
		runIntroduction();
		placeShips(player, AI);
		startGame(player, AI);
	}
	
	public static void runIntroduction() {
		System.out.println("Welcome to Battleship! The objective of the game is to outsmart your opponent"
				+ "\nby destroying all of his ships before he destroys your own. "
				+ "\nTo begin, place your ships by selecting a coordinate on the grid "
				+ "\nand then a direction (horizontal/vertical) using the characters: h or v."
				+ "\nThen, you and your opponent will take turns trying to guess the coordinates "
				+ "\nof each other's ships. Enter the coordinates when prompted."
				+ "\nA coordinate is defined as a letter and a number and the grid ranges"
				+ "\nfrom A-H and 1-8. Good Luck!");
	}
	
	public static void placeShips(Player player1, AI player2) {
		String choice = "";
		
		while(true) {
			if (choice.equals("c") || choice.equals("r"))
				break;
			System.out.println("Do you wish to place custom or random ships (c / r)?");
			choice = TextIO.getlnString().toLowerCase();
		}
			
		if (choice.equals("c")) {
			player1.placeAllCustomShips();
		}
		else {
			player1.placeAllRandomShips();
		}
		player2.placeAllRandomShips();
	}
	
	public static void startGame(Player player1, AI player2) {
		String coordinate = "";
		while(true) {
			System.out.println("\n\nWhat coordinate do you wish to attack?");
			coordinate = TextIO.getlnString().toLowerCase();
			while(isValidCoordinate(coordinate) == false) {
				System.out.println("Pick a valid coordinate!");
				coordinate = TextIO.getlnString().toLowerCase();
			}
			while(player2.getBoard().getGrid(Player.getXCoor(coordinate), Player.getYCoor(coordinate)) == 2 ||
					player2.getBoard().getGrid(Player.getXCoor(coordinate), Player.getYCoor(coordinate)) == -1) {
				System.out.println("Pick a new coordinate!");
				coordinate = TextIO.getlnString();
			}
			
			if(!player2.sunkenShipCheck())
				break;
			player2.takeGuess(player1);

			if(!player1.sunkenShipCheck() || !player2.sunkenShipCheck())
				break;
			player1.takeGuess(coordinate, player2);
		}
		if(!player1.sunkenShipCheck())
			System.out.println("You Lost!");
		else
			System.out.println("You won!");
	}
	
	public static boolean isValidCoordinate(String coordinate) {
		if(coordinate.length() != 2)
			return false;
		if(Player.getXCoor(coordinate) == -1 || Player.getYCoor(coordinate) > 7 ||
				Player.getYCoor(coordinate) < 0) {
			return false;
		}
		return true;
	}
	
}
