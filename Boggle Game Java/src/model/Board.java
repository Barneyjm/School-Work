package model;
import java.util.*;

/**
 * Board- This class stores the game board (4x4 array). It is responsible 
 * for shuffling and mixing letters and for providing client access to
 * individual letters.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 * 
 * Modifications: PA3 - Added hasWord methods (overloaded) to recursively 
 *                      search for a word on the board
 *                PA3 - Added setBoard to allow static board for testing
 *
 * @author James Barney, Nathan DiDomenico, Michael Norton
 * @version @version pa03 (10/15/20129), pa01 (9/09/2012)
 */
public class Board
{
	//----------------------------------------------------------------------
	// Declarations
	//----------------------------------------------------------------------
	private char[][] board;
	private Random random;	// we only want a single Random object!!!

	public final int X_VALUE = 4;
	public final int Y_VALUE = 4;

	public final String LETTERS = 	"JKQYZ" +
				          			"BCFGMPV" +
				          			"BCFGMPV" +
									"DUWX" +
									"DUWX" +
									"DUWX" +
									"HLR" +
									"HLR" +
									"HLR" +
									"HLR" +
									"HLR" +
									"AINSO" +
									"AINSO" +
									"AINSO" +
									"AINSO" +
									"AINSO" +
									"AINSO" +
									"ET" +
									"ET" +
									"ET" +
									"ET" +
									"ET" +
									"ET" +
									"ET" +
									"ET" +
									"ET" +
									"ET";



	/**
	 * Default constructor
	 */
	public Board()
	{
		board = new char[ X_VALUE ][ Y_VALUE ];
		random = new Random();

		mix();

	} // default constructor


	/*************************** public methods ***************************/

	/**
	 * getCell - Returns the requested cell to the client.  If the cell does 
	 *           not exist, returns null character (which is the same as 
	 *           null)
	 *
	 * @param x coordinate
	 * @param y coordinate
	 * @return char
	 */
	public char getCell( int x, int y )
	{
		char retVal = (char)0;

		if ( isInBounds(x, y) )
		{
			retVal = board[ x ][ y ];
		}

		return retVal;

	} // method getCell


	/**
	 * hasWord - returns true if the word sent is on the board
	 * 
	 * ** added for PA3 **
	 *
	 * @param String
	 * @return boolean
	 */
	public boolean hasWord ( String word )
	{
		boolean wordFound = false;

		if (word != null)
		{
			boolean[][] check = new boolean[4][4];
			int row = 0;
			int col = 0;

			// init check array to false
			for ( int i = 0; i < 4; i++ )
				for ( int j = 0; j < 4; j++ )
					check[ i ][ j ] = false;

			// loop through the board to see if the word sent is on the board
			while ( row < 4 && !wordFound )
			{
				while ( col < 4 && !wordFound )
				{
					wordFound = hasWord( word, row, col, check );
					col++;

				} // end while

				row++;
				col = 0; 		// reset columnt

			} // end while

		} // end if

		return wordFound;

	} // method has word


	/**
	 * hasWord - Recursive version of hasWord (overloaded)
	 *
	 * ** added for PA3 **
	 *
	 * @param String
	 * @param boolean[][]
	 * @return boolean
	 */
	public boolean hasWord ( String word, int row, int col,
		boolean[][] check )
	{
		boolean wordFound = false;
		
		if ( word != null )
		{
			word = word.toUpperCase(); // force to upper case for comparison

			// check to see if the first character matches the tile on the
			// board - Note: this is base case # 1
			if (word.charAt( 0 ) == getCell( row, col ) )
			{
				// make sure we have not used this tile already
				// Note: this is base case #2
				if ( !check[ row ][ col ] )
				{
					// make sure we don't use this tile again
					check[ row ][ col ] = true;

					// if this is the last letter, then we have found the
					// word - Note: this is base case #3
					if ( word.length() == 1 )
						wordFound = true;
					// otherwise, we need to check all of the columns around
					// us until we either find the word or determine it's
					// not here - Note: this is the recursive case
					else
					{
						int checkRow = 0;
						int checkCol = 0;
						int startRow = row - 1;
						int startCol = col - 1;

						// loop through the tiles around the one we're
						// looking at - Note: we'll skip the current one
						// with the check above.
						while ( checkRow < 3 && !wordFound )
						{
							while ( checkCol < 3 && !wordFound )
							{
								wordFound = hasWord( word.substring(1),
													 startRow + checkRow,
													 startCol + checkCol,
													 check );

								checkCol++;

							} // end while

							checkRow++;
							checkCol = 0;		// reset column

						} // end while

					} // end else

				} // end if

			} // end if

		} // end if

		return wordFound;

	} // method hasWord (overloaded, recursive version)


	/**
	 * mix - Generates a new mixed board
	 */
	public void mix()
	{
		for ( int x = 0; x < board.length; x++ )
			for ( int y = 0; y < board[0].length; y++ )
				board[ x ][ y ] = getRandomChar();

	} // method mix


	/**
	 * setBoard - Sets the board to the incoming array - For testing only!!!
	 *
	 * ** added for PA3 **
	 * 
	 * @param 2D Array for testing
	 */
	public void setBoard(char[][] testArray)
	{
		if ( testArray != null )
			board = testArray;

	} // end setBoard


	/************************** private methods ***************************/

	/**
	 * getRandomChar - Returns a random char from the String
	 *
	 * @return a randomchar
	 */
	private char getRandomChar()
	{
		return LETTERS.charAt( random.nextInt( LETTERS.length() ) );

	} // getRandomChar

	/**
	 * isInBounds - Returns true if the x,y coordinates are in bounds for 
	 *              the array
	 *
	 * @param x value
	 * @param y value
	 * @return true if x and y are in bounds
	 */
	private boolean isInBounds( int x, int y )
	{
		return ( x > -1 && x < X_VALUE && y > -1 && y < Y_VALUE );

	} // method isInBounds

} // class Board

