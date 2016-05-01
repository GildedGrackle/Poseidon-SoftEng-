package poseidon.player.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import poseidon.common.view.IModelUpdated;
import poseidon.entities.LevelModel;

public class ScoreView extends JPanel implements IModelUpdated{
	LevelModel model;  // The state of the Level
	JLabel firstStar;  // The left-most star in the score
	JLabel secondStar;  // The middle star in the score
	JLabel thirdStar;  // The right-most star in the score
	

	/**
	 * Create the panel. All stars start as empty sockets
	 */
	public ScoreView(LevelModel model) {
		this.model = model;
		
		// Create objects in panel
		setLayout(null);
		setBackground(Color.white);
		
		ImageIcon emptyStar = new ImageIcon("images/emptyStar.gif");  // TODO Filename subject to change
		firstStar = new JLabel(emptyStar);
		firstStar.setBounds(3, 0, 35, 35);
		add(firstStar);
		
		secondStar = new JLabel(emptyStar);
		secondStar.setBounds(41, 0, 35, 35);
		add(secondStar);
		
		thirdStar = new JLabel(emptyStar);
		thirdStar.setBounds(79, 0, 35, 35);
		add(thirdStar);

	}
	
	
	/**
	 *  Updates the display when the score changes.
	 */
	@Override
	public Boolean modelUpdated()
	{
		ImageIcon emptyStar = new ImageIcon("images/emptyStar.gif");  // TODO Filename subject to change
		ImageIcon filledStar = new ImageIcon("images/filledStar.gif");
		
		switch(model.getScore())
		{
		case 3:
			thirdStar.setIcon(filledStar);
			secondStar.setIcon(filledStar);
			firstStar.setIcon(filledStar);
			break;
		case 2:
			thirdStar.setIcon(emptyStar);
			secondStar.setIcon(filledStar);
			firstStar.setIcon(filledStar);
			break;
		case 1:
			thirdStar.setIcon(emptyStar);
			secondStar.setIcon(emptyStar);
			firstStar.setIcon(filledStar);
			break;
		default:  // Catches 0 and any error scores
			thirdStar.setIcon(emptyStar);
			secondStar.setIcon(emptyStar);
			firstStar.setIcon(emptyStar);
		}
		
		this.repaint();
		
		return true;
	}
	
}
