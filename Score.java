import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Score
{
	
	private int score = 0;
	
	
	
	public Score (){	

	JFrame a = new JFrame();
	JPanel panel = new JPanel();
	a.setVisible(true); 
	a.setTitle("Score");
	a.setSize(200,200);
	a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	a.setContentPane(panel);
	}
	
	public int getScore (){
		return score;
	}
	
	public void addScore (){
		score++;
	}
	
}
 
