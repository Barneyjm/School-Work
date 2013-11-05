package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;
//import javax.swing.border.Border;
//import javax.swing.border.EmptyBorder;

import listener.BoggleListener;





/**
 * MasterGUI
 *
 * This class is the wondow GUI that boggle runs in
 * 
 * @author James Barney, Nathan DiDomenico
 * @version 1
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 * 
 * Modifications: Now Launches GUI
 */
public class MasterGUI extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//Action Listeners
	private BoggleListener listen;
	
	
	//Layout Items
	private Box boardBox; // Box layout panel for the west
	
	private GridBagConstraints c;
	
	//JPanels:
//	private JPanel mainPanel; 
//	private JPanel northWordPanel; 
	private JPanel southWordPanel; 
	private JPanel mainBoardPanel; // main board panel
    private JPanel boardGrid; // panel for the board grid
    private JPanel timeLeftPanel; // panel for time remaining
//    private JPanel boardPanel;
	private JPanel gameFunctionPanel;
//	private JPanel wordsFoundPanel;
	private JPanel rejectPanel;
//	private JPanel wordPanel;
	private JPanel scoreInfoPanel;
	
	//JScrollPanes
	private JScrollPane youPanel;		
	private JScrollPane computerPanel;
	private JScrollPane commonPanel;
	private JScrollPane invalidPanel;
	
	//JLists
	private JList<String> youList;
	private JList<String> computerList;
	private JList<String> commonList;
	private JList<String> invalidList;
	
	//JButtons:
	private JButton quit;
	private JButton restart;
	private JButton startRoundBtn; // gets a round going
	private JButton remove;
	
	//JRadioButtons:
	private JRadioButton[] difficultySelect;
	private ButtonGroup diffGroup;
	private JButton[][] tileBtn; // array of board tiles
	
	
	//JSpinners:
	private JSpinner scoreAmount;
	
	//JLabels:
	private JLabel diffLevel;
	private JLabel score;
	private JLabel roundOff;
	private JLabel youLabel;
	private JLabel computerLabel;
	private JLabel commonLabel;
	private JLabel invalidLabel;
	
	private JLabel roundNumber;
	private JLabel roundScore;
	private JLabel totalScore;
	private JLabel humanPlayer;
	private JLabel computerPlayer;
	private JLabel humanRoundScore;
	private JLabel computerRoundScore;
	private JLabel humanTotalScore;
	private JLabel computerTotalScore;
	
	//JText Areas:
	 private JTextArea inputArea; // where the user types found words
	 
	//Miscillaneiousousous Objects:
	 private JProgressBar timeLeftBar; // displays time left in round
	 private JScrollPane textPane; // scroll pane for the text area
	
	/**
     * FINAL attributes
     */
    // spacing parameters for laying out components
//    private final int SURROUND = 8; // space around major chunks
//    private final int INSET = 5; // standard spacing around components
//    private final int TITLE_INSET = 5; // inner titled border spacing
    private final int TILE_GAP = 2; // space around letter tiles
    private final int VGAP = 8; // vertical space in layout managers
    private final int HGAP = 0; // horizontal space in layout managers
    private final int DIMENSION = 4; // the Boggle board dimension

//    private final Border EMPTY_BORDER // standard border space
//        = new EmptyBorder( INSET , INSET , INSET , INSET );

    // label String contents
    private final String BLANK_TILE = "---";
//    private final String EMPTY = "";
    private final String NO_TIME = "0:00";
