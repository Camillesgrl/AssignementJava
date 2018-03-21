import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Score implements ActionListener
{
	
	private int score = 0;
	private JFrame a = new JFrame("Scores");
	private JLabel[][] scoreLabels = new JLabel[5][2];//lines and colums
    private JTextField newNameTextField = new JTextField();
    
	public Score (){	
		JPanel panel = new JPanel();
		a.setVisible(false); 
		a.setTitle("Score");
		a.setSize(400,200);
		a.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Close the scores window without closing the whole application
		//initialise the labels with values
		for(int i=0;i<5;i++){
			scoreLabels[i][0] = new JLabel("Name"+(i+1)); 
			panel.add(scoreLabels[i][0]);
			scoreLabels[i][1] = new JLabel("0");
			panel.add(scoreLabels[i][1]);
		}
		//adding the last row to insert a new name once the puzzle is completed
		panel.add(new JLabel("Enter your name: "));
		panel.add(newNameTextField);
		newNameTextField.addActionListener(this);
		panel.setLayout(new GridLayout(6,2));
		a.setContentPane(panel);
	}
	
	public int getScore (){
		return score;
	}
	
	public void addScore (){
		score++;
	}
	
	public void show(){
		a.setVisible(true); 
	}
	//The name of the player must be more than one characther length to be valid and entered
	public void actionPerformed(ActionEvent e){
		if(newNameTextField.getText().length()>0){
			displayNewScore();
		}
	}
	
	private void displayNewScore(){
		//look up in the labels to find where to insert the new score
		boolean isScorePositionFound = false;
		String tempNameLabel = "";
		String tempScoreLabel = "";
		for(int i=0;i<5;i++){
			if(isScorePositionFound){
				//we shift the labels for names/scores
				String thatLineName = scoreLabels[i][0].getText();
				String thatLineScore = scoreLabels[i][1].getText();
				scoreLabels[i][0].setText(tempNameLabel);
				scoreLabels[i][1].setText(tempScoreLabel);
				tempNameLabel=thatLineName;
				tempScoreLabel=thatLineScore;
		    }
		    else{
				//get the score of the current line
				int tableScore = Integer.parseInt(scoreLabels[i][1].getText());
				//check if the current score should be inserted at that position in the table and keep a reference of what was in the table at this line 
				//to replace it in the next line
				if(tableScore > score || tableScore == 0){
					tempNameLabel = scoreLabels[i][0].getText();
					tempScoreLabel = scoreLabels[i][1].getText();
					scoreLabels[i][0].setText(newNameTextField.getText());
					scoreLabels[i][1].setText(Integer.toString(score));
					isScorePositionFound=true;

				}					
			}
		}
	}
	
}
 
