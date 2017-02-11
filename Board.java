/**
 * 
 */

/**
 * @author joshross
 *
 */
public class Board {

	protected int[][] board;
	private int dim;
	private int score;
	
	//Takes in value for dimensions of grid
	public Board(int dim) {
		this.dim = dim;
		score = 0;
		//Initialize board by input dimensions
		board = new int[dim][dim];
	}
	
	public int[][] getBoard() {
		return board;
	}
	
	public void startGame() {
		//Initialize each spot on board with 0
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
		score = 0;
		setRandomBlock();
		setRandomBlock();
	}
	
	public int getScore() {
		return score;
	}
	
	public void moveUp() {
		for (int col = 0; col < board[0].length; col++) {
			int[] currCol = new int[dim];
			int count = 0;
			for (int row = 0; row < board.length; row++) {
				if (board[row][col] != 0) {
					currCol[count] = board[row][col];
					count++;
				}
			}
			for (int curr = 0; curr < currCol.length - 1; curr++) {
				if (currCol[curr] == currCol[curr + 1]) {
					currCol[curr] = currCol[curr] * 2;
					score += currCol[curr];
					int next = curr + 1;
					while (next < currCol.length - 1) {
						currCol[next] = currCol[next + 1];
						next++;
					}
					currCol[next] = 0;
				}
			}
			for (int row = 0; row < board.length; row++) {
				board[row][col] = currCol[row];
			}
		}
		if (checkLose()) {
			System.out.println("Game Over!");
		}
		else if (checkZero()) {
			System.out.println("Try a different move");
		}
		else {
			setRandomBlock();
		}
	}
	
	public void moveDown() {
		for (int col = 0; col < board[0].length; col++) {
			int[] currCol = new int[dim];
			int count = dim - 1;
			for (int row = board.length - 1; row >= 0; row--) {
				if (board[row][col] != 0) {
					currCol[count] = board[row][col];
					count--;
				}
			}
			for (int curr = currCol.length - 1; curr > 0; curr--) {
				if (currCol[curr] == currCol[curr - 1]) {
					currCol[curr] = currCol[curr] * 2;
					score += currCol[curr];
					int next = curr - 1;
					while (next > 0) {
						currCol[next] = currCol[next - 1];
						next--;
					}
					currCol[next] = 0;
				}
			}
			for (int row = 0; row < board.length; row++) {
				board[row][col] = currCol[row];
			}
		}
		if (checkLose()) {
			System.out.println("Game Over!");
		}
		else if (checkZero()) {
			System.out.println("Try a different move");
		}
		else {
			setRandomBlock();
		}
	}
	
	public void moveRight() {
		for (int row = 0; row < board.length; row++) {
			int[] currRow = new int[dim];
			int count = dim - 1;
			for (int col = board[row].length - 1; col >= 0; col--) {
				if (board[row][col] != 0) {
					currRow[count] = board[row][col];
					count--;
				}
			}
			for (int curr = currRow.length - 1; curr > 0; curr--) {
				if (currRow[curr] == currRow[curr - 1]) {
					currRow[curr] = currRow[curr] * 2;
					score += currRow[curr];
					int next = curr - 1;
					while (next > 0) {
						currRow[next] = currRow[next - 1];
						next--;
					}
					currRow[next] = 0;
				}
			}
			board[row] = currRow;
		}
		if (checkLose()) {
			System.out.println("Game Over!");
		}
		else if (checkZero()) {
			System.out.println("Try a different move");
		}
		else {
			setRandomBlock();
		}
	}
	
	public void moveLeft() {
		for (int row = 0; row < board.length; row++) {
			int[] currRow = new int[dim];
			int count = 0;
			for (int col = 0; col < board[row].length; col++) {
				if (board[row][col] != 0) {
					currRow[count] = board[row][col];
					count++;
				}
			}
			for (int curr = 0; curr < currRow.length - 1; curr++) {
				if (currRow[curr] == currRow[curr + 1]) {
					currRow[curr] = currRow[curr] * 2;
					score += currRow[curr];
					int next = curr + 1;
					while (next < currRow.length - 1) {
						currRow[next] = currRow[next + 1];
						next++;
					}
					currRow[next] = 0;
				}
			}
			board[row] = currRow;
		}
		if (checkLose()) {
			System.out.println("Game Over!");
		}
		else if (checkZero()) {
			System.out.println("Try a different move");
		}
		else {
			setRandomBlock();
		}
	}
	
	private boolean checkZero() {
		for (int row = 0; row < dim; row++) {
			for (int col = 0; col < dim; col++) {
				if (board[row][col] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkLose() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				//Check block above
				if (row >= 1) {
					if (board[row][col] == board[row - 1][col]) {
						return false;
					}
				}
				//Check block below
				if (row <= board.length - 2) {
					if (board[row][col] == board[row + 1][col]) {
						return false;
					}
				}
				//Check block right
				if (col <= board.length - 2) {
					if (board[row][col] == board[row][col + 1]) {
						return false;
					}
				}
				//Check block left
				if (col >= 1) {
					if (board[row][col] == board[row][col - 1]) {
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private void setRandomBlock() {
		//Need to round the number because of the case of a 2 dimensions game
		int x1 = (int) Math.round((Math.random() * (dim - 1)));
		int y1 = (int) Math.round((Math.random() * (dim - 1)));
		if (board[y1][x1] != 0) {
			setRandomBlock();
		}
		else {
			if (Math.random() <= 0.7) {
				board[y1][x1] = 2;
			}
			else {
				board[y1][x1] = 4;
			}
		}
	}
	

}
