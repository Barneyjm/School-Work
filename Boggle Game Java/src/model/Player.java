package model;

import java.util.*;

/**
 * Player - This class represents a player of the Boggle game.  This class 
 * keeps track of the round and game scores and the words found by the user.
 *
 * Modifications: 1. PA2 -- Changed all ArrayList references to StringSet 
 *                          (linked list)
 *                2. PA2 -- Added reset() method
 *                3. PA2 -- added getScore() and computeScore()
 *                4. PA2 -- added reference to dictionary (sent via
 *                          constructor)
 *                5. PA2 -- add word to dictionary when adding to list
 *                6. PA2 -- added remove() method - remove word from 
 *                          dictionary and string set if added during this
 *                          session
 *                7. PA2 -- added computeScore() method
 *                8. PA3 -- made class abstract
 *                9. PA3 -- added method validateWords()
 *               10. PA3 -- added abstract method checkList()
 *               11. PA3 -- added valid, and invalid lists
 *               12. PA3 -- changed visibility of lists/dictionary from 
 *                          private to protected
 *               13. PA3 -- added finals for Iterator types
 *               14. PA3 -- added getList and getIterator methods
 *               15. PA3 -- added unique list and setUniqueList()
 *               16. PA3 -- modified remove() to remove from all lists
 *               17. PA3 -- changed reference to Dictionary to use 
 *                          Singleton method
 *
 * @author James Barney, Nathan DiDomenico, Michael Norton
 * @version pa03 (10/16/2012), pa02 (10/12/2012), pa01 (9/09/2012)
 */
abstract public class Player
{
	//----------------------------------------------------------------------
	// Declarations
	//----------------------------------------------------------------------
	private int[] scores;

	private Board board;				// added for PA3

	protected Dictionary dictionary; 	// added for PA2
	protected StringSet list;
	protected StringSet invalid;		// added for PA3
	protected StringSet	unique;			// added for PA3
	protected StringSet valid;			// added for PA3

	public static final int ROUND = 0;
	public static final int GAME = 1;

	public static final int LIST = 0;		// added for PA3
	public static final int	VALID = 1;		// added for PA3
	public static final int	INVALID = 2;	// added for PA3
	public static final int	UNIQUE = 3;		// added for PA3


	/**
	 * Explicit value constructor
	 */
	public Player( Board b )
	{
		list = new StringSet(); 	// create empty StringSet
		invalid = null;				// added for PA3
		unique = null;				// added for PA3
		valid = null;				// added for PA3

		scores = new int[ 2 ];

		board =	b;								 // added for PA3
		dictionary = Dictionary.getDictionary(); // added for PA2
		                                         // changed for PA3
		
		scores[ROUND] =	0;			// init scores to 0
		scores[GAME] = 0;

	} // constructor


	/**
	 * add - Add word to player's list of words
	 *
	 * ** modified for PA2 - added dictionary.add(s) & difficulty code
	 * ** added 2nd parameter to get difficulty level
	 *
	 * @param the word to add, the difficulty level
	 */
	public void add( String s, int difficulty )
	{
		Random rand = new Random();

		list.add(s);

		// factor in difficulty level when deciding to add to dictionary
		if ( rand.nextInt( 10 ) < difficulty )
			dictionary.add( s );

	} // method add


	/**
	 * computeScore - Computes the current round and game scores
	 * 
	 * ** Added for PA2
	 * ** Modified for PA3 - added Iterator for StringSet - use unique list
	 *
	 * @param set containing the list of unique words
	 */
    public void computeScore()
	{
		if (unique != null)
		{
			int points = 0;
			Iterator<String> myList = unique.iterator();
			String word = "";

			while ( myList.hasNext() ) 
			{
				word =  myList.next().trim();

				switch ( word.length() )
				{
					case 0: case 1: case 2: points += 0; break;
					case 3: case 4:			points += 1; break;
					case 5:					points += 2; break;
					case 6:					points += 3; break;
					case 7:					points += 5; break;
					default:				points += 11;

				} // end switch

			} // end while

			scores[ ROUND ] += points;
			scores[ GAME ] += points;

			
		} // end if

	} // method computeScore

    
    public void refreshScore()
	{
		if (unique != null)
		{
			int points = 0;
			Iterator<String> myList = unique.iterator();
			String word = "";

			while ( myList.hasNext() ) 
			{
				word =  myList.next().trim();

				switch ( word.length() )
				{
					case 0: case 1: case 2: points += 0; break;
					case 3: case 4:			points += 1; break;
					case 5:					points += 2; break;
					case 6:					points += 3; break;
					case 7:					points += 5; break;
					default:				points += 11;

				} // end switch

			} // end while
			scores[ GAME ] = scores[ GAME ] + points - scores[ ROUND ] ;
			
			scores[ ROUND ] = points;
			

		} // end if

	} // method computeScore

