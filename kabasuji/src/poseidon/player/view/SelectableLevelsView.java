package poseidon.player.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.Scrollable;

import poseidon.entities.LevelContainer;
import poseidon.entities.LevelPlayerModel;
import poseidon.player.controller.SelectLevelController;

/**
 *  Creates a scrollable panel containing selectable icons for each level in a gamemode.
 *  
 *  @author Alex Titus
 *
 */
public class SelectableLevelsView extends JPanel implements Scrollable
{
	/** The height of a level icon */
	public static final int ICON_HEIGHT = 80;
	/** The width of a level icon */
	public static final int ICON_WIDTH = 60;
	/** The distance between two level icons' left edges. */
	public static final int ICON_SPACING = 35;
	/** The top-level entity object, representing the game. */
	LevelPlayerModel model;
	/** The level select screen to modify. */
	LevelSelectView view;
	/** The level select buttons. */
	StarView[] buttons;
	/** Which gamemode's levels should be displayed. */
	int gameMode;

	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the top-level entity object, representing the game
	 *  @param view  the level select screen to modify.
	 *  @param gameMode  which gamemode's levels should be displayed
	 */
	public SelectableLevelsView(LevelPlayerModel model, LevelSelectView view, int gameMode)
	{
		this.model = model;
		this.view = view;
		this.gameMode = gameMode;
		this.buttons = new StarView[model.getLevels().get(gameMode).size()];
		
		// Set component details
		setLayout(null);
		int prefHeight = ICON_HEIGHT + 4;
		int prefWidth = ICON_WIDTH * model.getLevels().get(gameMode).size()
				+ ICON_SPACING * (model.getLevels().get(gameMode).size() - 1) + 20;
		setPreferredSize(new Dimension(prefWidth, prefHeight));
		
		// Create level select buttons
		initialize();
	}
	
	
	/**
	 *  Creates and places the StarView icons.
	 */
	void initialize()
	{
		int i = 0;
		for(LevelContainer lc : model.getLevels().get(gameMode))
		{
			// If the level is unlocked
			if(lc.getLevelNumber() <= model.getCurrentLevel()[gameMode])
			{
				// Then create a selectable icon
				buttons[i] = new StarView(model.getLevels().get(gameMode).get(lc.getLevelNumber()));
				buttons[i].setBounds(10 + (ICON_SPACING + ICON_WIDTH) * lc.getLevelNumber(), 2, ICON_WIDTH, ICON_HEIGHT);
				buttons[i].setBackground(Color.cyan);
				buttons[i].addActionListener(new SelectLevelController(view));
				add(buttons[i]);
			}
			else  // Level is locked
			{
				// Then create a nonselectable "level locked" icon
				buttons[i] = new StarView();
				buttons[i].setBounds(10 + (ICON_SPACING + ICON_WIDTH) * lc.getLevelNumber(), 2, ICON_WIDTH, ICON_HEIGHT);
				buttons[i].setBackground(Color.cyan);
				buttons[i].setEnabled(false);
				add(buttons[i]);
			}
			
			i++;
		}
	}
	
	
	/**
	 *  Resets all level select buttons to have blue backgrounds.
	 */
	public void resetSelectColors()
	{
		for(StarView sv : buttons)
		{
			sv.setBackground(Color.cyan);
		}
	}

	
	/** @return  Size large enough to view 5 StarViews placed left-to-right with gaps. */
	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		return new Dimension(ICON_WIDTH * 5 + ICON_SPACING * 4 + 20, ICON_HEIGHT + 4);
	}


	/**
	 *  Whenever the user clicks the track, move three StarViews over.
	 *  
	 *  @param visibleRect  The view area visible within the viewport
	 *  @param orientation  Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL
	 *  @param direction  Less than zero to scroll up/left, greater than zero for down/right
	 *  @return  Distance to scroll three StarViews. */
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
		// Enough to move three StarViews
		int maximum = 3 * (ICON_WIDTH + ICON_SPACING);
		//                          to snap to next piece
		return maximum - visibleRect.x % (ICON_WIDTH + ICON_SPACING);
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
	 *  Whenever the user clicks on one of the arrow buttons, move one StarView over.
	 *  
	 *  @param visibleRect  The view area visible within the viewport
	 *  @param orientation  Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL
	 *  @param direction  Less than zero to scroll up/left, greater than zero for down/right
	 *  @return  Distance to scroll to the next StarView.
	 */
	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
		// Scroll the width of one StarView, snapping to next StarView
		int maximum = (ICON_WIDTH + ICON_SPACING);
		return maximum - visibleRect.x % (ICON_WIDTH + ICON_SPACING);
	}

}
