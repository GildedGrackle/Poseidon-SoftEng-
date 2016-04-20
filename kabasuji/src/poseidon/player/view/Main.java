package poseidon.player.view;

import java.awt.EventQueue;

import poseidon.entities.LevelPlayerModel;
import poseidon.entities.XMLHandler;

public class Main
{
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					// Try to load progress from file
					// If file not present/valid then make new one {0,0,0}
					XMLHandler xmlHandler = new XMLHandler();
					int[] progress =  xmlHandler.loadProgressXML("progressXML.xml");
					progress = (progress == null)? (new int[]{0,0,0}) : (progress);
					
					// TODO Make actual levels, until then use this to generate examples
					xmlHandler.makeExampleLevels();

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
