import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Puzzle implements ActionListener
{
	private JButton[][] button = new JButton[3][4];
	private int rowBlankTile = 0;
	private int colBlankTile = 0;
	
	public Puzzle(){
	
		JFrame f = new JFrame();
		JPanel p = new JPanel();

		int imageIndex = 0;
		for (int i = 0; i < 3; i++){
          		for (int j = 0; j < 4; j++){	
					ImageIcon im = new ImageIcon ("resources/Bart"+imageIndex+".jpg");
					button[i][j] = new JButton (im);
					button[i][j].addActionListener(this);
					p.add(button[i][j]);
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
			for (int i = 0; i < 3; i++){
          			for (int j = 0; j < 4; j++){
      					boolean isAdjacent = ((rowBlankTile == i) && ((colBlankTile == j+1) || (colBlankTile == j-1))) ||
							(((rowBlankTile == i+1) || (rowBlankTile == i-1)) && (colBlankTile == j));
					if (e.getSource() == button[i][j] && isAdjacent){
						Icon tempIcon = button[i][j].getIcon();
						button[i][j].setIcon( button[rowBlankTile][colBlankTile].getIcon());
						button[rowBlankTile][colBlankTile].setIcon(tempIcon);
						rowBlankTile = i;
						colBlankTile = j;
						foundButton = true;
						break; 
					}
				}
				if(foundButton)
					break;
				
			}
		}

}

