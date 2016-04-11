package poseidon.builder.view;

import java.awt.EventQueue;

public class Main
{
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelBuilderView application = new LevelBuilderView();
					application.getBuilder().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
