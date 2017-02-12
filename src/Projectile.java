import java.awt.Graphics;

import constant.C;

/**
 * The Projectile class will be used by the Snake class to shoot
 * certain types of food.  
 * @author Alex
 *
 */
public class Projectile {
	
	
	/**
	 * The coordinate location of the Projectile.
	 */
	private int x, y;
	
	/**
	 * An integer determining the direction the Projectile is moving.
	 * The 'constant' package assigns 1 through 4 with each direction
	 * so it is uniform through the program.
	 */
	private int direction;
	
	/**
	 * The speed of the Projectiles.
	 */
	private final double SPEED = 2;
	
	/**
	 * The radius of the Projectiles.
	 */
	private final int RADIUS = 8;
	
	
	/**
	 * The constructor takes the x, y location, and the direction
	 * as parameters, and instantiates the data members.
	 * @param startingX
	 * @param startingY
	 * @param theDirection
	 */
	public Projectile(int startingX, int startingY, int theDirection){
	
		x = startingX;
		y = startingY;
		direction = theDirection;
	}
	
	/**
	 * This method returns true if the Projectile made contact with the
	 * x, y location given as parameters.
	 * @param targetX
	 * @param targetY
	 * @return
	 */
	public boolean didHit(int targetX, int targetY){
		
		boolean result = false;
		
		// Since the Projectile moves 2 units at each update,
		// we use tX and tY to represent the other location which
		// the Projectile jumped over to determine if it made contact
		// with the target.
		int tX = 0, tY = 0;
		
		// Assign tX or tY depending on the direction
		switch(direction){
		case C.LEFT: tX = x-1;
		break;
		case C.RIGHT: tX = x+1;
		break;
		case C.UP: tY = y - 1;
		break;
		case C.DOWN: tY = y+1;
		break;
		}
		
		// If the Projectile shares the same coordinate location as the target, return true.
		if( ( (x == targetX) || (tX == targetX) ) && ( (y == targetY) || (tY == targetY) ) )
			result = true;
		
		return result;
	}
	
	/**
	 * Returns the x coordinate of the Projectile
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Returns the y coordinate of the Projectile
	 * @return
	 */
	public int getY(){
		return y;
	}
	
	
	/**
	 * Paints the Projectile to the applet
	 * @param pane
	 */
	public void paint(Graphics pane){
		
		pane.fillOval(x*C.BODY_WIDTH + (C.BODY_WIDTH-RADIUS)/2, 
				y*C.BODY_WIDTH + (C.BODY_WIDTH-RADIUS)/2, RADIUS, RADIUS);
	}
	
	/**
	 * The update function simply changes the x, y coordinates depending 
	 * on the direction.  Since Projectiles do not change direction, unlike
	 * the Snake, the update function takes no parameters. Returns true if
	 * the Projectile made contact with the wall, so we know to delete it, 
	 * false otherwise.
	 * @return
	 */
	public boolean update(){
		switch(direction){
		case C.LEFT: x -= SPEED;
		break;
		case C.RIGHT: x += SPEED;
		break;
		case C.UP: y -= SPEED;
		break;
		case C.DOWN: y += SPEED;
		break;
		}
		
		
		boolean result = false;
		if((x < 0) || (x >= C.WINDOW_SIZE/C.BODY_WIDTH) || 
				(y < 0) || (y >=C.WINDOW_SIZE/C.BODY_WIDTH))
			result = true;
		
		
		return result;
	}

}// End Projectile