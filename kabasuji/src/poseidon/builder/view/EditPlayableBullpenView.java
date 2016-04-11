package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;

public class EditPlayableBullpenView extends JPanel
{
	LevelBuilderView application;  // The top-level GUI object

	/**
	 * Create the panel.
	 */
	public EditPlayableBullpenView(LevelBuilderView view)
	{
		application = view;
		setLayout(null);
		
		JButton doneButton = new JButton("Done");
		doneButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		doneButton.setBounds(500, 550, 130, 45);
		add(doneButton);

	}

}
