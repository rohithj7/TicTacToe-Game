import java.util.Scanner;
import java.util.ArrayList;

public class TicTacToe{

	private char[][] board;
	private char player; // 'X' or 'O'
	
	/*
	 * My Addition to the logic:
	 * All moves will be collected in the Array List.
	 */
	ArrayList<String> moves;
	
	/* 
	 * Instantiate board to be a 3 by 3 char array of spaces.
	 * Set player to be 'X'.
	 */
	public TicTacToe() {
		
		player = 'X';
		board = new char[3][3];
		moves = new ArrayList<String>();
		
		//Initializes all the spaces of the board with ' '.  
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				board[i][j] = ' ';
			}
		}
		
	}
	
	/* 
	 * If s represents a valid move, add the current player's symbol to the board and return true.
	 * Otherwise return false.
	 */
	public boolean play(String s) {
		
		//Array of all possible moves.
		String[] allBoxes = {"A1","A2","A3","B1","B2","B3","C1","C2","C3"};
		
		boolean correctInput = false;
		
		//Goes through all the elements of the allBoxes array and validates the input.
		for(String element : allBoxes) {
			if(s.equals(element)) {
				correctInput = true;
				break;
			}
		}
		
		if(!correctInput) return false;
		
		//Goes through the array list moves and checks if a move is repeated.
		for(String element : moves) {
			if(s.equals(element)) {
				return false;
			}
		}
		
		moves.add(s);
		
		//Calculates the row and column numbers of where to allocate the current player. 
		int rowNum = 0;
		int colNum = 0;
		
		if(s.substring(0,1).equals("A")) {rowNum = 0;}
		if(s.substring(0,1).equals("B")) {rowNum = 1;}
		if(s.substring(0,1).equals("C")) {rowNum = 2;}
		
		if(Integer.parseInt(s.substring(1,2)) == 1) {colNum = 0;} 
		if(Integer.parseInt(s.substring(1,2)) == 2) {colNum = 1;}
		if(Integer.parseInt(s.substring(1,2)) == 3) {colNum = 2;}

		//Equates the player with the allocated place. 
		board[rowNum][colNum] = player;
		
		return true; 
	}
	
	/*
	 * Switches the current player from X to O, or O to X.
	 */
	public void switchTurn() {
		
		if(player == 'X') {
			player = 'O';
		} else if (player == 'O') {
			player = 'X';
		}
		
	}
	
	private final String TRIPLE_X = "XXX";
	private final String TRIPLE_O = "OOO";
	
	/*
	 * Returns true if the current player has won the game.
	 * Three in a row, column or either diagonal.
	 * Otherwise, return false.
	 */
	public boolean won() {
		
		//Checks all three columns for a triple X or O.
		for(int i = 0; i < board.length; i++) {
			
			String rowOverall = "";

			for(int j = 0; j < board[0].length; j++) 
				rowOverall += board[i][j];
			
			if(rowOverall.equals(TRIPLE_X) || rowOverall.equals(TRIPLE_O)) return true;
			
		}
		
		//Checks all three columns for a triple X or O.
		for(int i = 0; i < board[0].length; i++) {
			
			String colOverall = "";
			
			for(int j = 0; j < board.length; j++) 
				colOverall += board[j][i];

			if(colOverall.equals(TRIPLE_X) || colOverall.equals(TRIPLE_O)) return true;
			
		}
		
		String diagOneOverall = "";
		String diagTwoOverall = "";
		
		//Checks diagonal one for a triple X or O.
		for(int i = 0, j = 0; i < board.length && j < board[0].length; i++, j++) {
			
			diagOneOverall += board[i][j];
			if(diagOneOverall.equals(TRIPLE_X) || diagOneOverall.equals(TRIPLE_O)) return true;
			
		}
		
		//Checks diagonal two for a triple X or O.
		for(int i = 0, j = board[0].length - 1; i < board.length && j >= 0; i++, j--) {
			
			diagTwoOverall += board[i][j];
			if(diagTwoOverall.equals(TRIPLE_X) || diagTwoOverall.equals(TRIPLE_O)) return true;
			
		}
		
		return false;
	}
	
	/*
	 * Returns true if there are no places left to move
	 */
	public boolean stalemate() {
		
		/*
		 * Checks to see if any of the places on the board are unfilled.
		 * If yes, continues. If not, and there is no winner, the method returns true for
		 * a stalemate.
		 */
	    for(int i = 0; i < board.length; i++) {
	    	for(int j = 0; j < board[0].length; j++) {
	    		if(board[i][j] == ' ') return false;
	    	}
	    }
	    
		return true;
	}
	
	/*
	 * Returns private instance variable player
	 */
	public char getPlayer() {
		return player;
	}
	
	/*
	 * Prints tic-tac-toe board
	 */
	public void print() {
		System.out.println();
		System.out.println("\t  1 2 3");
		System.out.println();
		System.out.println("\tA "+board[0][0]+"|"+board[0][1]+"|"+board[0][2]);
		System.out.println("\t  -----");
		System.out.println("\tB "+board[1][0]+"|"+board[1][1]+"|"+board[1][2]);
		System.out.println("\t  "+"-----");
		System.out.println("\tC "+board[2][0]+"|"+board[2][1]+"|"+board[2][2]);
		System.out.println();
	}
	
	/* 
	 * Runs the game by getting input from the user, making the 
	 * appropriate moves, and prints who won or if it was a stalemate. 
	*/ 
	public static void main(String[] args) {
	       
		Scanner in = new Scanner(System.in);
	    TicTacToe game = new TicTacToe();
	    System.out.println("Welcome to Tic-tac-toe");
	    System.out.println("Enter coordinates for your move following the X and O prompts");
	    
	    while(!game.stalemate()) 
	    {
	           game.print();
	           System.out.print(game.getPlayer() + ": ");

	           //Loop while the method play does not return true when given their move.
	           //Body of loop should ask for a different move
	           while(!game.play(in.next()))
	           {
	                 System.out.println("Illegal move. Enter your move.");
	                 System.out.print(game.getPlayer() + ": ");
	            }
	           //If the game is won, call break;
	           
	           /*
	            * My Addition:
	            * Only calls won method if there have been more than 4 moves, since
	            * there cannot be a winner without 4 moves being made. 
	            */	           
	           if(game.moves.size() > 4) {
	        	   
	        	   //The loop is broken if the game is won.
			       if(game.won())
			            break;
			       
	           }
	         
	           //Switch the turn
	           game.switchTurn();

	     }
	     game.print();
	     if(game.won())
	     {
	          System.out.println("Player " + game.getPlayer() + " Wins!!!!");
	     } 
	     else 
	     {
	          System.out.println("Stalemate");
	     }
		
		in.close();
		
	}
	
}