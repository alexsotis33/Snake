import java.applet.Applet;
import java.awt.*;
import constant.C;

/**
 * The Driver is a child class of Applet.  Its main purpose is to handle the double
 * buffering and instantiate the main class.
 * @author Alex Sotis
 *
 */
public class Driver extends Applet{

	

	/**
	 * The main class of the program which will use this Applet 
	 * for displaying graphics.
	 */
	private Main main;
	
	/**
	 * The background color.
	 */
	private Color color;
	
	
	/**
	 * Change the size of the window and instantiate the data members.
	 */
	public void init(){
		this.setSize(new Dimension(C.WINDOW_SIZE,C.WINDOW_SIZE));
		main = new Main(this);
		color = new Color(40, 40, 70);
	}
	
	/**
	 * Set the background, and paint the Main class.
	 */
	public void paint(Graphics pane){
		 
		setBackground(color);
		
		main.paint(pane);
	}
	
	
//*******************************************************************************
//* The following code is not my own work.  It was given to me by Timothy		*
//* Leonard to allow double buffering.  It effectively eliminates flickering. 	*
//* The rest of the code in this program is my original work, and will			*
//* still function if this portion is removed. 									*
//*******************************************************************************
	
	
	private Image dbImage;
	private Graphics dbg;
	 /** Update - Method, implements double buffering */
    public void update (Graphics g)
    {

        dbImage = createImage (this.getSize().width, this.getSize().height);
        dbg = dbImage.getGraphics ();
        // initialize buffer
        if (dbImage == null)
        {}

        // clear screen in background
        dbg.setColor (getBackground ());
        dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

        // draw elements in background
        dbg.setColor (getForeground());
        paint (dbg);

        // draw image on the screen
        g.drawImage (dbImage, 0, 0, this);

    }
	
}// End Driver



