package poseidon.player.view;

import java.awt.EventQueue;

import poseidon.entities.LevelPlayerModel;
import poseidon.entities.XMLHandler;

/**
 *  Starts the Kabasuji Level Player application.
 *  
 *  @author Alex Titus
 */
public class Main
{
	
	/**
	 * Launch the application.
	 * Starts with the splash screen and then opens to the main menu.
	 * 
	 * @param args  the command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Try to load progress from file
					// If file not present/valid then make new one {0,0,0}
					int[] progress =  XMLHandler.loadProgressXML("progress.xml");
					progress = (progress == null)? (new int[]{0,0,0}) : (progress);
					
					// Initialize the top-level model and view, and then display the main menu
					LevelPlayerModel model = new LevelPlayerModel(progress, null);
					LevelPlayerView window = new LevelPlayerView(model);
					window.kabasuji.setVisible(true);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
