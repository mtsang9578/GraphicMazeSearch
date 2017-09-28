import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicMaze {
	private final int TRIED = 3;	//visited cells marked with 3
	private final int SOLVED = 7;	//final path when solved marked with 7
	private int i;
	private JPanel panel;
	private int[][] grid;
	private JPanel [][] blocks;
	private Maze maze;
	private Stack path;


	//--------------------------------------------------------------
	// Constructor that generates a maze from the maze class
	//--------------------------------------------------------------
	public GraphicMaze(int numRows, int numCols) {
		path = new Stack();

		//Calls Maze class to construct a maze
		maze = new Maze(numRows, numCols);
		grid = maze.getGrid();

		int rows = grid.length;
		int cols = grid[0].length;
		blocks = new JPanel[rows][cols];	
		GridLayout layout = new GridLayout(rows, cols);
		panel = new JPanel();
		panel.setLayout(layout);
		
		//initializes the squares in the graphic maze to black or white
		for(int r =0; r< rows; r++){
			for(int c = 0; c<cols; c++){
				JPanel block = new JPanel();
				//block.setSize(new Dimension(100,100));
				block.setPreferredSize(new Dimension(10, 10));
				if (grid[r][c] == 1 ||grid[r][c] == 2 ) {
					block.setBackground(Color.white);
					blocks[r][c] = block; 
					panel.add(block);
				} else {
					blocks[r][c] = block;
					block.setBackground(Color.black);
					panel.add(block);
				}
			}
		}
	}

//	//--------------------------------------------------------------
//	// Create a 20x20 maze by default
//	//--------------------------------------------------------------
//	public GraphicMaze() {
//		this(20, 20);
//	}

	//--------------------------------------------------------------
	// Calls the maze to be graphically solved
	//--------------------------------------------------------------
	public boolean traverse (int row, int column) throws InterruptedException
	{
		Thread.sleep(5);
		path.push(new Position(row, column));
		boolean done = false;
		if (valid (row, column)) { 
			grid[row][column] = TRIED; // this cell has been tried


			//Creates a gradient for aesthetic effect 
			i += 2*Math.PI;
			blocks[row][column].setBackground(new Color((int) (127.5 + 127.5 * Math.cos(i)),
					(int) (127.5 + 127.5 * Math.sin(i)), 128));

			if (row == grid.length-2 && column == grid[0].length-1) {
				done = true; // the maze is finished
			} else {
				done = traverse (row + 1, column);     // down
				if (!done) {
					path.pop();
					done = traverse (row, column + 1);  // right
				}

				if (!done) {
					path.pop();
					done = traverse (row - 1, column);  // up
				}

				if (!done) {
					path.pop();
					done = traverse (row, column - 1);  // left
				}
			}

			if (done) {  // this location is part of the final path
				grid[row][column] = SOLVED;
				blocks[row][column].setBackground(Color.GREEN);
			} else {
				blocks[row][column].setBackground(Color.white);
				path.pop();
			}
		}
		return done;
	}


	//--------------------------------------------------------------
	//  Determines if a specific location is valid.
	//--------------------------------------------------------------
	private boolean valid (int row, int column) {
		boolean result = false;

		//  check if cell is in the bounds of the matrix
		if (row >= 0 && row < grid.length &&
				column >= 0 && column < grid[row].length)

			//  check if cell is not blocked and not previously tried
			if (grid[row][column] == 1 || grid[row][column] == 2)
				result = true;

		return result;
	}

	//---------------------------------------------------------------------------------------
	//  Returns the Graphic Panel
	//---------------------------------------------------------------------------------------
	public JPanel getPanel(){
		return panel;
	}

	//---------------------------------------------------------------------------------------
	//  Returns the maze as a string.
	//---------------------------------------------------------------------------------------
	public String toString () {
		String result = "\n";
		int[][] grid2 = new int[grid.length][grid[0].length];

		while(!path.isEmpty()) {
			Position current = (Position) path.pop();
			grid2[current.getX()][current.getY()] = SOLVED;
		}

		for (int row=0; row < grid.length; row++) {
			for (int column=0; column < grid[row].length; column++)
				System.out.print(grid2[row][column]);
			System.out.println();
		}

		for (int row=0; row < grid.length; row++) {
			for (int column=0; column < grid[row].length; column++)
				result += grid[row][column] + "";
			result += "\n";
		}

		return result;
	}

}
