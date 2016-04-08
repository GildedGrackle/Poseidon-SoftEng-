package poseidon.builder.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LevelBuilder extends JFrame {

	private JPanel contentPane;
	private final JButton btnBack = new JButton("Back");
	private final JButton btnLightningLevel = new JButton("Lightning Level");
	private final JLabel lblBuilderType = new JLabel("Builder: Type");
	private final JTextField textField = new JTextField();
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LevelBuilder frame = new LevelBuilder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LevelBuilder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel BuilderMenu = new JPanel();
		contentPane.add(BuilderMenu, "name_1460072467403057000");
		BuilderMenu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kabasuji Level Builder");
		lblNewLabel.setBounds(117, 40, 442, 63);
		lblNewLabel.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		BuilderMenu.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New Level");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton.setBounds(262, 235, 145, 52);
		BuilderMenu.add(btnNewButton);
		
		JButton btnEditLevel = new JButton("Edit Level");
		btnEditLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnEditLevel.setBounds(255, 334, 159, 52);
		BuilderMenu.add(btnEditLevel);
		
		JButton btnNewButton_1 = new JButton("Quit ");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnNewButton_1.setBounds(264, 441, 141, 57);
		BuilderMenu.add(btnNewButton_1);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnAbout.setBounds(262, 141, 145, 52);
		BuilderMenu.add(btnAbout);
		
		JPanel AboutBuilder = new JPanel();
		contentPane.add(AboutBuilder, "name_1460074043511448000");
		AboutBuilder.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("About");
		lblNewLabel_1.setBounds(277, 21, 115, 49);
		lblNewLabel_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		AboutBuilder.add(lblNewLabel_1);
		
		JTextPane txtpnHereIsSome = new JTextPane();
		txtpnHereIsSome.setText("Here is some text about how to make builder work");
		txtpnHereIsSome.setBounds(34, 94, 598, 374);
		AboutBuilder.add(txtpnHereIsSome);
		btnBack.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		btnBack.setBounds(34, 529, 148, 86);
		AboutBuilder.add(btnBack);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		btnNext.setBounds(484, 529, 148, 86);
		AboutBuilder.add(btnNext);
		
		JPanel NewLevel = new JPanel();
		contentPane.add(NewLevel, "name_1460074040199478000");
		NewLevel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("New Level");
		lblNewLabel_2.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		lblNewLabel_2.setBounds(225, 6, 219, 101);
		NewLevel.add(lblNewLabel_2);
		btnLightningLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnLightningLevel.setBounds(243, 271, 184, 60);
		NewLevel.add(btnLightningLevel);
		
		JButton btnPuzzleLevel = new JButton("Puzzle Level");
		btnPuzzleLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnPuzzleLevel.setBounds(243, 149, 184, 60);
		NewLevel.add(btnPuzzleLevel);
		
		JButton btnReleaseLevel = new JButton("Release Level");
		btnReleaseLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnReleaseLevel.setBounds(243, 392, 184, 60);
		NewLevel.add(btnReleaseLevel);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnBack_1.setBounds(23, 528, 120, 84);
		NewLevel.add(btnBack_1);
		
		JPanel EditLevel = new JPanel();
		contentPane.add(EditLevel, "name_1460072467415808000");
		EditLevel.setLayout(null);
		
		JPanel BuilderView = new JPanel();
		contentPane.add(BuilderView, "name_1460075630913251000");
		BuilderView.setLayout(null);
		
		JButton button = new JButton("<");
		button.setBounds(555, 163, 44, 42);
		BuilderView.add(button);
		
		JButton button_1 = new JButton("Flip");
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		button_1.setBounds(555, 95, 95, 56);
		BuilderView.add(button_1);
		
		JButton button_2 = new JButton(">");
		button_2.setBounds(605, 163, 44, 42);
		BuilderView.add(button_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(134, 249, 402, 346);
		BuilderView.add(panel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(134, 79, 402, 134);
		BuilderView.add(scrollPane);
		lblBuilderType.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		lblBuilderType.setBounds(192, 22, 285, 45);
		BuilderView.add(lblBuilderType);
		
		JLabel label = new JLabel("Countdown:");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label.setBounds(548, 228, 116, 42);
		BuilderView.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(548, 263, 116, 76);
		BuilderView.add(panel_1);
		
		JButton btnHint = new JButton("Hint");
		btnHint.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnHint.setBounds(552, 376, 109, 45);
		BuilderView.add(btnHint);
		
		JButton btnChooseShapes = new JButton("Choose Shapes");
		btnChooseShapes.setBounds(548, 448, 117, 88);
		BuilderView.add(btnChooseShapes);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnUndo.setBounds(13, 159, 109, 45);
		BuilderView.add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRedo.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnRedo.setBounds(13, 225, 109, 45);
		BuilderView.add(btnRedo);
		
		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblSize.setBounds(46, 302, 54, 30);
		BuilderView.add(lblSize);
		textField.setBounds(76, 344, 46, 29);
		BuilderView.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(13, 344, 46, 29);
		BuilderView.add(textField_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(56, 350, 22, 16);
		BuilderView.add(lblX);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnQuit.setBounds(13, 405, 117, 56);
		BuilderView.add(btnQuit);
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		btnSave.setBounds(13, 502, 117, 66);
		BuilderView.add(btnSave);
	}
}
