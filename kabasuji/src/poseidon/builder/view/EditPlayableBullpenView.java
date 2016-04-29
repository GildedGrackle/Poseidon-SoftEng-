package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class EditPlayableBullpenView extends JPanel
{
	LevelBuilderView application;  // The top-level GUI object

	/**
	 *  Constructor.
	 */
	public EditPlayableBullpenView(LevelBuilderView view)
	{
		application = view;
		setLayout(null);
		
		JLabel title = new JLabel("Edit Playable Bullpen");
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(120, 20, 440, 45);
		add(title);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 90, 610, 420);
		add(scrollPane);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCancel.setBounds(35, 550, 145, 45);
		add(btnCancel);
		
		JButton doneButton = new JButton("Done");
		doneButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		doneButton.setBounds(500, 550, 145, 45);
		add(doneButton);
	}
}
