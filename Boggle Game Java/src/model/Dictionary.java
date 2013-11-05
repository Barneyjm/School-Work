package model;

import java.util.*;


/**
 * Dictionary - Represents the Dictionary used by the Boggle game
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 * 
 * Modifications: PA3 -- Added iterator method
 *                PA3 -- Converted to Singleton
 *
 * @author James Barney, Nathan DiDomenico, Michael Norton
 * @version pa03 (10/15/2012), pa02 (10/11/2012)
 */
public class Dictionary
{
	//---------------------------------------------------------------------
	// Declarations
	//---------------------------------------------------------------------
	private TreeSet<String> dictionary;
	
	private static Dictionary dict; // added for PA3 to enable Singleton

    /**
     * constructor
     */
    private Dictionary()
    {
        dictionary = new TreeSet< String >();

    } // constructor wordList


    /**************************** Public Methods *************************/

    /**
     * add
     *
     * adds a word to the dictionary in alpha order with no duplicates
     * (handled by the TreeSet)
     *
     * @param word (String)
     * @return boolean
     */
    public boolean add( String word )
    {
        boolean success = false;

        if ( word != null )
        {
            dictionary.add( word );
            success = true;
            
        } // end if
        
        return success;

    } // method add


    /**
     * clear
     *
     * clears the Dictionary
     */
    public void clear()
    {
        dictionary.clear();

    } // method get


    /**
     * contains
     *
     * returns true if the String is in the Dictionary
     *
     * @param target (String)
     * @return boolean
     */
    public boolean contains ( String target )
    {
        boolean retVal = false;

        if ( target != null )
            retVal = dictionary.contains( target );

        return retVal;

    } // method contains


    /**
     * getDictionaryList
     *
     * Returns the dictionary as an ArrayList
     * Note: you could also use the toArray( String[] array ) method to 
     * an array of Strings.  I like to play with iterators, so I did it 
     * this way.
     *
     * @return an ArrayList
     */
    public ArrayList< String > getDictionaryList()
    {
        ArrayList< String > list = new ArrayList< String >();

        Iterator<String> iterator = dictionary.iterator();
        
        while( iterator.hasNext() )
            list.add( iterator.next() );
        
        return list;

    } // method get


    /**
     * Return the dictionary as an iterator
     * 
     * **MLN - Added for PA3
     * 
     * @return an iterator
     */
    public Iterator<String> iterator()
    {
        return dictionary.iterator();

    } // method iterator
    
    
    /**
     * remove
     *
     * removes the specified word from the Dictionary
     * Note: uses iterative binary search to locate the item to remove
     *
     * @param word (String)
     * @return boolean
     */
    public boolean remove( String word )
    {
        boolean success = false;

        if ( word != null && dictionary.size() > 0 )
        {
                dictionary.remove( word );
                success = true;
        }

        return success;

    } // method size


    /**
     * size
     *
     * returns the size of the Dictionary
     *
     * @return int
     */
    public int size()
    {
        return dictionary.size();

    } // method size
    
    
    /**
     * Return a subset of the Dictionary from start (inclusive) to finish
     * (exclusive)
     * 
     * @param start
     * @param end
     * @return a SortedSet of Strings
     */
    public SortedSet< String > subSet( String start, String end )
    {
        return dictionary.subSet( start, end );
   
    } // method subSet

	/******************************* Static Methods ***********************/

	/**
	 * getDictionary - Singleton method to ensure that there is never more
	 *                 than a single Dictionary object
	 *                 
	 * ** Added for PA3
	 * 
	 * @return the one and only Dictionary
	 */
	public static Dictionary getDictionary()
	{
		if ( dict == null )
			dict = new Dictionary();
		
		return dict;
		
	}
} // class Dictionary
