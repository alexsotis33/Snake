import java.awt.Color;
import java.awt.Graphics;

import constant.C;


/**
 * The Nibblet class is a child class of Food.  It blue in color.  If this type of 
 * food is eaten, the Snake gains one link in size. 
 * @author Alex
 *
 */
public class Nibblet extends Food{

	/**
	 * The constructor sets the location of the food.
	 * @param theX
	 * @param theY
	 */
	public Nibblet(int theX, int theY) {
		super(theX, theY);
	}

	/**
	 * Paints the food to the applet
	 */
	public void paint(Graphics pane){
		pane.setColor(Color.cyan);
		pane.fillOval(getX()*C.BODY_WIDTH + (C.BODY_WIDTH-C.NIBBLET_SIZE)/2,
				getY()*C.BODY_WIDTH + (C.BODY_WIDTH-C.NIBBLET_SIZE)/2, C.NIBBLET_SIZE, C.NIBBLET_SIZE);
	}
	
	/**
	 * Called from the main class to know which type of Food is present.
	 */
	public int getFoodType(){
		return C.NIB;
	}
}
