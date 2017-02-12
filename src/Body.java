import java.awt.Color;
import java.awt.Graphics;

import constant.C;

/**
 * Each Body object will represent each segment of the Snake.  Each Body
 * will have an x, y location, and a reference to the next body in the chain.
 */
public class Body {

	/**
	 * The coordinate location.
	 */
	private int x, y;

	/**
	 * A Reference to the next Body in the Snake.
	 */
	private Body next;
	
	/**
	 * Bodies are drawn with a 3D rectangle, and this boolean
	 * determines if the Body is up or down.
	 */
	private boolean up;




	/**
	 * Constructor takes the coordinate location as a parameter.
	 * up is set to false automatically so the last segment of the
	 * Snake is always down.
	 * @param theX
	 * @param theY
	 */
	public Body(int theX, int theY){
		x = theX;
		y = theY;
		
		up = false;
	}
	
	

	/**
	 * This method is called when a Food is eaten. 
	 * This method recursively calls itself until
	 * it has reached the end of the Snake. 
	 */
	public void setNext(){
		
		// Set up to true since this Body is no longer the last.
		up = true;
		
		// Basis of the recursion.  Sets next to be a new Body
		if(next == null)
			next = new Body(x,y);
		else
			next.setNext();
	}

	/**
	 * Called to the head Body by the Snake class. This method will be distinct from
	 * the paintBody class in order to paint the head of the snake differently.
	 * @param pane
	 */
	public void paint(Graphics pane) {
		
		if(next != null)  			// If there is another Body in the Snake...
			next.paintBody(pane);	// Paint that Body
		
		// Paints the head of the snake with a red dot in the middle
		pane.fill3DRect(x*C.BODY_WIDTH, y*C.BODY_WIDTH, C.BODY_WIDTH, C.BODY_WIDTH, up);
		pane.setColor(Color.red);
		pane.fillOval(x*C.BODY_WIDTH, y*C.BODY_WIDTH, C.BODY_WIDTH, C.BODY_WIDTH);

	}
	
	/**
	 * Recursively paints each Body in the Snake.
	 * @param pane
	 */
	private void paintBody(Graphics pane){
		
		
		if(next != null)			// If there is another Body in the Snake...
			next.paintBody(pane);	// Paint that Body
		pane.fill3DRect(x*C.BODY_WIDTH, y*C.BODY_WIDTH, C.BODY_WIDTH, C.BODY_WIDTH, up);
		
	}


	/**
	 * The update method will be called by Snake and only applies to the head segment.
	 * It takes a parameter which specifies the last directional key pressed by the 
	 * player.  This method also calls the collision method to check if the head 
	 * ran into the wall or its own tail. This method will return true if there
	 * was a collision and false otherwise.
	 * @param direction
	 * @return
	 */
	public boolean update(int direction) {
		
		// Update the other segments
		if(next!= null)
			next.update(this);
		
		// Update location based on direction
		switch(direction){
		case C.LEFT: x -= 1;
		break;
		case C.RIGHT: x += 1;
		break;
		case C.UP: y -= 1;
		break;
		case C.DOWN: y += 1;
		break;
		}

		// Check if there was collision
		boolean gameOver = false;
		if(collision()){
			gameOver = true;
		}
		// Return false if no collision, true otherwise
		return gameOver;
	}
	
	/**
	 * This method is the update method for all of the Body segments
	 * other than the head.  It takes the Body segment in front of it
	 * as a parameter, and updates its own location to that of the 
	 * Body in front of it.
	 */
	public void update(Body front) {
		
		// Recursively call this method
		if(next != null)
			next.update(this);

		// Update location
		x = front.getX();
		y = front.getY();
	}
	

	/**
	 * This method checks if the head hit the walls, then calls the other
	 * collision method to check if the head ran into its tail.  
	 * Returns true if there was a collision and false otherwise.
	 * @return
	 */
	private boolean collision(){
		
		// check if head hit the edge
		if((x < 0) || (x >= C.WINDOW_SIZE/C.BODY_WIDTH) || 
				(y < 0) || (y >=C.WINDOW_SIZE/C.BODY_WIDTH))
			return true;
		
				
		
		// check if head hit a body part
		boolean result = false;
		
		if(next != null)
			result = next.collision(x,y);
		
		
		return result;
	}
	
	/**
	 * This method checks if this Body's location is identical with
	 * that of the parameters headX and headY.  It is public because
	 * the Snake class will call this method to check if the head ate 
	 * any Food.  If the location is identical with the two integer
	 * parameters, returns true.  Recursively calls until the end of
	 * the Snake is reached.
	 * @param headX
	 * @param headY
	 * @return
	 */
	public boolean collision(int headX, int headY){
		
		boolean result = false;
		
		// If the body is at the same location as the parameters
		if((headX == x) && (headY == y))
			// Return true
			return true;
		
		// If there is another Body segment in the snake
		if(next != null)
			// Call this method on the next Body
			result = next.collision(headX, headY);
		
		return result;
	}
	

	/**
	 * Returns the x location
	 */
	public int getX(){
		return x;
	}

	/**
	 * Returns the y location
	 */
	public int getY(){
		return y;
	}


}// End Body
