package poseidon.player.view;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	/** Images of stars for score*/
	Image star, greyStar;
	

	/**
	 * Create the panel. All stars start as empty sockets
	 */
	public ScoreView(LevelModel model) {
		this.model = model;
		
		// Create objects in panel
		setLayout(null);
		setBackground(new Color(0, 191, 255));
		
		ImageIcon emptyStar = new ImageIcon("images/Star-grey.gif");  
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
		switch(model.getScore())
		{
		case 3:
			try {
				star = ImageIO.read(getClass().getClassLoader().getResource("images/Star.png"));
				greyStar = ImageIO.read(getClass().getClassLoader().getResource("images/Star-grey.png"));
				firstStar.setIcon(new ImageIcon(star));
				secondStar.setIcon(new ImageIcon(star));
				thirdStar.setIcon(new ImageIcon(star));
			} catch (IOException e) {
			}
			break;
		case 2:
			try {
				star = ImageIO.read(getClass().getClassLoader().getResource("images/Star.png"));
				greyStar = ImageIO.read(getClass().getClassLoader().getResource("images/Star-grey.png"));
				firstStar.setIcon(new ImageIcon(star));
				secondStar.setIcon(new ImageIcon(star));
				thirdStar.setIcon(new ImageIcon(greyStar));
			} catch (IOException e) {
			}
			break;
		case 1:
			try {
				star = ImageIO.read(getClass().getClassLoader().getResource("images/Star.png"));
				greyStar = ImageIO.read(getClass().getClassLoader().getResource("images/Star-grey.png"));
				firstStar.setIcon(new ImageIcon(star));
				secondStar.setIcon(new ImageIcon(greyStar));
				thirdStar.setIcon(new ImageIcon(greyStar));
			} catch (IOException e) {
			}
			break;
		default:  // Catches 0 and any error scores
			try {
				star = ImageIO.read(getClass().getClassLoader().getResource("images/Star.png"));
				greyStar = ImageIO.read(getClass().getClassLoader().getResource("images/Star-grey.png"));
				firstStar.setIcon(new ImageIcon(greyStar));
				secondStar.setIcon(new ImageIcon(greyStar));
				thirdStar.setIcon(new ImageIcon(greyStar));
			} catch (IOException e) {
			}
		}
		
		this.repaint();
		
		return true;
	}
	
}
