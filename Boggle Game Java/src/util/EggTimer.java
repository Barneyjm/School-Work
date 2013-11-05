package util;


import java.util.*;

import listener.*;

/**
 *  EggTimer--Counts down in seconds to 0. The EggTimer is given the time
 *  period it must count when created, and it immediately starts counting.
 *  Once it gets to 0 the EggTimer is defunct and a new one must be created 
 *  to count once more. The EggTimer can be stopped at any time, but it is 
 *  then defunct and cannot be restarted.
 *
 *  Clients implementing the TickListener interface can register themselves
 *  as tick listeners, which means that their tick() operations will be
 *  called every time the EggTimer ticks (every second).
 *
 *  Clients can interrogate the EggTimer to find out how many seconds are 
 *  left at any time. They can also get a String version of the time left
 *  suitable for display.
 *
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 * 
 *  Note that this timer is very accurate because it uses the 
 *  java.util.Timer to provide clock ticks; using javax.swing.Timer
 *  is not very accurate at all.
 *
 *  Class invariant: (timer != null) iff (0 < secondsLeft)
 *                   listeners != null
 *
 *  Modifications: 03/03 Add listener registration and notification to
 *                       the base timer functionality--C. Fox
 *                 06/05 Add generics to Set/Hashset creation to make
 *                       Java 1.5 compliant - also reduced imports to
 *                       a single line (import entire package rather 
 *                       than each individual class
 *
 *  @author C. Fox, modified by M. Norton to make Java 1.5 complient
 *  @version 06/05, 03/03 (original)
 */
public class EggTimer extends TimerTask {

      /*  public static attributes
       *****************************/

      /*  private static attributes
       ******************************/

      /*  attributes
       ***************/

   private Timer timer;                  // provides one pulse every second
   private int secondsLeft;              // for counting down from whatever 
   private Set<TickListener> listeners;  // tick listeners notified every tick

      /*  constructors
       *****************/

      /**
       *  Creates a one-time EggTimer that starts right away and counts
       *  down from the parameter, in seconds.
       *
       *   @param secondsToCount The value from which it counts down.
       */

   public EggTimer( int secondsToCount ) {

      secondsLeft = secondsToCount;
      timer = (0 < secondsLeft) ? new Timer() : null;
      if ( timer != null ) timer.scheduleAtFixedRate(this,1000,1000);
      listeners = new HashSet<TickListener>();

   } // EggTimer

      /*  public methods
       *******************/

      /**
       *  Respond to an alert from the java.util.Timer object. This is
       *  a method required in extending the java.TimerUser class.
       *
       *  The seconds left counter is decremented. If the EggTimer runs out
       *  the Timer is stopped and forgotten.
       */

   public void run() {

      secondsLeft--;
      if ( secondsLeft <= 0 ) stop();
      notifyListeners();

   } // run

      /**
       *  Add a tick listener to the set of tick listeners.
       *
       *   @param l The tick listener added to the set
       */

   public void addTickListener( TickListener l ) {
      listeners.add( l );
   }

      /**
       *  Remove a tick listener from the set of tick listeners.
       *
       *   @param l The tick listener removed from the set
       */

   public void removeTickListener( TickListener l ) {
      listeners.remove( l );
   }

      /**
       *  Fetch the seconds left.
      /**
       *  Fetch the seconds left.
       *  @return How many seconds are left on this EggTimer.
       */

   public int getSecondsLeft() { return secondsLeft; }

      /**
       *  Fetch the time left as a String in the format m:ss.
       *  @return The time left as a String.
       */

   public String getTimeLeft() {

      int minutesPart = secondsLeft / 60;   // minutes portion of String
      int secondsPart = secondsLeft % 60;   // seconds portion of String

      return   String.valueOf( minutesPart )
             + ((secondsPart<10) ? ":0" : ":")
             + String.valueOf( secondsPart );

   } // getTimeLeft

      /**
       *  Stop this EggTimer. It may not be restarted.
       */

   public void stop() {

      if ( timer != null ) {
         timer.cancel();
         timer = null;
      }

   } // stop

      /*  private methods
       ********************/

      /**
       *  Iterate through the list of tick listeners and notify each one
       *  that the timer has ticked.
       */

   private void notifyListeners() {

      Iterator<TickListener> i = listeners.iterator();
      while ( i.hasNext() ) {
         TickListener l = (TickListener)i.next();
         l.tick( this );
      }

   } // notifyListeners

} // EggTimer
