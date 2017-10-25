/**
 * A class to represent the coordinates of a cell in the maze
 * @author Mya Tsang
 *
 */
public class Position {
	private int x;
	private int y;

	/**
	 * Constructor for a set of x-y coordinates
	 * @param x
	 * @param y
	 */
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}

	/**
	 * Getter method for the x coordinate
	 * @return the x coordinate as an int
	 */
	public int getX(){
		return x;
	}

	/**
	 * Getter method for the y coordinate
	 * @return the y coordinate as an int
	 */
	public int getY(){
		return y;
	}

	/**
	 * Sets the x coordinate
	 * @param x
	 */
	public void setX(int x){
		this.x = x;
	}

	/**
	 * Sets the y coordinate
	 * @param y
	 */
	public void setY(int y){
		this.y = y;
	}

	/**
	 * Returns the coordinates as a String
	 * @return String coordinates
	 */
	public String toString(){
		return "X: " + x + "  Y: " + y;
	}
}
