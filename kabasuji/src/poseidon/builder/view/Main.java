package poseidon.builder.view;

import java.awt.EventQueue;

public class Main
{
	/**
	 * Launch the application.
	 * Starts with the splash screen and then opens to the main menu
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Thread.sleep(1000);
					LevelBuilderView application = new LevelBuilderView();
					application.getBuilder().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
