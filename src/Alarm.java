/*
	Author:	Gaston

	Alarm.java
									A l a r m
									=========

	This class defines an alarm, which "beeps" after a resetable delay.
	On each "beep," the alarm will notify the object registered when the timer
		is instantiated..
 */


public class Alarm
extends Thread
{
	private AlarmListener whoWantsToKnow;	//	The object to notify
	//		in case of emergency

	private int delay = 0;					//	The first beep will occur
	//		without any delay

	//
	//	C o n s t r u c t o r s
	//
	public Alarm()
	{
		super("Alarm");						//	With the default constructor,
		whoWantsToKnow = null;				//		nobody will be notified
	}

	public Alarm(AlarmListener someBody)
	{
		super("Alarm");						//	In general,  we expect to know who
		whoWantsToKnow = someBody;			//		(i.e., which object) to notify
	}

	public Alarm(String name, AlarmListener someBody)
	{
		super(name);						//	We can also give a name to the alarm
		whoWantsToKnow = someBody;
	}
	
	
	public void setNotifier(AlarmListener someBody){
		whoWantsToKnow = someBody;
	}

	//
	//	The setPeriod method will set or reset the period by which beeps will occur.
	//	Note that the new period will be used after reception of the following beep.
	//
	public void setPeriod(int someDelay)
	{										//	Note:  The period should be expressed
		delay = someDelay;					//				in milliseconds
	}

	//
	// The decreasePeriod method will lower the period by the value taken as a 
	// parameter, as long as the resulting period will be greater than or equal to one.
	//
	public void decreasePeriod(int adjust){
		if(delay - adjust >= 1)
			delay -= adjust;
	}

	//
	// The increasePeriod method will lower the period by the value taken as a
	// a parameter.
	//
	public void increasePeriod(int adjust){
		delay += adjust;
	}
	
	//
	//	The setPeriodicBeep method will keep on notifying the "object in charge"
	//		at set time intervals.
	//	Note that the time interval can be changed at any time through setPeriod.
	//
	private void setPeriodicBeep(int someDelay)
	{
		delay = someDelay;

		try {
			while (true){						//	For as long as we have energy,
				Thread.sleep(delay);			//		first we wait
				if (whoWantsToKnow != null)		//		then we notify the
					whoWantsToKnow.takeNotice();//		responsible party
			}									//		(if anybody wants to hear)
		}
		catch(InterruptedException e) {
			System.err.println("Oh, oh ... " + e.getMessage());
		}
	}

	//
	//	The alarm is a Thread, and the run method gets the thread started, and running.
	//
	public void run()
	{
		setPeriodicBeep(delay);
	}

	public int getPeriod() {
		return delay;
	}

}	//	end of class Alarm
