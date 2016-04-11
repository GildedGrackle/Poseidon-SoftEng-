package poseidon.common.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PieceView extends JPanel
{
//	Piece model;  // The model of the Piece
//	JPanel display;  // What is displayed

	/**
	 * Create the panel.
	 */
	public PieceView()
	{
		setLayout(null);
		setPreferredSize(new Dimension(60,60));
	}
	
	
	/**
	 *  Getters and setters
	 */
//	public JPanel getDisplay()
//	{
//		return display;
//	}

	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.red);
		// For each Point in model, draw a square at that location
		for(int i = 0; i < 3; i++)
		{
			g.fillRoundRect(10*i, 0, 10, 10, 3, 3);
		}
		for(int i = 0; i < 3; i++)
		{
			g.fillRoundRect(0, 10 + 10*i, 10, 10, 3, 3);
		}
	}
}
