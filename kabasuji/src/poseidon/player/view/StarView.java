package poseidon.player.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import poseidon.entities.LevelContainer;

import javax.swing.SwingConstants;

public class StarView extends JButton
{
	LevelContainer levelContainer;  // The Level Container associated with this StarView

	/**
	 *  Construct selectable icon
	 */
	public StarView(LevelContainer lc)
	{
		this.levelContainer = lc;
		
		setLayout(null);
		
		
		JLabel levelNumLabel = new JLabel();
		if(lc.getLevel() != null)
		{
			levelNumLabel.setText("" + lc.getLevelNumber());
		}
		else
		{
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
		
		// Display the appropriate number of stars
		switch(lc.getScore())
		{
		case 0:
			firstStar.setBackground(Color.gray);  // TODO instead of background colors, load empty and filled star images
			secondStar.setBackground(Color.gray);
			thirdStar.setBackground(Color.gray);
			break;
		case 1:
			firstStar.setBackground(Color.yellow);
			secondStar.setBackground(Color.gray);
			thirdStar.setBackground(Color.gray);
			break;
		case 2:
			firstStar.setBackground(Color.yellow);
			secondStar.setBackground(Color.yellow);
			thirdStar.setBackground(Color.gray);
			break;
		case 3:
			firstStar.setBackground(Color.yellow);
			secondStar.setBackground(Color.yellow);
			thirdStar.setBackground(Color.yellow);
			break;
		}
		
	}
	
	
	/**
	 *  Constructs the locked level icon
	 */
	public StarView()
	{
		setLayout(null);
		
		JLabel lockedLabel = new JLabel("No");
		// TODO load the locked level icon instead
//		ImageIcon lockedLevel = ___.getLockedLevelImage();
//		JLabel lockedLabel = new JLabel(lockedLevel);
		lockedLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lockedLabel.setBounds(0, 0, 60, 80);
		lockedLabel.setBackground(Color.DARK_GRAY);
		add(lockedLabel);
	}
	
	
	public LevelContainer getLevelContainer()
	{
		return levelContainer;
	}
}
