package listener;

import util.*;

/**
 *  TickListener--Interface for a class that listens for EggTimer ticks.
 *  See the EggTimer class for more details.
 *
 *   @author C. Fox
 *  @version 03/03
 */

//import ;

public interface TickListener {

      /*  public methods
       *******************/

      /**
       *  Listen for EggTimer ticks occurring every second.
       *
       *   @param timer The EggTimer producing the tick.
       */

   void tick( EggTimer timer );

} // TickListener
