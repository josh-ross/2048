import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 */

/**
 * @author joshross
 *
 */
public class Main {
	
	private static int best = 0;
	
	public static void printBoard(Board game) {
		int[][] board = game.getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws IOException {
		boolean quit = false;
		System.out.println("Welcome to 2048");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What dimensions would you like the board to be?");
		int n = Integer.parseInt(br.readLine());
		Board game = new Board(n);
		while (!quit) {
			game.startGame();
			while (true) {
				Main.printBoard(game);
				if (game.getScore() >= best) {
					best = game.getScore();
				}
				System.out.println("Score: " + game.getScore() + "; Best Score: " + best);
				System.out.println("Moves: Left(L), Right(R), Up(U), Down(D), New Game(N), Quit(Q)");
				String s = br.readLine();
				
				if (s.equals("L")) {
					game.moveLeft();
				}
				else if (s.equals("R")) {
					game.moveRight();
				}
				else if (s.equals("U")) {
					game.moveUp();
				}
				else if (s.equals("D")) {
					game.moveDown();
				}
				else if (s.equals("N")) {
					break;
				}
				else if (s.equals("Q")) {
					quit = true;
					break;
				}
				else {
					System.out.println("Invalid Input Please Try Again (Make Sure Move Is Capital Letter");
				}
			}
		}
		br.close();
	}
}
