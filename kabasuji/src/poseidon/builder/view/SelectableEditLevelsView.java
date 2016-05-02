package poseidon.builder.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Scrollable;

import poseidon.builder.controller.SelectEditLevelController;
import poseidon.entities.LevelBuilderModel;
import poseidon.entities.LevelContainer;
import poseidon.player.controller.SelectLevelController;
import poseidon.player.view.StarView;

/**
 *  Creates a panel containing selectable icons representing all custom-made
 *  levels that have either been added to the game or are have only been saved.
 *  
 *  @author Alex Titus
 *
 */
public class SelectableEditLevelsView extends JPanel implements Scrollable
{
	/** The height of a level icon */
	public static final int ICON_HEIGHT = 50;
	/** The width of a level icon */
	public static final int ICON_WIDTH = 50;
	/** The distance between two level icons' sides. */
	public static final int ICON_SPACING_WIDTH = 29;
	/** The distance between two level icons' top and bottom. */
	public static final int ICON_SPACING_HEIGHT = 15;
	/** The top-level entity object, representing the game. */
	LevelBuilderModel model;
	/** The level select screen to modify. */
	EditLevelView view;
	/** The level select icons. */
	EditLevelIcon[][] buttons;
	/** Whether the displayed icons are for saved or added levels. */
	int levelType;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  The top-level entity object, representing the game
	 *  @param view  The level select screen to modify
	 *  @param levelType  Whether the screen displays saved or added levels
	 */
	public SelectableEditLevelsView(LevelBuilderModel model, EditLevelView view, int levelType)
	{
		this.model = model;
		this.view = view;
		this.levelType = levelType;
		this.buttons = new EditLevelIcon[LevelBuilderModel.NUM_GAMEMODES][];
		for(int i = 0; i < LevelBuilderModel.NUM_GAMEMODES; i++)
		{
			buttons[i] = new EditLevelIcon[model.getSavedLevels().get(i).size()];
		}
		
		// Set component details
		setLayout(null);
		int prefHeight = ICON_HEIGHT * 3 + ICON_SPACING_HEIGHT * 2 + 4;
		// Determine the largest of the three gamemodes for this levelType
		int largestSize = 0;
		for(int i = 0; i < LevelBuilderModel.NUM_GAMEMODES; i++)
		{
			if(largestSize < model.getSavedLevels().get(i).size())
			{
				largestSize = model.getSavedLevels().get(i).size();
			}
		}
		int prefWidth = ICON_WIDTH * largestSize + ICON_SPACING_WIDTH * (largestSize - 1) + 20;
		setPreferredSize(new Dimension(prefWidth, prefHeight));
		
		// Create icons
		initialize();
	}


	/**
	 *  Creates and places the icons.
	 */
	void initialize()
	{
		for(int gamemode = 0; gamemode < LevelBuilderModel.NUM_GAMEMODES; gamemode++)
		{
			int levelNumber = 0;
			for(LevelContainer lc : model.getSavedLevels().get(gamemode))
			{
				// Create a selectable icon
				buttons[gamemode][levelNumber] = new EditLevelIcon(model.getSavedLevels().get(gamemode).get(lc.getLevelNumber()));
				buttons[gamemode][levelNumber].setBounds(10 + (ICON_SPACING_WIDTH + ICON_WIDTH) * lc.getLevelNumber(),
						2 + (ICON_SPACING_HEIGHT + ICON_HEIGHT) * gamemode, ICON_WIDTH, ICON_HEIGHT);
				buttons[gamemode][levelNumber].setBackground(new Color(0, 191, 255));
				buttons[gamemode][levelNumber].addActionListener(new SelectEditLevelController(view));
				add(buttons[gamemode][levelNumber]);
				levelNumber++;
			}
		}
	}
	
	
	/**
	 *  Update the display when the underlying model has changed.
	 *  
	 *  Changes all icon colors to blue.
	 */
	public void modelUpdated()
	{
		resetSelectColors();
	}
	
	
	/**
	 *  Sets all EditLevelIcons to have Color.cyan backgrounds.
	 */
	public void resetSelectColors()
	{
		for(EditLevelIcon[] type : buttons)
		{
			for(EditLevelIcon icon : type)
			{
				icon.setBackground(new Color(0, 191, 255));
			}
		}
	}
	
	
	/** @return  Size large enough to view 5 icons placed left-to-right with gaps. */
	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		return new Dimension(ICON_WIDTH * 5 + ICON_SPACING_WIDTH * 4 + 20,
				ICON_HEIGHT * 3 + ICON_SPACING_HEIGHT * 2 + 4);
	}


	/**
	 *  Whenever the user clicks the track, move three icons over.
	 *  
	 *  @param visibleRect  The view area visible within the viewport
	 *  @param orientation  Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL
	 *  @param direction  Less than zero to scroll up/left, greater than zero for down/right
	 *  @return  Distance to scroll three icons. */
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
		// Enough to move three icons
		int maximum = 3 * (ICON_WIDTH + ICON_SPACING_WIDTH);
		//                          to snap to next piece
		return maximum - visibleRect.x % (ICON_WIDTH + ICON_SPACING_WIDTH);
	}


	/**
	 *  Whether the panel should be constrained by the height of the viewing window.
	 *  
	 *  @return  False - don't want to be constrained.
	 */
	@Override
	public boolean getScrollableTracksViewportHeight()
	{
		return false;
	}


	/**
	 *  Whether the panel should be constrained by the width of the viewing window.
	 *  
	 *  @return  False - don't want to be constrained.
	 */
	@Override
	public boolean getScrollableTracksViewportWidth()
	{
		return false;
	}


	/**
	 *  Whenever the user clicks on one of the arrow buttons, move one icon over.
	 *  
	 *  @param visibleRect  The view area visible within the viewport
	 *  @param orientation  Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL
	 *  @param direction  Less than zero to scroll up/left, greater than zero for down/right
	 *  @return  Distance to scroll to the next icon.
	 */
	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
		// Scroll the width of one StarView, snapping to next StarView
		int maximum = (ICON_WIDTH + ICON_SPACING_WIDTH);
		return maximum - visibleRect.x % (ICON_WIDTH + ICON_SPACING_WIDTH);
	}

}
