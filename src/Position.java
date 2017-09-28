//Mya Tsang
//Eckard
//ADS
//1/17/2016
//Graphic Maze
//
//Generates and solves a maze using stacks


public class Position {
	private int x;
	private int y;
	
	//---------------------------------------------------------------------------------------
	//Constructor passed x and y coordinates
	//---------------------------------------------------------------------------------------
	public Position(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	//---------------------------------------------------------------------------------------
	// returns x coordinate as integer
	//---------------------------------------------------------------------------------------
	public int getX(){
		return x;
	}
	
	//---------------------------------------------------------------------------------------
	// returns y coordinate as integer
	//---------------------------------------------------------------------------------------
	public int getY(){
		return y;
	}
	
	//---------------------------------------------------------------------------------------
	//sets the x coordinate
	//---------------------------------------------------------------------------------------
	public void setX(int x){
		this.x = x;
	}
	
	//---------------------------------------------------------------------------------------
	//Sets the y coordinate
	//---------------------------------------------------------------------------------------
	public void setY(int y){
		this.y = y;
	}
	
	//---------------------------------------------------------------------------------------
	//Prints the position
	//---------------------------------------------------------------------------------------
	public String toString(){
		return "X: " + x + "  Y: " + y;
	}
}