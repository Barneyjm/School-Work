package listener;

import java.awt.Toolkit;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import util.*;
import view.file.FileIO;
import listener.TickListener;
import model.Board;
import model.Dictionary;
import model.Game;
import model.Player;
import model.StringSet;


/**
 * MasterGUI
 *
 * This class that listens to the  GUI that boggle has
 * 
 * @author Nathan DiDomenico, James Barney
 * @version 1
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 * 
 * Modifications: Now Launches GUI
 */
public class BoggleListener extends WindowAdapter implements TickListener,
        ActionListener, ChangeListener
{
    protected EggTimer timer;
    private JButton start;
    private JButton quit;
    private JButton restart;
    private JButton remove;
    private JProgressBar timerBar; 
    private JTextArea area;
    private JRadioButton[] diffSelect;
    private JSpinner scoreSpinner;
    private JLabel humanRoundScore;
    private JLabel computerRoundScore; 
    private JLabel humanTotalScore;
    private JLabel computerTotalScore; 
    private JLabel roundNumber;
    private JLabel roundOff;
    private JList<String> youList;
    private JList<String> computerList; 
    private JList<String> commonList;
    private JList<String> invalidList;
    
    private DefaultListModel<String> youListModel;
    private DefaultListModel<String> computerListModel;
    private DefaultListModel<String> commonListModel;
    private DefaultListModel<String> invalidListModel;
    
    private SpinnerModel scoreModel;
    
    private JButton[][] tileBtn;
    
    private final int INPUT_TIME = 180; //ft
    
    
    // controller knowledge
    private Dictionary dict;                    // added for PA2
    private FileIO file;                        // added for PA2
    private Game game;
    private Board board;
    private Player[] players;
 
    

    
    
    //private TextIO ui;

    public static final int GAME_TIME = 180;        // added for PA2

    public static final int COMMON_WORDS = 0;          // added for PA3
    public static final int INVALID_WORDS = 1;      // added for PA3
    public static final int HUMAN_UNIQUE = 2;       // added for PA3
    public static final int COMPUTER_UNIQUE = 3;    // added for PA3
    
    /**
     * Explicit value constructor
     * 
     * @param the start button
     * @param the progress bar
     * @param the text area
     */
     public BoggleListener( JButton s, JButton q, JButton rs, JButton rm, 
    		 JRadioButton[] d, JProgressBar p, JTextArea t, JLabel hrs, 
    		 JLabel crs, JLabel hts, JLabel cts, JList<String> yl, 
    		 JList<String> cl, JList<String> comlist, JList<String> il,
    		 JButton[][] tiles, JLabel rn, JLabel ro, JSpinner sp)
    {   
        start = s;
        quit = q;
        restart = rs;
        remove = rm;
        diffSelect = d;
        timerBar = p;
        area = t;
        
        humanRoundScore = hrs;
        computerRoundScore = crs; 
        humanTotalScore = hts;
        computerTotalScore = cts; 
        youList = yl;
        computerList = cl; 
        commonList = comlist;
        invalidList = il;
        tileBtn = tiles;
        
        roundNumber = rn;
        roundOff = ro;
        
        scoreSpinner = sp;
        
        // controller stuff
        file = new FileIO( "./dictionary.txt" );
        game = new Game();
        board = game.getBoard();
        dict = Dictionary.getDictionary();
        players = new Player[2];
        players[ Game.HUMAN ] = game.getPlayer( Game.HUMAN );
        players[ Game.COMPUTER ] = game.getPlayer( Game.COMPUTER );
        scoreModel = new SpinnerNumberModel(100, //initial value
        									1, //min
        									10000, //max
        									1);     //step
        
        scoreSpinner.setModel(scoreModel);
        start.requestFocus();
        
        youListModel = new DefaultListModel<String>();
        computerListModel = new DefaultListModel<String>();
        commonListModel = new DefaultListModel<String>();
        invalidListModel = new DefaultListModel<String>();
        
        readFile();
    
    }
     

    /**
     * Runs when the Start button is pressed
     * 
     * @param the Action Event object
     * @see java.awt.event.ActionListener#
     *      actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e)
    {
        if ( e.getSource() == start )
        {
            startPressed();
        }
        
        if ( e.getSource() == quit )
        {
            quitPressed();
        }
        
        if ( e.getSource() == restart )
        {
            restartPressed();
        }
        
        if (e.getSource() == remove)
        {
        	removePressed();
        }
        
        for(int i = 0; i< diffSelect.length; i++)
        {
        	if(e.getSource() == diffSelect[i])
        		game.setDifficulty(Integer.parseInt(diffSelect[i].getText()));   	
        }

    }
    	
    
	
    /**
     * Increases the target score of the game when the spinner is manipulated
     * 
     * @param the Action Event object
     * @see java.awt.event.ActionListener#
     *      actionPerformed(java.awt.event.ActionEvent)
     */
	public void stateChanged(ChangeEvent e) 
	{
		int score = (Integer)scoreModel.getValue();
		game.setPointsToWin(score);
	}


	/**
	 * Starts a round of the game
	 * 
	 * ** added for PA4
	 */
	public void startPressed()
    {
		game.playRound();
		
        // reset scores and lists for both players
        for ( int i = 0; i < players.length; i++ )
            players[ i ].resetRound();
		
		for( int x = 0; x < board.X_VALUE; x++ )
        {
            for( int y = 0; y < board.Y_VALUE; y++ )
            {
            	String temp = Character.toString(board.getCell(x, y));
            	tileBtn[x][y].setText(temp); 
            }
        }
		
		roundNumber.setText("Round Number: " + game.getRoundNumber());
		//enable and clear text field
		area.setText(null);
		area.setEnabled(true);
		roundOff.setText("Game Status: Round in progress");
		
		//clear the word lists
		youListModel.clear();
		computerListModel.clear();
		commonListModel.clear();
		invalidListModel.clear();
		
    	// disable Start button
        start.setEnabled( false );
        
        //disable remove button
        remove.setEnabled(false);
        
        // init the timer
        timer = new EggTimer( INPUT_TIME );
        timer.addTickListener( this );
        timerBar.setString( "3:00" );
        
        // move focus to the text area for input
        area.requestFocus();
    }
    
	
	/**
	 * quits the game
	 * 
	 * ** added for PA4
	 */
    public void quitPressed()
    {
   
    	writeFile();
    	System.exit(0);
    	
    }
    
    
    /**
	 * restarts the game
	 * 
	 * ** added for PA4
	 */
    public void restartPressed()
	{
		game.newGame();

		// reset scores and lists for both players
	    for ( int i = 0; i < players.length; i++ )
	    {
	        players[ i ].resetRound();
	    }
		
	    displayScore();
	
		for( int x = 0; x < board.X_VALUE; x++ )
	    {
	        for( int y = 0; y < board.Y_VALUE; y++ )
	        {
	        	
	        	tileBtn[x][y].setText("---"); 
	        }
	    }
		//clear the word lists
		youListModel.clear();
		computerListModel.clear();
		commonListModel.clear();
		invalidListModel.clear();
		
		timer.stop();
		timerBar.setValue(0);
		
		diffSelect[4].setSelected(true);
	
		start.setEnabled(true);
		area.setText(null);
		
	}


	/**
     * Runs every second - uses the EggTimer
     * 
     * @param the Egg timer
     * @see TickListener#tick(EggTimer)
     */
    public void tick(EggTimer timer)
    {
        timerBar.setValue( INPUT_TIME - timer.getSecondsLeft() );
        timerBar.setString( timer.getTimeLeft() );
        
        if ( timer.getSecondsLeft() == 0 )
        {
            start.setEnabled( true );
            // do end of round/game stuff
            timerDead();
        }

    }

    /**
	 * Handle window closing actions
	 * 
	 * @param the Window Event object
	 * @see java.awt.event.WindowAdapter#
	 *      windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing( WindowEvent e )
	{
	    System.exit( 0 );
	}


	/**
     * addWordsToPlayer - Add the list of words from the player to the 
     *                    ArrayList
     *                    
     * ** modified for PA2 -- added difficulty level to param of player.add
     * ** modified for PA3 -- get Human Player
     * ** modified for PA3 -- fix 0-byte word issue (thanks Bryan Conner!)
     *
     * @param String
     */
    private void addWordsToPlayer(String words)
    {
        if ( words != null )
        {
            Player player = game.getPlayer( Game.HUMAN );
            String[] wordArray = words.split( "\\s+" );

            for ( String word : wordArray )
                if ( word.length() > 0 )
                player.add( word, game.getDifficulty() );

        } // end if

    } // method addWordsToPlayer
    
    /**
	 * displayScore - Displays score to user
	 * 
	 * ** added for PA2 **
	 *
	 * ** modified for PA3 - scores array for both players, show scores for
	 * **                    both players - move computeScore calls here 
	 * **                    (from endRound)
	 *
	 * @param Player[]
	 * @throws IOException
	 **********************************************************************/
	private void displayScore()
	{
		
		
		if ( players != null && players.length == 2 ) // don't try if array
	    {                                             // is incorrect
	        for ( int i = 0; i < 2; i++ )
	        {
		        players[ i ].computeScore();
	        }
	        
	        int[] humanScore = players[Game.HUMAN].getScore();
	        humanRoundScore.setText(Integer.toString(humanScore[Player.ROUND]));
	        humanTotalScore.setText(Integer.toString(humanScore[Player.GAME]));
	        
	        int[] computerScore = players[Game.COMPUTER].getScore();
	        computerRoundScore.setText(Integer.toString(computerScore[Player.ROUND]));
	        computerTotalScore.setText(Integer.toString(computerScore[Player.GAME]));
	
	    } // end if
	
	} // method displayScore


	/**
	 * displayWords - Displays words at the end of a round
	 * 
	 * ** added for PA2 (moved from endRound method)
	 *
	 * @param Player
	 */
	private void displayWords( Player[] players )
	{
	    StringSet[] sets = generateSets( players );
	    
	    for( int i = 0; i < sets[HUMAN_UNIQUE].size(); i++)
	    {
	    	youListModel.addElement(sets[HUMAN_UNIQUE].get(i));
	    }
	    
	    for( int i = 0; i < sets[COMPUTER_UNIQUE].size(); i++)
	    {
	    	computerListModel.addElement(sets[COMPUTER_UNIQUE].get(i));
	    }
	    
	    for( int i = 0; i < sets[COMMON_WORDS].size(); i++)
	    {
	    	commonListModel.addElement(sets[COMMON_WORDS].get(i));
	    }
	    
	    for( int i = 0; i < sets[INVALID_WORDS].size(); i++)
	    {
	    	invalidListModel.addElement(sets[INVALID_WORDS].get(i));
	    }
	    
	    youList.setModel(youListModel);
	    computerList.setModel(computerListModel);
	    commonList.setModel(commonListModel);
	    invalidList.setModel(invalidListModel);
	
	}


	/**
	 * endRound - Handles end of round events
	 *
	 * ** modified for PA2 - return type changed to boolean to indicate
	 * **                    whether or not to continue - if game score >= 
	 * **                    points needed, then carryOn = false;
	 * ** modified for PA3 - added computer player, sent human & computer
	 * **                    players to displayWords method
	 *
	 * @return boolean
	 * @throws IOException
	 */
	private boolean endRound()
	{
	    boolean carryOn = true; // added for PA2
	    int maxScore = 0;
	
	    Toolkit tk = Toolkit.getDefaultToolkit();
	    tk.beep();
	    
	    displayWords( players ); // moved this procedure in PA2
	    //rejectWords( players[ Game.HUMAN ] );
	    
	    displayScore();
	
	
	
	    maxScore = Math.max(
	            players[ Game.HUMAN ].getScore()[ Player.GAME ],
	            players[ Game.COMPUTER ].getScore()[ Player.GAME ] );
	
	    if ( maxScore >= game.getPointsToWin() )
	        carryOn = false;
	
	    return carryOn;
	
	} // method endRound


	/**
     * generateSets
     *
     * generate the StringSets for a user
     * 
     * ** added for PA3 **
     *
     * @return an array of StringSets
     * @param the Player array
     */
    private StringSet[] generateSets( Player[] players )
    {
        StringSet[] sets = new StringSet[ 4 ];

        // don't try this if the incoming array is not correct
        if ( players != null && players.length == 2 )
        {
            // generate the valid/invalid lists for each player
            for ( int i = 0; i < players.length; i++ )
                players[ i ].checkList();

            // get the intersection of the two lists
            sets[ COMMON_WORDS ] = players[ Game.HUMAN ].getList(
                Player.LIST ).intersection(players[ Game.COMPUTER ].
                    getList( Player.LIST ) );

            // human list - computer list
            sets[ HUMAN_UNIQUE ] = players[ Game.HUMAN].getList(
                Player.VALID ).difference(players[ Game.COMPUTER ].
                    getList( Player.VALID) );

            // computer list - human list
            sets[ COMPUTER_UNIQUE ] = players[ Game.COMPUTER].getList(
                Player.VALID ).difference( players[ Game.HUMAN ].
                    getList( Player.VALID) );

            // human invalid list
            sets[ INVALID_WORDS ] = players[ Game.HUMAN ].getList(
                Player.INVALID);

            // set the unique list for each player
            players[ Game.HUMAN ].setUniqueSet( sets[ HUMAN_UNIQUE ] );
            players[ Game.COMPUTER ].setUniqueSet( sets[ COMPUTER_UNIQUE ] );
            

        } // end if

        return sets;

    } // method generateSets
    
    /**
     * readFile - Read file and populate Dictionary
     *
     * ** added for PA2
     *
     * @return true if the file was read successfully
     */
    private boolean readFile() 
    {
        Dictionary dict = game.getDictionary();

        boolean canRead = file.openReader();

        if (canRead)
        {
            String word = file.readLine();
            while ( word != null )
            {
                 dict.add( word );
                 word = file.readLine();

            } // end while

            file.closeReader();

        } // end if

        return canRead;

    } // method readFile
    
    
    
    /**
	 * recalcuates the score after between-round edditing
	 * 
	 * ** added for PA4
	 */
    private void refreshScore()
	{
	    	
	    	if ( players != null && players.length == 2 ) // don't try if array
	        {                                             // is incorrect
	            for ( int i = 0; i < 2; i++ )
	                players[ i ].refreshScore();
	            
	            int[] humanScore = game.getPlayer(Game.HUMAN).getScore();
	            humanRoundScore.setText(Integer.toString(humanScore[Player.ROUND]));
	            humanTotalScore.setText(Integer.toString(humanScore[Player.GAME]));
	            
	            int[] computerScore = game.getPlayer(Game.COMPUTER).getScore();
	            computerRoundScore.setText(Integer.toString(computerScore[Player.ROUND]));
	            computerTotalScore.setText(Integer.toString(computerScore[Player.GAME]));
	
	        } // end if
	
	    } // method displayScore


    /**
	 * removes selected words from the player's and computer's lists
	 * 
	 * ** added for PA4
	 */
	private void removePressed() 
	{
		int removeThisInd;
		String removeThisWord;
		int[] youRemoveInds = youList.getSelectedIndices();
		
		
		
		
		for( int i = youRemoveInds.length-1 ; i >= 0; i--)
		{
			removeThisInd = youRemoveInds[i];
			removeThisWord = (String)youListModel.getElementAt(removeThisInd);
			
			game.getPlayer(Game.HUMAN).remove(removeThisWord);
			youListModel.remove(removeThisInd);
		}
		
		int[] computerRemoveInds = computerList.getSelectedIndices();
		
		for( int i = computerRemoveInds.length-1 ; i >= 0; i--)
		{
			removeThisInd = computerRemoveInds[i];
			removeThisWord = (String)computerListModel.getElementAt(removeThisInd);
			
			game.getPlayer(Game.COMPUTER).remove(removeThisWord);
			computerListModel.remove(removeThisInd);
		}
		refreshScore();
		
	}

	
	
	/**
	 * Collects input and enables the between-round 
	 * edditing after the time has run out.
	 * 
	 * ** added for PA4
	 */
	private void timerDead()
	{
		area.setEnabled(false);
		roundOff.setText("Game Status: Round over");
		String wordsEntered = area.getText();
		remove.setEnabled(true);
		
		addWordsToPlayer(wordsEntered);
		//displayScore();
		endRound();
		
		
		
	}


	/**
     * writeFile - Write file from Dictionary - if over 300 words randomly 
     *             select 300 without writing duplicates
     *
     * ** added for PA2
     *
     * @return true if the file was written successfully
     */
    private boolean writeFile()
    {
        boolean canWrite = file.openWriter();
        ArrayList< String > dictList = dict.getDictionaryList();
        
        if ( canWrite )
        {
            
            // 300 or fewer, write everything
            if ( dictList.size() <= 300 )
                for ( int i = 0; i < dictList.size(); i++ )
                    file.write( dictList.get(i) );

            // otherwise randomly write 300
            else
            {
                Random rand = new Random();
                int[] tracker = new int[ dictList.size() ]; // store slots used
                int counter = 0;
                int index = 0;


                while ( counter < 300 )
                {
                    index = rand.nextInt( dictList.size() );

                    // write this if it hasn't already been written
                    if ( tracker[ index ] != 1 )
                    {
                        file.write( dictList.get(index) );
                        tracker[ index ] = 1;
                        counter++;

                    } // end if

                } // end while

            } // end else

            file.closeWriter();

        } // end if

        return canWrite;

    } // method writeFile
}


