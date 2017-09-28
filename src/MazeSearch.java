import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JFrame;


public class MazeSearch {
	//---------------------------------------------------------------------------------------
	//  Creates a new maze, prints its original form, tries to
	//  solve it, and prints out its final form.
	//---------------------------------------------------------------------------------------
	public static void main (String[] args) throws InterruptedException {
		JFrame application;
		GraphicMaze maze;
		while(true) {
			application = new JFrame();
			application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
			maze = new GraphicMaze(30, 30);
			application.setContentPane(maze.getPanel());
			application.setSize(new Dimension(800,800));	
			application.setVisible( true );
			maze.traverse(1, 1);

			Thread.sleep(100);
			application.setVisible(false);
		}
	}
}