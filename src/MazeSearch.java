import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * Contains the main method for the program. Generates mazes in a loop
 * @author admin
 *
 */
public class MazeSearch {
	/**
	 * Continuously generates and solves mazes
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main (String[] args) throws InterruptedException {
		JFrame application;
		GraphicMaze maze;
		while(true) {
			
			//Creates a 10x10 maze
			application = new JFrame();
			application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			maze = new GraphicMaze(10, 10); //Specify the dimensions of the maze
			application.setContentPane(maze.getPanel());
			application.setSize(new Dimension(800,800));	
			application.setVisible( true );
			maze.traverse(1, 1);
			Thread.sleep(100);
			application.setVisible(false);
			
			//Creates a 20x20 maze
			application = new JFrame();
			application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			maze = new GraphicMaze(20, 20); //Specify the dimensions of the maze
			application.setContentPane(maze.getPanel());
			application.setSize(new Dimension(800,800));	
			application.setVisible( true );
			maze.traverse(1, 1);
			Thread.sleep(100);
			application.setVisible(false);
			
			//Creates a 15x15 maze
			application = new JFrame();
			application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			maze = new GraphicMaze(15, 15); //Specify the dimensions of the maze
			application.setContentPane(maze.getPanel());
			application.setSize(new Dimension(800,800));	
			application.setVisible( true );
			maze.traverse(1, 1);
			Thread.sleep(100);
			application.setVisible(false);
		}
	}
}