package poseidon.player.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollBar;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;

import poseidon.common.view.BoardView;
import poseidon.common.view.BullpenView;
import poseidon.player.controller.BackPlayerController;

public class LevelView extends JPanel
{
	LevelPlayerView game;  // The top-level GUI object
	BullpenView bullpen;
	BoardView board;
	JButton resetButton;
	JButton quitButton;
	JButton finishButton;
	JButton rotateCWButton;
	JButton rotateCCWButton;
	JButton flipButton;
	JScrollPane bullpenContainer;
	JLabel levelTitle;
	JLabel scoreView;
	JLabel countdownView;

	/**
	 * Create the panel.
	 */
	public LevelView(LevelPlayerView view)
	{
		game = view;
		setLayout(null);
		
		levelTitle = new JLabel("<Level Name>");
		levelTitle.setOpaque(true);
		levelTitle.setFocusable(false);
		levelTitle.setBackground(new Color(0, 191, 255));
		levelTitle.setFont(new Font("Lucida Handwriting", Font.PLAIN, 35));
		levelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		levelTitle.setBounds(150, 0, 380, 75);
		add(levelTitle);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(0, 191, 255));
		leftPanel.setBounds(0, 0, 150, 615);
		add(leftPanel);
		leftPanel.setLayout(null);
		
		resetButton = new JButton("Reset");
		resetButton.setBounds(10, 290, 130, 40);
		leftPanel.add(resetButton);
		resetButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		quitButton = new JButton("Quit");
		quitButton.addActionListener(
				new BackPlayerController(game));
		quitButton.setBounds(10, 340, 130, 40);
		leftPanel.add(quitButton);
		quitButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		finishButton = new JButton("<html><center>Finish<br>Level</center></html>");
		finishButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		finishButton.setBounds(10, 508, 130, 87);
		leftPanel.add(finishButton);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBackground(new Color(0, 191, 255));
		rightPanel.setBounds(530, 0, 150, 615);
		add(rightPanel);
		rightPanel.setLayout(null);
		
		rotateCCWButton = new JButton("<");
		rotateCCWButton.setBounds(10, 125, 45, 45);
		rightPanel.add(rotateCCWButton);
		
		rotateCWButton = new JButton(">");
		rotateCWButton.setBounds(93, 125, 45, 45);
		rightPanel.add(rotateCWButton);
		
		flipButton = new JButton("Flip");
		flipButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		flipButton.setBounds(10, 75, 130, 40);
		rightPanel.add(flipButton);
		
		scoreView = new JLabel("Score:");
		scoreView.setFont(new Font("Tahoma", Font.PLAIN, 25));
		scoreView.setBounds(10, 250, 115, 25);
		rightPanel.add(scoreView);
		
		countdownView = new JLabel("<html>Countdown:<br><center>Number</center></html>");
		countdownView.setBackground(Color.WHITE);
		countdownView.setBounds(10, 340, 140, 55);
		rightPanel.add(countdownView);
		countdownView.setHorizontalAlignment(SwingConstants.LEFT);
		countdownView.setFont(new Font("Tahoma", Font.PLAIN, 25));
		
		bullpen = new BullpenView();
		bullpenContainer = new JScrollPane(bullpen);
		bullpenContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		bullpenContainer.setBounds(160, 85, 360, 70);
		add(bullpenContainer);

		board = new BoardView();
		board.setBounds(160, 195, 361, 361);
		add(board);
	}
}
