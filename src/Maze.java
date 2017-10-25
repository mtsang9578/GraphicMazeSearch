import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that constructs a maze 
 * @author Mya Tsang
 *
 */
public class Maze {
	private final int BLOCKED = 0;	//Blocked cells marked with 0 
	private final int OPEN = 1;		//Open cells marked as 1
	private final int VISITED = 2;	//Blocked cells that become open during generation are marked with 2
	private final int TRIED = 3;	//Cells visited marked with 3 during traverse
	private final int SOLVED = 7;	//Final path marked with 7 when solved

	private Stack path;
	private Stack nPath;
	private int numRows, numCols;
	private String[][] randomGrid;
	private int[][]rGrid;

	/**
	 * Constructor for a maze of given dimensions
	 * @param r
	 * @param c
	 */
	public Maze(int r, int c) {
		numRows = r;
		numCols = c;
		path = new Stack();
		nPath = new Stack();
		generateRandomMaze();	
	}

	/**
	 * Generates a random maze 
	 */
	public void generateRandomMaze() {

		int r = numRows;
		int c = numCols;
		randomGrid = new String[r][c];
		rGrid = new int[2 * r + 1][2 * c + 1];

		//Creates alternating cells open and closed
		for(int i = 0; i < r; i++){
			for(int j = 0; j < c; j++){
				if( (i+j) % 2 == 1){
					randomGrid[i][j] = null;
				}
			}
		}

		//Set up the checkered integer grid with closed borders
		for(int i = 0; i < 2 * r + 1; i++) {
			for(int j = 0; j < 2 * c + 1; j++) {
				if(i % 2 == 0) {
					rGrid[i][j] = BLOCKED;
				} else if(j == 0 ) {
					rGrid[i][j] = BLOCKED;
				} else if(i == 2 * r) {
					rGrid[i][j] = BLOCKED;
				} else if((i + j) % 2 == 0) {
					rGrid[i][j] = OPEN;
				}
			}
		}

		//Open a square at the bottom right corner for exit
		rGrid[2 * r - 1][2 * c] = OPEN;

		//Generate a maze starting at this point
		generate(0,0);

	}

	/**
	 * Recursively traverses a checkered grid and determines which cells to open
	 * @param row
	 * @param col
	 * @return true if finished
	 */
	public boolean generate(int row, int col) {
		nPath.push(new Position(row, col)); // add it to the maze
		boolean done = false;

		int numOpen = 0;
		ArrayList<String> openPos = new ArrayList<String>();

		//look at neighbors and if they are open, add them to the stack of open positions
		if (rvalid(row + 1, col)) {
			numOpen++;
			openPos.add("D");
		}
		
		if (rvalid(row, col + 1)) {
			numOpen++;
			openPos.add("R");
		}
		
		if (rvalid(row-1, col)) {
			numOpen++;
			openPos.add("U");
		}
		if (rvalid(row, col - 1)) {
			numOpen++;
			openPos.add("L");
		}

		if (numOpen == 0) {
			//if dead end then backtrack;
			nPath.pop();		
			if (nPath.empty()) { 
				//if the stack is empty at dead end you are done
				return true;
			} else {
				//if the stack is not empty re-search the last position
				Position current = (Position) nPath.pop(); 	
				generate(current.getX(), current.getY());
			}
		} else {
			//choose a random spot from unvisited and open a path to it
			int choice = (int) (Math.random() * numOpen);
			
			if (openPos.get(choice).equals("D")) {
				randomGrid[row][col] = "D";
				rGrid[2 * row + 2][2 * col + 1] = VISITED;
				randomGrid[row+1][col] = "D";
				generate(row + 1, col);
			}else if (openPos.get(choice).equals("R")) {
				randomGrid[row][col] = "R";
				rGrid[2 * row + 1][2 * col + 2] = VISITED;
				randomGrid[row][col+1] = "R";
				generate(row, col+1);
			} else if (openPos.get(choice).equals("U")) {
				randomGrid[row][col] = "U";
				rGrid[2 * row][2 * col + 1] = VISITED;
				randomGrid[row - 1][col] = "U";
				generate(row - 1, col);
			} else if (openPos.get(choice).equals("L")) {
				randomGrid[row][col] = "L";
				rGrid[2 * row + 1][2 * col] = VISITED;
				randomGrid[row][col - 1] = "L";
				generate(row, col - 1);
			}
		}
		return done;
	}

	/**
	 * Returns the numeric representation of the maze in an array
	 * @return
	 */
	public int[][] getGrid(){
		return rGrid;
	}

	/**
	 * Determines if a cell is in the bounds of the matrix and has not been previously entered
	 * @param row
	 * @param column
	 * @return
	 */
	private boolean rvalid (int row, int column) {
		boolean result = false;

		//  check if cell is in the bounds of the matrix
		if (row >= 0 && row < randomGrid.length &&
				column >= 0 && column < randomGrid[row].length)

			//  check if cell is not blocked and not previously tried
			if (randomGrid[row][column] == null)
				result = true;

		return result;
	}
}