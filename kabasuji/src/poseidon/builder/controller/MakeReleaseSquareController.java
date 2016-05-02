package poseidon.builder.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import poseidon.builder.view.LevelBuilderView;
import poseidon.entities.LevelBuilderModel;

public class MakeReleaseSquareController implements ActionListener{

	LevelBuilderModel model;
	LevelBuilderView view;
	boolean toggle = false; 
	
	public MakeReleaseSquareController(LevelBuilderModel model, LevelBuilderView view) {
		this.model = model;
		this.view = view;
	}
	

	public void actionPerformed(ActionEvent ae)	{
		
	}

}
