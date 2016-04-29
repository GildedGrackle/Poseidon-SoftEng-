package poseidon.builder.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.Scrollable;
import javax.swing.text.NumberFormatter;

import poseidon.common.view.BullpenView;
import poseidon.entities.Bullpen;
import poseidon.entities.Piece;
import poseidon.entities.PieceContainer;
import poseidon.entities.PieceFactory;
import poseidon.entities.Point;

/**
 *  Creates a GUI where the user can specify how many of which types of pieces to add to the level.
 *  
 * @author Alex Titus
 */
public class PlayBullpenInputView extends JPanel implements Scrollable
{
	/** The model of the playable bullpen. */
	Bullpen model;
	/** The input fields, one for each piece. */
	JFormattedTextField[]	inputs;
	
	
	/**
	 *  Constructor.
	 *  
	 *  @param model  the playable bullpen
	 */
	public PlayBullpenInputView(Bullpen model)
	{
		this.model = model;
		
		// Set component details
		setLayout(null);
		int prefHeight = BullpenView.PIECE_SIZE * 35 + 2 * 35;
		int prefWidth = BullpenView.PIECE_SIZE + 4 + 75;
		setPreferredSize(new Dimension(prefWidth, prefHeight));
		
		// Add JFormattedTextFields
		inputs = new JFormattedTextField[35];
		for(int i = 0; i < 35; i++)
		{
			NumberFormat limitFormat = NumberFormat.getIntegerInstance();
			limitFormat.setMinimumIntegerDigits(1);
			limitFormat.setMaximumIntegerDigits(2);
			NumberFormatter limitFormatter = new NumberFormatter(limitFormat);
			limitFormatter.setMinimum(new Integer(0));
			limitFormatter.setMaximum(new Integer(99));
			inputs[i] = new JFormattedTextField(limitFormatter);
			inputs[i].setValue(getPieceCount(i + 1));
			inputs[i].setBounds(BullpenView.PIECE_SIZE + 4 + 13, BullpenView.PIECE_SIZE * i + 20, 49, 20);
			add(inputs[i]);
		}
		
	}
	
	
	/**
	 *  Counts the number of pieces in the playable bullpen matching a piece number.
	 *  
	 *  @param index  the piece number
	 *  @return  The number of pieces in the playable bullpen with the piece number.
	 */
	int getPieceCount(int index)
	{
		int count = 0;
		PieceFactory fac = new PieceFactory();
		Piece counting = fac.getPiece(index);
		
		for(PieceContainer pc : model.getPieces())
		{
			if(pc.getPiece().equals(counting))
			{
				count++;
			}
		}
		
		return count;
	}

	
	/**
	 *  Overrides JPanel's paintComponent() method, drawing the Board.
	 *  
	 *  @param g  Graphics object used to render this object
	 */
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		drawPieces(g);
	}
	
	
	/** 
	 *  Draws pieces currently in the Bullpen.
	 *  
	 *  This method shouldn't be called directly. It is automatically called
	 *  during repaintComponent().
	 *  
	 *  @param g  Graphics object passed from repaintComponent()
	 */
	void drawPieces(Graphics g)
	{
		Graphics drawer = g.create();
		PieceFactory pieces = new PieceFactory();
		Color fill = Color.LIGHT_GRAY;
		Color border = Color.BLACK;
		
		// Draw Pieces
		for(int i = 0; i < 35; i++)
		{
			// Pick a color for each piece
			switch(i % 5)
			{
			case 0:
				fill = Color.yellow;
				border = Color.orange;
				break;
			case 1:
				fill = Color.pink;
				border = Color.red;
				break;
			case 2:
				fill = Color.cyan;
				border = Color.blue;
				break;
			case 3:
				fill = Color.green;
				border = Color.darkGray;
				break;
			case 4:
				fill = Color.orange;
				border = Color.red;
				break;
			}
			
			// Now draw piece
			Piece p = pieces.getPiece(i+1);
			int offsetY = BullpenView.PIECE_SIZE * i + 2;
			for(Point pt : p.getPiece())
			{
				int pieceOffsetY = 2 + BullpenView.SQUARE_SIZE * pt.getRow();
				int pieceOffsetX = 2 + BullpenView.SQUARE_SIZE * pt.getCol();
				drawer.setColor(fill);
				drawer.fillRoundRect(pieceOffsetX, pieceOffsetY + offsetY,
						BullpenView.SQUARE_SIZE, BullpenView.SQUARE_SIZE, 3, 3);
				drawer.setColor(border);
				drawer.drawRoundRect(pieceOffsetX, pieceOffsetY + offsetY,
						BullpenView.SQUARE_SIZE, BullpenView.SQUARE_SIZE, 3, 3);
			}
		}
	}
	
	
	/**
	 *  @return  Size large enough to display 7 pieces arranged vertically.
	 */
	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		int prefHeight = BullpenView.PIECE_SIZE * 7 + 4 * 7;
		int prefWidth = BullpenView.PIECE_SIZE + 4 + 75;
		return new Dimension(prefWidth, prefHeight);
	}


	/**
	 *  Whenever the user clicks the track, move five pieces up or down.
	 *  
	 *  @param visibleRect  The view area visible within the viewport
	 *  @param orientation  Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL
	 *  @param direction  Less than zero to scroll up/left, greater than zero for down/right
	 *  @return  Distance to scroll to location five pieces up or down.
	 */
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
		// Scroll enough to move two Pieces in the Bullpen, snapping to next Piece
		int maximum = 5 * BullpenView.PIECE_SIZE;

		return maximum - visibleRect.y % BullpenView.PIECE_SIZE;
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
	 *  Whenever the user clicks on one of the arrow buttons, move two pieces up or down.
	 *  
	 *  @param visibleRect  The view area visible within the viewport
	 *  @param orientation  Either SwingConstants.VERTICAL or SwingConstants.HORIZONTAL
	 *  @param direction  Less than zero to scroll up/left, greater than zero for down/right
	 *  @return  Distance to scroll to location two pieces up or down.
	 */
	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation,
			int direction)
	{
	// Scroll the width of one Piece in the Bullpen, snapping to next Piece
			int maximum = 2 * BullpenView.PIECE_SIZE;
			return maximum - visibleRect.y % BullpenView.PIECE_SIZE;
	}

	
	/** @return  The input boxes, with information about the piece counts. */
	public JFormattedTextField[] getInputs()
	{
		return inputs;
	}
}
