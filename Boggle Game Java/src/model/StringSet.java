package model;

import java.util.*;

/**
 * StringSet
 *
 * Represents a linked list of unique words as entered by player
 * (stored in nodes)
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 *
 * Modifications: (PA2) added iterator() method, changed "list" to "set"
 *
 * @author James Barney, Nathan DiDomenico, Michael Norton
 * @version pa03 (10/16/2012), pa02 (10/12/2012)
 */
public class StringSet
{
    private ArrayList< String > set;

    /**
     * Constructor
     */
     public StringSet()
     {
         set = new ArrayList< String >();
     } // constructor

     /*************************** public methods **************************/

    /**
     * add
     *
     * add a Node to the list
     *
     * @param word (String)
     */
    public boolean add( String word )
    {
        boolean success = false;

        if ( word != null ) // make sure there's really a String to add here
        {           
            word = word.toLowerCase();
            
            if ( !contains( word ) )
            {
                set.add( word );
                success = true;
                
            } // end if
            
        } // end if
        
        return success;
        
    } // method add()

    /**
     * clear
     *
     * clears the list
     */
    public void clear()
    {
        set.clear();
        
    } // method clear


    /**
     * contains
     *
     * returns true if the word in the parameter is found in the list
     *
     * @param target (String)
     * @return boolean
     */
    public boolean contains( String target )
    {
        return set.contains(  target );

    } // method contains
    

    /**
     * difference
     * 
     * returns a StringSet containing the difference between the current
     * set and the other set
     * 
     * @param other
     * @return a StringSet containing the difference between the sets
     */
    public StringSet difference( StringSet other )
    {
        StringSet difference = new StringSet();
        
        for ( String word: set )
            if ( !other.contains( word ) )
                difference.add( word );        
        
        return difference;

    } // method difference
    
    
    /**
     * intersection - returns
     * 
     * returns a StringSet containing the intersection of the current
     * set and the other set
     * 
     * @param other
     * @return a StringSet containing the intersection of the sets
     */
    public StringSet intersection( StringSet other )
    {
        StringSet intersection = new StringSet();
        
        for ( String word: set )
            if ( other.contains( word ) )
                intersection.add( word );        
        
        return intersection;

    } // method intersection

    
    /**
     * This method returns an iterator for the StringSet
     * 
     * **MLN - Added for PA3
     * 
     * @return an iterator for the StringSet
     */
    public Iterator< String > iterator()
    {
        return set.iterator();
        
    } // method iterator
    
    
    /**
     * get
     *
     * returns the Word stored in the specified Node
     *
     * @param index (int)
     * @return String
     */
    public String get( int index )
    {
        String retVal = null;

        if ( isInBounds( index ) )
        {
            retVal = set.get( index );
            
        } // end if

        return retVal;

    } // method getWord


    /**
     * remove
     *
     * remove the specified Node
     *
     * @param word (String)
     * @return boolean
     */
    public boolean remove( String word )
    {
        return set.remove( word );
        
    } // method remove


    /**
     * size()
     *
     * returns the number of Nodes in the list
     *
     * @return String
     */
    public int size()
    {
        return set.size();

    } // method size

    
    /**
     * union
     * 
     * Returns the union of the 2 sets
     * 
     * @param other
     * @return the union of the 2 sets
     */
    public StringSet union( StringSet other )
    {
        StringSet union = new StringSet();
        
        for ( String word : set )
            union.add( word );
        
        for ( int i = 0; i < other.size(); i++ )
            union.add( other.get( i ) );
        
        return union;
        
    } // method union
    
    /******************** private methods *********************************/
    
    /**
     * isInBounds()
     * 
     * returns true if the parameter is in bounds
     * 
     * @param where
     * @return true if the int is in bounds
     */
    private boolean isInBounds( int where )
    {
        return where > -1 && where < set.size();
        
    } // method isInBounds

} // class StringSet
