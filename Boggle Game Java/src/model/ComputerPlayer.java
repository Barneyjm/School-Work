package model;

import java.util.*;


/**
 * ComputerPlayer - Computer Player subclass for Player
 * 
 *  Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 * 
 * @author James Barney, Nathan DiDomenico, Michael Norton
 * @version pa03 (10/15/2012)
 */
public class ComputerPlayer extends Player
{
	/**
	 * Explicit Value Constructor
	 */
	public ComputerPlayer( Board b )
	{
		super( b );

	} // constructor

	
	/**
	 * checkList - Implements the abstract checkList method inherited from
	 * Player - in this case we'll validate the words from the Dictionary
	 * against the board and populate the appropriate lists
	 */
	public void checkList()
	{
		Iterator<String> myList = dictionary.iterator();

		validateWords( myList );

		list = valid; // since these are the same, use an alias here

	} // method checkList

} // class ComputerPlayer