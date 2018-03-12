import javax.swing.*;
import java.awt.*;

public class Puzzle
{
	private JButton[][] button = new JButton[3][4];
	
	public Puzzle(){
	
		JFrame f = new JFrame();
		JPanel p = new JPanel();

		int imageIndex = 0;
		for (int i = 0; i < 3; i++){
          		for (int j = 0; j < 4; j++){	
					ImageIcon im = new ImageIcon ("resources/Bart"+imageIndex+".jpg");
					button[i][j] = new JButton (im);
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

		public void actionPerformed(){
	
		}

}
