package model;

/**
 * Game - This class supervises play of the Boggle game, including keeping 
 * track of and incrementing the round, and mixing the board. The class 
 * needs to keep track of the round, the current board, and the players.
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 *
 * Note: This class is not playing the game, only keeping information about
 * the game.  The game is actually played by the Controller.
 *
 * Modifications: 1. PA2 -- added pointsToWin and difficulty and set 
 *                          defaults in constructor (added constants to 
 *                          handle values for these)
 *                2. PA2 -- added get/set methods for these
 *                3. PA2 -- added Dictionary object and get
 *                          method
 *                4. PA2 -- added static finals for default values
 *                5. PA3 -- added static finals for human/computer
 *                6. PA3 -- added human/computer players
 *                7. PA3 -- modified getPlayer to return specific
 *                          player (via value sent in parameter)
 *
 * @author James Barney, Nathan DiDomenico, Michael Norton
 * @version pa03 (10/16/2012), pa02 (10/11/2012), pa01 (9/09/2012)
 **/
public class Game
{
	//----------------------------------------------------------------------
	// Declarations
	//----------------------------------------------------------------------
	private int roundNumber;
	private int pointsToWin; 	// added for PA2
	private int difficulty;		// added for PA2

	private Board board;
	private Dictionary dictionary; 	// added for PA2
	private Player computer;			// added for PA3
	private Player human;				// renamed for PA3

	public static final int COMPUTER = 0;	// added for PA3
	public static final int HUMAN = 1;		// added for PA3

	public static final int DEFAULT_DIFFICULTY = 5;
	public static final int DEFAULT_SCORE = 100;
	public static final int MIN_DIFFICULTY = 1;
	public static final int MIN_SCORE = 1;
	public static final int MAX_DIFFICULTY = 10;
	public static final int MAX_SCORE = 10000;

	/**
	 * Default constructor
	 */
	public Game()
	{
		roundNumber = 0;
		pointsToWin = DEFAULT_SCORE; 			// added for PA2
		difficulty = DEFAULT_DIFFICULTY;		// added for PA2

		dictionary = Dictionary.getDictionary(); 	// added for PA2
													// modified for PA3

		board = new Board();
		human = new HumanPlayer( board );		// PA3
		computer = new ComputerPlayer( board );	// PA3

	} // constructor


	/**
	 * getBoard - Returns the board
	 *
	 * @return the Board
	 */
	public Board getBoard()
	{
		return board;

	} // method getBoard


	/**
	 * getDictionary - Return the dictionary
	 *
	 * ** added for PA2 **
	 *
	 * @return the Dictionary
	 */
	public Dictionary getDictionary()
	{
		return dictionary;

	} // method getDifficulty


	/**
	 * getDifficulty - Returns the difficulty level
	 *
	 * ** added for PA2 **
	 *
	 * @return the difficulty level
	 */
	public int getDifficulty()
	{
		return difficulty;

	} // method getDifficulty


	/**
	 * getPlayer - Returns the requested player
	 *
	 * ** modified for PA3 ** added param to return proper player
	 *
	 * @param which player
	 * @return the player requested
	 */
	public Player getPlayer( int who )
	{
		Player player;

		if ( who == COMPUTER )
			player = computer;
		else
			player = human;

		return player;

	} // method getPlayer


	/**
	 * getPointsToWin - Returns the points necessary to win
	 *
	 * ** added for PA2 **
	 *
	 * @return number of points
	 */
	public int getPointsToWin()
	{
		return pointsToWin;

	} // method getPointsToWin


	/**
	 * getRoundNumber - Returns the current round number
	 *
	 * @return the current round number
	 */
	public int getRoundNumber()
	{
		return roundNumber;

	} // method getRoundNumber


	/**
	 * newGame - Reset the variables for a new game
	 * 
	 * ** added for PA3
	 */
	public void newGame()
	{
		human.resetGame();
		computer.resetGame();

		roundNumber = 0;

	} // newGame

	/**
	 * playRound - Start a round of play
	 */
	public void playRound()
	{
		roundNumber++;	// increment the round number

		board.mix();

	} // method playRound


	/**
	 * setDifficulty - Sets the difficulty level (between 1 & 10)
	 *
	 * ** added for PA2
	 *
	 * @param the difficulty level (1 - 10)
	 */
	public void setDifficulty( int d )
	{
		if ( d >= MIN_DIFFICULTY && d <= MAX_DIFFICULTY )
			difficulty =  d;

	} // method setDifficulty


	/**
	 * setPointsToWin - Sets the number of points necessary to win 
	 *                  (10 - 10,000)
	 *
	 * ** added for PA2
	 *
	 * @param number of points to win
	 */
	public void setPointsToWin(int p)
	{
		if ( p >= MIN_SCORE && p <= MAX_SCORE )
			pointsToWin = p;

	} // method setPointsToWin

} // class Game