//    private final String PLAY = "Play";
    private final String START_ROUND = "Start a New Round";
	private final String QUIT = "Quit";
	private final String DIFF = "Difficulty: ";
	private final String RESTART = "Restart";
	private final String TARGET_SCORE = "Target Score: ";
    
    // miscellaneous constants
    private final int INPUT_TIME = 180; // seconds for user input
    
	
    /**
     * Default constructor
     */
	public MasterGUI()
	{
		
		super();
		createComponents();		
        setComponentAttributes();
        addComponentsToPanels();
        addPanels();
        setBorders();
        setListeners();
        difficultySelect[4].setSelected(true);
        centerFrame();
        setVisible( true );
		
		
	}
	
	
    /************************* private methods*****************************/

	
	
	/**
     * Add components & panels to panels
     */
    private void addComponentsToPanels()
	{
		// Add buttons to grid
	    for (int r = 0; r < DIMENSION; r++)
	        for (int c = 0; c < DIMENSION; c++)
	            boardGrid.add( tileBtn[ r ][ c ] );
	
	    // Add button, grid, timeLeft to Box
	    boardBox.add( startRoundBtn );
	    boardBox.add( Box.createVerticalStrut( VGAP ) );
	    boardBox.add( boardGrid );
	    boardBox.add( Box.createVerticalStrut( VGAP ) );
	    boardBox.add( timeLeftPanel );
	    
	    timeLeftPanel.add( timeLeftBar , BorderLayout.NORTH );
	 // put inputArea into a scroll pane
	    textPane = new JScrollPane( inputArea );
	    
	 // add Box and ScrollPane to main
	    mainBoardPanel.add( boardBox , BorderLayout.NORTH );
	    mainBoardPanel.add( textPane , BorderLayout.CENTER );
	    
	    rejectPanel.add(remove);
		//if statement to determine which:
		rejectPanel.add(roundOff);
		
		// add the "you" panel to the word lists panel
		southWordPanel.add(youLabel, BorderLayout.NORTH);
		southWordPanel.add(youPanel, BorderLayout.SOUTH);
		
		// add the "computer" panel to the word lists panel
		southWordPanel.add(computerLabel);
		southWordPanel.add(computerPanel);
		
		// add the "common" panel to the word lists panel
		southWordPanel.add(commonLabel);
		southWordPanel.add(commonPanel);
		
		// add the "invalid" panel to the word lists panel
		southWordPanel.add(invalidLabel, BorderLayout.NORTH);
		southWordPanel.add(invalidPanel, BorderLayout.SOUTH);
		
		gameFunctionPanel.add(diffLevel);
		for(int i = 0; i < 10; i++)
		{
			String num = Integer.toString(i+1); 
			difficultySelect[i] = new JRadioButton(num);
			difficultySelect[i].setName(num);
			diffGroup.add(difficultySelect[i]);	
			gameFunctionPanel.add(difficultySelect[i]);
		}
		gameFunctionPanel.add(score);
		gameFunctionPanel.add(scoreAmount);
		gameFunctionPanel.add(restart);
		gameFunctionPanel.add(quit);
		
		
	}

    
    /**
     * Add panels to Frame
     */
	private void addPanels() 
	{
	    
	    // add the main panel to the west region of the content pane
		
	    add( mainBoardPanel, BorderLayout.WEST );
	
	    add(rejectPanel, BorderLayout.SOUTH);
	    //add(northWordPanel);
	    
	    
	    add(gameFunctionPanel, BorderLayout.NORTH);
	    
	    
	    add(scoreInfoPanel, BorderLayout.EAST);
		add(southWordPanel, BorderLayout.CENTER);
		
		centerFrame();
	}

	/**
     * Center the frame in the screen
     */
    private void centerFrame()
    {
        Dimension dimScreenSize = Toolkit.getDefaultToolkit()
                .getScreenSize();
        Dimension dimFrameSize = getSize();

        if ( dimFrameSize.height > dimScreenSize.height )
            dimFrameSize.height = dimScreenSize.height;
        if ( dimFrameSize.width > dimScreenSize.width )
            dimFrameSize.width = dimScreenSize.width;

        setLocation( (dimScreenSize.width - dimFrameSize.width) / 2 ,
                (dimScreenSize.height - dimFrameSize.height) / 2 );

    } // centerFrame

	/**
	 * Instantiate all components and containers
     */
	private void createComponents()
	{
		//JPanels:
		southWordPanel = new JPanel();
		mainBoardPanel = new JPanel( new BorderLayout( HGAP , VGAP ) );
        boardGrid = new JPanel( new GridLayout( DIMENSION ,
                DIMENSION , TILE_GAP , TILE_GAP ) );
        timeLeftPanel = new JPanel( new BorderLayout() );
    	gameFunctionPanel = new JPanel();
    	rejectPanel = new JPanel();

    	
    	
		
		scoreInfoPanel = new JPanel();
        
        // create Box panel 
        boardBox = Box.createVerticalBox();
        
        c = new GridBagConstraints();
		
		//JButtons:
        remove = new JButton("Reject selected words"); // make final
		quit = new JButton(QUIT);
		restart = new JButton(RESTART);
		tileBtn = new JButton[ DIMENSION ][ DIMENSION ];
        for (int r = 0; r < DIMENSION; r++)
            for (int c = 0; c < DIMENSION; c++)
            {
                tileBtn[ r ][ c ] = new JButton( BLANK_TILE );
               
            } // end for
        
		startRoundBtn = new JButton(START_ROUND); // gets a round going
		
		//JRadioButtons:
		difficultySelect = new JRadioButton[10];
		diffGroup = new ButtonGroup();
		
		//JSpinners:
		scoreAmount = new JSpinner();
		
		//JLabels:
		diffLevel = new JLabel(DIFF);
		score = new JLabel(TARGET_SCORE);
		roundOff = new JLabel(); //final-ize
		score = new JLabel("Target Score: ");
		youLabel = new JLabel("You");
		computerLabel = new JLabel("Computer");
		commonLabel = new JLabel("Common Words");
		invalidLabel = new JLabel("Invalid Words");
		
		roundNumber = new JLabel("Round Number");
		roundScore = new JLabel("Round Score");
		totalScore = new JLabel("Total Score");
		humanPlayer = new JLabel("You:");
		computerPlayer = new JLabel("Computer:");
		humanRoundScore = new JLabel();
		computerRoundScore = new JLabel();
		humanTotalScore = new JLabel();
		computerTotalScore = new JLabel();
		
		//JLists:
		youList = new JList<String>();
		computerList = new JList<String>(); 
		commonList = new JList<String>();
		invalidList = new JList<String>();
		
		youPanel = new JScrollPane(youList);		
		computerPanel = new JScrollPane(computerList);
		commonPanel = new JScrollPane(commonList);
		invalidPanel = new JScrollPane(invalidList);
		
		//JText Areas:
		inputArea = new JTextArea(); // where the user types found words
		 
		//Miscillaneiousousous Objects:
		timeLeftBar = new JProgressBar( 0 , INPUT_TIME ); // displays time left in round
		textPane = new JScrollPane(); // scroll pane for the text area
		
		listen = new BoggleListener(startRoundBtn, quit, restart, remove, difficultySelect, timeLeftBar, inputArea, humanRoundScore, computerRoundScore, humanTotalScore, computerTotalScore, youList, computerList, commonList, invalidList, tileBtn, roundNumber, roundOff, scoreAmount);
		
	}

	/*
	 *  give the components thier listeners
	 */
	private void setListeners() 
	{
		startRoundBtn.addActionListener(listen);
		quit.addActionListener(listen);
		restart.addActionListener(listen);
		remove.addActionListener(listen);
		for(int i = 0; i < difficultySelect.length; i++)
		{
			difficultySelect[i].addActionListener(listen);
		}
		
		scoreAmount.addChangeListener(listen);
	}

	/**
	 *  give the panels thier boarders
	 */
	private void setBorders() 
	{
		scoreInfoPanel.setBorder(BorderFactory.createTitledBorder("Scoring"));
		gameFunctionPanel.setBorder(BorderFactory.createTitledBorder("Game"));
		mainBoardPanel.setBorder(BorderFactory.createTitledBorder("Play"));	
		
	}

	
	 /**
     * Set attributes for components 
     */
	private void setComponentAttributes()
	{
		setSize(1100, 800);
		
		// set attributes for the start button
        startRoundBtn.setAlignmentX( 0.5f );
        startRoundBtn.setName( "startRound" );
        
        // set attributes for the quit button
        quit.setAlignmentX( 0.5f );
        quit.setName( "quit" );
        
        // set attributes for the restart button
        restart.setAlignmentX( 0.5f );
        restart.setName( "restart" );
        
        JFormattedTextField ftf = null;
        JComponent editor = scoreAmount.getEditor();
        if (editor instanceof JSpinner.DefaultEditor)
        {
            ftf = ((JSpinner.DefaultEditor)editor).getTextField();
        } 
        if (ftf != null ) {
            ftf.setColumns(6); //specify more width than we need
            ftf.setHorizontalAlignment(JTextField.RIGHT);
        }
        
        
   
        
        
        // set attributes for buttons on the grid
        for (int r = 0; r < DIMENSION; r++)
            for (int c = 0; c < DIMENSION; c++)
            {   
                Dimension size = tileBtn[ r ][ c ].getPreferredSize();
                size.height = size.width;
                tileBtn[ r ][ c ].setPreferredSize( size );
                tileBtn[ r ][ c ].setBackground( Color.WHITE );
 
            } // end for

        // set attributes for the progress bar
        timeLeftBar.setBackground( Color.WHITE );
        timeLeftBar.setBorderPainted( false );
        timeLeftBar.setAlignmentX( 0.5f );
        timeLeftBar.setString( NO_TIME );
        timeLeftBar.setStringPainted( true );
        
        // set border for timeLeftPanel
//        timeLeftPanel.setBorder( createEtchedBorder( 0 ) );  //lazy
        
        scoreInfoPanel.setLayout(new GridBagLayout());
        
		c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = 40;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		scoreInfoPanel.add(roundNumber, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = 20;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 0;
		scoreInfoPanel.add(roundScore, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.CENTER;
		c.ipadx = 20;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 0;
		scoreInfoPanel.add(totalScore, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		scoreInfoPanel.add(humanPlayer, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.LINE_END;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 2;
		scoreInfoPanel.add(computerPlayer, c);
		
		c = new GridBagConstraints();
		humanRoundScore.setAlignmentX(0.5f);
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 1;
		scoreInfoPanel.add(humanRoundScore, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridx = 2;
		c.gridy = 2;
		scoreInfoPanel.add(computerRoundScore, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
//		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 1;
		scoreInfoPanel.add(humanTotalScore, c);
		
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 3;
		c.gridy = 2;
		scoreInfoPanel.add(computerTotalScore, c);
        
        // set the "you" panel up
        youList.setName("you");
     	youList.setSize(50, 100);
     	youList.setVisible(true);
     	
        // set the "computer" panel up
     	computerList.setName("you");
     	computerList.setSize(50, 100);
     	computerList.setVisible(true);

        // set the "common" panel up
     	commonList.setName("you");
     	commonList.setSize(50, 100);
     	commonList.setVisible(true);
     	
     	// set the "invalid" panel up
     	invalidList.setName("you");
     	invalidList.setSize(50, 100);
     	invalidList.setVisible(true);
     	
       // wordPanel.setLayout( new GroupLayout());
		
	}

	
	
	
}
