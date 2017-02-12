import java.awt.Color;
import java.awt.Graphics;
import constant.C;

/**
 * The Food class is abstract to allow for different types of
 * food with different effects. 
 * @author Alex
 *
 */
public abstract class Food {

	/**
	 * Location of the Food.  
	 */
	private int x, y;
	
	/**
	 * Constructor just instantiates the x, y coordinates.
	 * @param theX
	 * @param theY
	 */
	public Food(int theX, int theY){
		
		x = theX;
		y = theY;
	}
	
	/**
	 * Paints the food to the applet at its location
	 * @param pane
	 */
	public abstract void paint(Graphics pane);
	
	/**
	 * This method returns the integer corresponding to the type of
	 * Food.  These are stored in the package 'constant' so they
	 * are uniform through the program.
	 * @return
	 */
	public abstract int getFoodType();
	
	/**
	 * Returns the x location of the Food
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Returns the y location of the Food
	 * @return
	 */
	public int getY(){
		return y;
	}
} // End Food

