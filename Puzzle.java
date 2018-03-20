import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Puzzle implements ActionListener
{
	private JButton[][] button = new JButton[3][4];
	private int rowBlankTile = 0;
	private int colBlankTile = 0;
	private int indexBlankTile = 0;
	private	JFrame f = new JFrame();

	private int[] indexArray = new int [12];
	
	private Score scoreManager = new Score();
	
	public Puzzle(){
	
		JPanel p = new JPanel();

		int imageIndex = 0;
		for (int i = 0; i < 3; i++){
          		for (int j = 0; j < 4; j++){	
					ImageIcon im = new ImageIcon ("resources/Bart"+imageIndex+".jpg");
					button[i][j] = new JButton (im);
					button[i][j].addActionListener(this);
					p.add(button[i][j]);
					indexArray[imageIndex] = imageIndex;
					imageIndex++;
				}
		}
		f.setTitle("Puzzle");
		f.setSize (111*4,121*3);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setLayout(new GridLayout(3,4));  
		f.setContentPane(p);
		f.setVisible(true);			
		}

//This block of code enables only adjacent tiles to be swapped with the blank tile, and does not permit th 
		public void actionPerformed(ActionEvent e){
			boolean foundButton = false;
			int imageIndex = 0; 
			for (int i = 0; i < 3; i++){
          			for (int j = 0; j < 4; j++){
      					boolean isAdjacent = ((rowBlankTile == i) && ((colBlankTile == j+1) || (colBlankTile == j-1))) ||
							(((rowBlankTile == i+1) || (rowBlankTile == i-1)) && (colBlankTile == j));
					if (e.getSource() == button[i][j] && isAdjacent){
						Icon tempIcon = button[i][j].getIcon();
						button[i][j].setIcon( button[rowBlankTile][colBlankTile].getIcon());
						button[rowBlankTile][colBlankTile].setIcon(tempIcon);
						indexArray[indexBlankTile] = indexArray[imageIndex];
						indexArray[imageIndex] = 0;
						rowBlankTile = i;
						colBlankTile = j;
						indexBlankTile = imageIndex;
						scoreManager.addScore();
						f.setTitle("Puzzle Score= " + scoreManager.getScore());
						foundButton = true;
						checkPuzzleCompleted();
						break; 
					}
					imageIndex++;
				}
				if(foundButton)
					break;
				
			}
		}
		
		private void checkPuzzleCompleted (){
			for (int i = 0; i < 12; i++){
				if (indexArray[i] != i)
					return;
			}
			System.out.println("GAGNEEEEE");
}
}

