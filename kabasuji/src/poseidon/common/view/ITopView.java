package poseidon.common.view;

import javax.swing.JFrame;

/**
 *  The top-level GUI object for both the Level Player and Builder.
 *  
 *  @author Alex Titus
 */
public interface ITopView
{
	/** @return  The application frame. */
	public JFrame getFrame();
	
	/** @return  Indicator of whether the object is for Builder or Player. */
	public Boolean isGame();
}
