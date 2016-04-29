package poseidon.builder.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ScrollPaneConstants;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import poseidon.builder.controller.CancelEditController;
import poseidon.builder.controller.SetBullpenController;

/**
 *  The edit playable bullpen screen, to change the pieces avaible to the player during actual gameplay.
 *  
 * @author Alex Titus
 */
public class EditPlayableBullpenView extends JPanel implements IBuilderScreen
{
	/** The top-level GUI object. */
	LevelBuilderView view;
	/** The panel where piece numbers are input. */
	PlayBullpenInputView pieceCountsPanel;
	/** The button used to discard changes and return to the build level screen (BuilderView). */
	JButton cancelButton;
	/** The button used to change the playable bullpen and return to the build level screen (BuilderView). */
	JButton doneButton;

	
	/**
	 *  Constructor.
	 *  
	 *  @param view  top-level GUI object
	 */
	public EditPlayableBullpenView(LevelBuilderView view)
	{
		this.view = view;
		setLayout(null);
		
		JLabel title = new JLabel("Edit Playable Bullpen");
		title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		title.setBounds(120, 20, 440, 45);
		add(title);
		
		// Create input panel
		BuilderView bview = (BuilderView) view.getCurrentScreen();
		pieceCountsPanel = new PlayBullpenInputView(bview.getModel().getPlayableBullpen());
		JScrollPane scrollPane = new JScrollPane(pieceCountsPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(235, 90, 300, 420);
		add(scrollPane);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		cancelButton.setBounds(35, 550, 145, 45);
		cancelButton.addActionListener(new CancelEditController(view));
		add(cancelButton);
		
		doneButton = new JButton("Done");
		doneButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		doneButton.setBounds(500, 550, 145, 45);
		doneButton.addActionListener(new SetBullpenController(view, this, bview.getModel().getPlayableBullpen()));
		add(doneButton);
	}
	

	/**
	 *  Updates display when model changes.
	 *  
	 *  The model cannot change while on this screen.
	 *  @return  False
	 */
	@Override
	public Boolean modelUpdated()
	{
		return false;
	}
	
	
	/** @return  The panel with the information about bullpen piece counts. */
	public PlayBullpenInputView getPieceCountsPanel()
	{
		return pieceCountsPanel;
	}
}
