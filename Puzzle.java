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


		public void actionPerformed(ActionEvent e){
			boolean foundButton = false;
			int imageIndex = 0; 
			for (int i = 0; i < 3; i++){
          			for (int j = 0; j < 4; j++){
      					boolean isAdjacent = ((rowBlankTile == i) && ((colBlankTile == j+1) || (colBlankTile == j-1))) ||
							(((rowBlankTile == i+1) || (rowBlankTile == i-1)) && (colBlankTile == j)); //Verifies thanks to the coordinates that the tile clicked are adjacent to the blank tile
					if (e.getSource() == button[i][j] && isAdjacent){
						Icon tempIcon = button[i][j].getIcon(); //A temporary icon is created to store the image of the tile clicked
						button[i][j].setIcon( button[rowBlankTile][colBlankTile].getIcon()); //The tile clicked becomes associated with the blank tile
						button[rowBlankTile][colBlankTile].setIcon(tempIcon); //The blank tile get the image stored in the temporary icon of the tile clicked
						indexArray[indexBlankTile] = indexArray[imageIndex]; //The index of the blank tile becomes the one of the tile clicked
						indexArray[imageIndex] = 0; //Since at the start of the game the index of the Blank tile is 0, the first tile clicked will get the index of 0
						rowBlankTile = i;
						colBlankTile = j;
						indexBlankTile = imageIndex; //The index of the blank tile gets the index of the tile swapped
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
				if (indexArray[i] != i) //if an index does not match the index where it should be, the puzzle is not completed yet. And the Game continues.
					return;
			}
			scoreManager.show();
		}
}

