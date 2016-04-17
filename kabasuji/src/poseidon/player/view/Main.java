package poseidon.player.view;

import java.awt.EventQueue;

import poseidon.entities.LevelPlayerModel;

public class Main
{
	
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run(){
				try{
					int dummy[];  // TODO instead of this, read progress from file
					dummy = new int[3];
					LevelPlayerModel model = new LevelPlayerModel(dummy, null);
					LevelPlayerView window = new LevelPlayerView(model);
					window.kabasuji.setVisible(true);}
				catch(Exception e){
					e.printStackTrace();}
			}
		});
	}
	
}
