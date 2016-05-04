package poseidon.player.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import poseidon.entities.LevelContainer;

import javax.swing.SwingConstants;

/**
 *  Renders a JButton with attached stars representing an associated level's high score.
 *  
 *  @author Morgan Hopeman
 *  @author Alex Titus
 */
public class StarView extends JButton
{
	/** The Level Container associated with this StarView. */
	LevelContainer levelContainer;
	
	Image star, greyStar, locked;

	/**
	 *  Constructor.
	 *  
	 *  @param lc  the level container to associate with
	 */
	public StarView(LevelContainer lc)
	{
		this.levelContainer = lc;
		
		setLayout(null);
		
		
		JLabel levelNumLabel = new JLabel();
		if(lc.getLevel() != null) {
			levelNumLabel.setText("" + lc.getLevelNumber());
		}
		else {
			levelNumLabel.setText("File Not Found");
		}
		
		levelNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		levelNumLabel.setBounds(0, 0, 60, 60);
		add(levelNumLabel);
		
		JLabel firstStar = new JLabel("1");
		firstStar.setBounds(0, 60, 20, 20);
		firstStar.setOpaque(true);
		add(firstStar);
		
		JLabel secondStar = new JLabel("2");
		secondStar.setBounds(20, 60, 20, 20);
		secondStar.setOpaque(true);
		add(secondStar);
		
		JLabel thirdStar = new JLabel("3");
		thirdStar.setBounds(40, 60, 20, 20);
		thirdStar.setOpaque(true);
		add(thirdStar);
		
		try {
			star = ImageIO.read(getClass().getClassLoader().getResource("images/Star.png"));
			greyStar = ImageIO.read(getClass().getClassLoader().getResource("images/Star-grey.png"));
			firstStar.setIcon(new ImageIcon(greyStar));
			secondStar.setIcon(new ImageIcon(greyStar));
			thirdStar.setIcon(new ImageIcon(greyStar));
		} catch (IOException e) {
		}
	
		
		
		// Display the appropriate number of stars
		switch(lc.getScore()) {
		case 0:
			try {
				star = ImageIO.read(getClass().getClassLoader().getResource("images/Star.png"));
				greyStar = ImageIO.read(getClass().getClassLoader().getResource("images/Star-grey.png"));
				firstStar.setIcon(new ImageIcon(greyStar));
				secondStar.setIcon(new ImageIcon(greyStar));
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
			}
		}
		
	
	
	/**
	 *  Constructs the locked level icon
	 */
	public StarView()
	{
		setLayout(null);
		
		JLabel lockedLabel = new JLabel("");
		lockedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lockedLabel.setBounds(0, 0, 60, 80);
		lockedLabel.setBackground(Color.DARK_GRAY);
		add(lockedLabel);
		
		try {
			locked = ImageIO.read(getClass().getClassLoader().getResource("images/lock.png"));
			lockedLabel.setIcon(new ImageIcon(locked));
		} catch (IOException e) {
		}
	}
	
	
	/** @return  The LevelContainer associated with this object. */
	public LevelContainer getLevelContainer()
	{
		return levelContainer;
	}
}
