package model;

import java.util.*;


/**
 * HumanPlayer - Human Player subclass for Player
 *
 * @author James Barney, Nathan DiDomenico, Michael Norton
 * @version pa03 (10/15/2012)
 */
public class HumanPlayer extends Player
{
	/**
	 * Explicit Value Constructor
	 */
	public HumanPlayer (Board b )
	{
		super( b );

	} // constructor

	/**
	 * checkList - Implements the abstract checkList method inherited 
	 * from Player - in this case we'll check the words from the StringSet
	 * and populate the appropriate lists
	 */
    public void checkList()
	{
		Iterator<String> myList = list.iterator();

		validateWords( myList );

	} // method validateWords

} // class HumanPlayer