	/**
	 * getList - Returns the StringSet specified by the parameter
	 * 
	 * ** added for PA3 **
	 *
	 * @param which list
	 * @return the list specified by the parameter
	 */
	public StringSet getList(int whichOne)
	{
		StringSet stringSet = null;

		if (whichOne >= LIST && whichOne <= UNIQUE)
		{
			switch (whichOne)
			{
				case LIST:	stringSet = list; break;
				case VALID: stringSet = valid; break;
				case INVALID: stringSet = invalid; break;
				case UNIQUE: stringSet = unique;

			} // end switch

		} // end if

		return stringSet;

	} // method getList


	/**
	 * getListSize - Return the size of the StringSet
	 *
	 * @return int
	 */
	public int getListSize()
	{
		return list.size();

	} // method getListSize


	/**
	 * getScore - Return the scores
	 *
	 * ** Added for PA2 **
	 *
	 * @param the scores array
	 */
	 public int[] getScore()
	 {
		 return scores;

	 } // method getWord


	/**
	 * getWord - Return a single word from the list
	 *
	 * @param int
	 * @return String
	 */
	 public String getWord(int index)
	 {
		 return list.get( index );

	 } // method getWord


	/**
	 * remove - Remove words from lists/dictionary
	 *
	 * ** added for PA2
	 * ** modified for PA3 to remove from all lists
	 *
	 * @param the word to remove
	 */
	public void remove( String s )
	{
		if ( s != null )
		{
			// remove from dictionary only if added in this round
			if ( list.remove(s) )
				dictionary.remove( s );

			// remove from all other lists (if there)
			if ( valid != null )
				valid.remove( s );
			if ( invalid != null )
				invalid.remove( s );
			if ( unique != null )
				unique.remove( s );

		} // end if

	} // method remove


	/**
	 * resetGame - Reset for a new game
	 *
	 * ** Added for PA3 **
	 */
	public void resetGame()
	{
		resetRound();
		if(unique != null)
			unique.clear();
		scores[ GAME ] = 0;

	 } // method resetGame


	/**
	 * resetRound - Reset for a new round
	 *
	 * ** Added for PA2 **
	 * ** renamed from reset to resetRound in PA3
	 * ** modified PA3 to set valid, invalid lists to null
	 */
	 public void resetRound()
	 {
		 list.clear();
		 
		 valid = null;
		 invalid = null;

		 scores[ ROUND ] = 0;

	 } // method resetRound

	/**
	 * setUniqueSet - Set the unique StringSet to that sent via the 
	 * parameter
	 *
	 * ** Added for PA3 **
	 * 
	 * @param the new unique StringSet
	 */
	 public void setUniqueSet( StringSet in )
	 {
		if ( in != null )
			unique = in;

	 } // method setUniqueSet



	/******************* private/protected methods ************************/

	/**
	 * validateWords - Implements the portion of validateWords common to all 
	 * players - each will send an Iterator object on which to operate - 
	 * child methods will override if need be - creates a StringSet 
	 * consisting of valid words
	 *
	 * ** Added for PA3
	 *
	 * @param the Iterator containing words to validate
	 */
	protected void validateWords( Iterator<String> iterator )
	{
		String word = "";

		invalid = new StringSet();
		valid = new StringSet();

		while ( iterator.hasNext() )
		{
			word = iterator.next();

			if ( board.hasWord( word) )
				valid.add( word );
			else
				invalid.add( word );

		} // end while

	} // method validateWords

	/***************************** abstract methods ************************/

	/**
	 * checkList - Set up environment for validateWords method - Generate 
	 *             correct Iterator and handle list pointers
	 *
	 * ** Added for PA3
	 *
	 */
	abstract public void checkList();

} // class Player
