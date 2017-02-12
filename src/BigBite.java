import java.awt.Color;
import java.awt.Graphics;

import constant.C;

/**
 * The BigBite class is a child class of Food.  It is slightly bigger than the normal Food, 
 * and yellow in color.  If this type of food is eaten, you lose the game. This Food needs to
 * be shot with a Projectile in order to disappear.
 * @author Alex
 *
 */
public class BigBite extends Food{

	/**
	 * Constructor takes the location as parameters, and calls the parent constructor.
	 * @param theX
	 * @param theY
	 */
	public BigBite(int theX, int theY) {
		super(theX, theY);
	}

	/**
	 * Paints the Food to the Applet
	 */
	public void paint(Graphics pane) {
		pane.setColor(Color.YELLOW);
		pane.fillOval(getX()*C.BODY_WIDTH + (C.BODY_WIDTH-C.BIG_BITE_SIZE)/2,
				getY()*C.BODY_WIDTH + (C.BODY_WIDTH-C.BIG_BITE_SIZE)/2,
				C.BIG_BITE_SIZE, C.BIG_BITE_SIZE);
	}
	
	/**
	 * Called from the main class to know which type of Food is present.
	 */
	public int getFoodType(){
		return C.BITE;
	}

}
