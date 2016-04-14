package poseidon.player.view;

import java.awt.EventQueue;

public class Main
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					LevelPlayerModel model = new LevelPlayerModel();
					LevelPlayerView window = new LevelPlayerView(model);
					window.kabasuji.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

}
