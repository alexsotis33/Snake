import java.awt.*;
import constant.C;

/**
 * The Snake class is made up of Body objects and will move around in
 * the applet.  It is capable of shooting, eating, and growing.
 * @author Alex
 *
 */
public class Snake {

	/**
	 * The Body segments making the Snake will be stored in a linked list,
	 * and this is the reference to the head.
	 */
	private Body head;

	/**
	 * An array of projectiles
	 */
	private Projectile[] projectiles;

	/**
	 * The direction the Snake is moving.  The package
	 * 'constants' assigns the numbers 1 through 4 as the
	 * four directions in order to keep it uniform through
	 * the program.
	 */
	private int direction;
	
	/**
	 * An integer determining how many links will be gained
	 * after eating each food.
	 */
	private int growthRate;

	/**
	 * This boolean is set to true after the Snake's direction is
	 * altered.  Its purpose is to temporarily disable another key
	 * from changing its direction until the update function is called.
	 */
	private boolean justMoved;

	/**
	 * A reference to the main class. Used to end the game when 
	 * the Snake collides with the wall or its tail.
	 */
	private Main main;

	/**
	 * Constant for the maximum number of projectiles allowed at once.
	 */
	private final int MAX_PROJECTILES = 3;


	/**
	 * The constructor takes the starting size of the Snake, and
	 * a reference to the main class, instantiates these data
	 * members, then creates the Body of the Snake.
	 * @param startingSize
	 * @param theMain
	 */
	public Snake(int startingSize, Main theMain){

		main = theMain;

		// Obviously we have not moved at the start of the game
		justMoved = false;

		projectiles = new Projectile[MAX_PROJECTILES];
		
		// Start moving right
		direction = C.RIGHT;
		
		// Only add 1 link per food eate
		growthRate = 1;

		// Create the head at this location
		head = new Body(0,5);

		// And add a number of segments depending on the
		// starting size
		for(int i=1; i < startingSize; i++)
			head.setNext();

	}


	/**
	 * Changes the direction of the Snake under two conditions:
	 * 1) if the Snake is moving up or down, it can only
	 *    be changed to left and right. (and vice versa)
	 * 2) if the Snake's direction has not already been changed
	 *    before the update function was called.
	 * 
	 * @param theDirection
	 */
	public void setDirection(int theDirection){

		if((direction%2 != theDirection%2) && !justMoved){
			direction = theDirection;
			justMoved = true;
		}
	}

	/**
	 * Called by the main class when the space bar is pressed.
	 * Adds a Projectile to the array of projectiles as long as
	 * there are less than 3 already.
	 */
	public void shoot(){

		// for indexing the array of projectiles
		int i = 0;
		boolean keepGoing = true;

		// Stop if we add a projectile or if we reach the end
		// of the array
		while((i < MAX_PROJECTILES) && (keepGoing)){

			if(projectiles[i] == null){
				projectiles[i] = new Projectile(head.getX(), head.getY(), direction);
				keepGoing = false;
			}
			i++;
		}
	}

	/**
	 * Paints the Body objects and the projectiles.
	 */
	public void paint(Graphics pane) {

		// we want our snake green
		pane.setColor(Color.green);
		
		// Paint the head of the Snake, it will recurively paint the rest
		head.paint(pane);

		// Paint the projectiles
		for(Projectile projectile: projectiles){
			if(projectile != null)
				projectile.paint(pane);
		}
	}

	/**
	 * The update function takes a Food as a parameter to check if the
	 * Snake ate the food.  This function also updates the Body objects
	 * making up the Snake, and the projectiles.  Returns true if the 
	 * food given as parameter was eaten.
	 * 
	 * @param someFood
	 * @return
	 */
	public boolean update(Food someFood){
		
		// Update the Body's, and if it collided with the tail or the
		// wall, let the main class know the game is over.
		if(head.update(direction))
			main.gameOver();

		// Update the Projectiles
		for(int i=0; i<MAX_PROJECTILES; i++){

			if((projectiles[i] != null) && (projectiles[i].update()))
				projectiles[i] = null;
		}

		boolean result = false;

		// Now that the Snake has moved, we can allow for the user to 
		// change the direction again.
		justMoved = false;

		// If there is food on the screen...
		if(someFood != null){
			
			// We need to do different things depending on the type of food
			switch(someFood.getFoodType()){
			
			// If the food is a Nibblet
			case C.NIB:

				// If the Snake made contact with the food
				if(head.collision(someFood.getX(), someFood.getY())){
					
					// Add Body segments
					for(int i=0; i < growthRate; i++)
						head.setNext();


					result = true;

				}
				break;
				
			// If the food is a BigBite
			case C.BITE:
				
				// If the Snake made contact with the food
				if((head.getX() == someFood.getX()) && (head.getY() == someFood.getY())){
						// The player loses!
						main.gameOver();
				}
				
				// If any of the Projectiles hit the BigBite...
				for(int i=0; i<MAX_PROJECTILES; i++){
					if((projectiles[i] != null) && (projectiles[i].didHit(someFood.getX(), someFood.getY()))){
												
						// Add 3 links
						for(int j=0; j<3; j++)
							head.setNext();

						
						result = true;
					}
				}
			}
		}
		return result;
	}



}  // End Snake