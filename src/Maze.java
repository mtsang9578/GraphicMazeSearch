import java.util.ArrayList;
import java.util.Stack;
//Mya Tsang
//Eckard
//ADS
//1/17/2016
//Graphic Maze
//
//Generates and solves a maze using stacks

public class Maze
{
	//private final int TRIED = 3;
	private final int PATH = 7;
	private final int VISITED = 2;

	private Stack path;
	private Stack nPath;
	private int numRows, numCols;


	private String[][] randomGrid;
	private int[][]rGrid;


	public Maze(int r, int c) {
		numRows = r;
		numCols = c;
		path = new Stack();
		nPath = new Stack();
		generateRandomMaze();	

	}

	//---------------------------------------------------------------------------------------
	//  Tries to recursively follow the maze. Inserts special
	//  characters for locations that have been tried and location
	//  that become part of the solution.
	//---------------------------------------------------------------------------------------
	public void generateRandomMaze(){

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


		//Set up the integer grid with closed borders
		for(int i = 0; i < 2 * r + 1; i++) {
			for(int j = 0; j < 2 * c + 1; j++) {
				if(i % 2 == 0) {
					rGrid[i][j] = 0;
				} else if(j == 0 ) {
					rGrid[i][j] = 0;
				} else if(i == 2 * r) {
					rGrid[i][j] = 0;
				} else if((i + j) % 2 == 0) {
					rGrid[i][j] = 1;
				}
			}
		}

		rGrid[2 * r - 1][2 * c] = 1;

		//Generate a maze starting at this point
		generate(0,0);

	}

	//---------------------------------------------------------------------------------------
	// Function to recursively generate a maze
	//---------------------------------------------------------------------------------------
	public boolean generate(int row, int col) {
		nPath.push(new Position(row, col)); // add it to the maze
		boolean done = false;

		int numOpen = 0;
		ArrayList<String> openPos = new ArrayList<String>();

		//look at neighbors
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
			nPath.pop();		//if dead end then backtrack;

			if (nPath.empty()) { 
				return true;	//if the stack is empty at dead end you are done
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
	


	//---------------------------------------------------------------------------------------
	//Returns the randomly generated grid
	//---------------------------------------------------------------------------------------
	public int[][] getGrid(){
		return rGrid;
	}


	//---------------------------------------------------------------------------------------
	//  Determines if a specific location is valid when generating
	//---------------------------------------------------------------------------------------
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