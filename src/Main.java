import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import constant.C;


/**
 * This is the main class of the program.  It is responsible for taking player input
 * from the keyboard, and handling the visuals.  An alarm will be used in order to
 * call the update and paint methods at set intervals.
 * @author Alex
 *
 */
public class Main extends KeyAdapter implements MouseListener, AlarmListener, KeyListener{

	/**
	 * A reference to the Snake
	 */
	private Snake snake;

	/** 
	 * The applet for handling graphics
	 */
	private Applet applet;

	/**
	 * The Alarm for calling the update and paint methods
	 */
	private Alarm alarm;

	/**
	 * A reference to the food in the window.  Only one
	 * food item will be present at a time, so we only need
	 * one data member, instead of an array.
	 */
	private Food food;

	/**
	 * A reference to the animation speed
	 */
	private int animationSpeed = 100;

	/**
	 * The probability that a food will be generated.
	 */
	private final int foodRate = 15;

	/**
	 * A boolean to determine if the game is paused.
	 */
	private boolean paused;

	/**
	 * Keeps track of how many food items have been eaten
	 */
	private int score;

	/**
	 * Constants for game options
	 */
	private final int STARTING_SNAKE_SIZE =5, MIN_ANIMATION_SPEED = 90;



	// CONSTRUCTOR
	public Main(Applet theApplet){

		// Instantiate the reference to the applet
		applet = theApplet;
		applet.addMouseListener(this);
		applet.addKeyListener(this);

		// Create the snake
		snake =  new Snake(STARTING_SNAKE_SIZE, this);
		
		// Create the alarm
		alarm = new Alarm(this);
		alarm.start();
		alarm.setPeriod(animationSpeed);

		applet.setFocusable(true);
		
		// Initially, the game is not paused
		paused = false;
		// and the score starts at 0
		score = 0;
	}

	/**
	 * Called by the alarm.  Updates the snake, the animation time, and
	 * randomly decide if a new food item should be generated.
	 */
	public void takeNotice() {

		// If the game is paused, don't do anything
		if(!paused){

			// update the snake, and if the snake ate the food...
			if(snake.update(food)){
				
				// remove the food
				food = null;
				// add one to the score
				score += 1;
				
				// Every 5 peices of food that are eaten, increase the animation speed
				if((score%5 == 0) && (alarm.getPeriod() > MIN_ANIMATION_SPEED))
					alarm.decreasePeriod(10);
			}

			// Randomly generate a new Food item
			int rand = (int)(Math.random()*foodRate);
			if((rand == 1) && (food == null))
				dropFood();
			// and repaint the applet
			applet.repaint();
		}
	}


	/**
	 * This method just stops the alarm from notifing the main class
	 */
	public void gameOver(){
		alarm.setNotifier(null);
	}
	
	/**
	 * Paints the snake, the food, and the score.
	 * @param pane
	 */
	public void paint(Graphics pane){

		// Paint the snake
		snake.paint(pane);

		// If there is food to be eaten, paint it
		if(food != null)
			food.paint(pane);

		// Display the score in the top left corner
		pane.setColor(Color.orange);
		pane.drawString("score: "+score, 20, 20);
	}

	/**
	 * Creates a new food object and places it somewhere in the
	 * applet.  There is a one in ten chance the food will be a 
	 * BigBite, otherwise its a nibblet.
	 */
	private void dropFood(){

		int fX = (int) (Math.random()*C.WINDOW_SIZE/C.BODY_WIDTH);
		int fY = (int) (Math.random()*C.WINDOW_SIZE/C.BODY_WIDTH);

		int rand = (int) (Math.random()*10);
		
		if(rand == 0)
			food = new BigBite(fX, fY);
		else
			food = new Nibblet(fX, fY);
	}

	// KEY LISTENER METHODS
	public void keyReleased(KeyEvent key) {


		// CONTROLS
		//  a,w,s,d keys move the snake
		//  p pauses the game
		//  space bar makes the snake shoot
		switch(key.getKeyChar()){
		case 'a': snake.setDirection((C.LEFT));
		break;
		case 'w': snake.setDirection(C.UP);
		break;
		case 'd': snake.setDirection(C.RIGHT);
		break;
		case 's': snake.setDirection(C.DOWN);
		break;
		case 'p': paused = !paused;
		break;
		case ' ': snake.shoot();
		}
	}

	public void keyPressed(KeyEvent key) {}

	public void keyTyped(KeyEvent key) {}


	// MOUSE LISTENER METHODS
	public void mouseClicked(MouseEvent arg0) {}

	public void mouseEntered(MouseEvent arg0) {}

	public void mouseExited(MouseEvent arg0) {}

	public void mousePressed(MouseEvent arg0) {}

	public void mouseReleased(MouseEvent arg0) {}




} // End Main