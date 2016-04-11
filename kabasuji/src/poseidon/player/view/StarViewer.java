package poseidon.player.view;

import java.awt.Graphics;

import javax.swing.JPanel;

public class StarViewer extends JPanel {

	/**
	 * Create the panel.
	 */
	public StarViewer() {
		setLayout(null);

	}

	public void setStars(int s) {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Hello", 5, 5);
	}
	
}
