package view.file;

import java.io.*;

/**
 * FileIO
 *
 * File I/O for Boggle Game
 * 
 * Acknowledgements: I acknowledge that I have neither given nor
 * received assistance for this assignment except as noted
 * below:
 *
 * @author James Barney, Nathan DiDomenico, Michael Norton
 * @version pa02 (10/11/2012)
 */
public class FileIO
{

    //---------------------------------------------------------------------
    // Declarations
    //---------------------------------------------------------------------
    private BufferedReader reader;  // the reader object
    private BufferedWriter writer;  // the writer object
    private File inFile;
    private File outFile;


    /**
     * constructor
     */
    public FileIO(String inName)
    {
        String outName;

        if ( inName != null )
        {
            // create temp file for output
            if ( inName.indexOf( "." ) > -1 )
                outName = inName.substring( 0, inName.lastIndexOf( "." ) ) + ".tmp";
            else
                outName = inName + ".tmp";

            // instantiate File objects
            inFile = new File( inName );
            outFile = new File( outName );

            // instantiate File reader and writer
        } // end if
    }

    /**************************** Public Methods *************************/

    /**
     * closeReader
     *
     * close the files and rename the output file to the input file name
     */
    public void closeReader()
    {
        
        try
    	{
        	reader.close();
    	}
        catch(IOException e){}

    } // method closeReader


    /**
     * closeWriter
     *
     * close the files and rename the output file to the input file name
     */
    public void closeWriter()
    {
    	try
    	{
	        reader.close(); // redundant close, just in case
	        writer.close(); // shouldn't be open, but we'll close it anyway
	
	        // rename tmp file to dat
	        inFile.delete();
	        outFile.renameTo( inFile );
    	}
    	catch(IOException e){}

        
    } // method closeWriter


    /**
     * openReader
     *
     * open the file reader
     */
    public boolean openReader()
    {
        boolean canRead = false;

        try
        {
            if ( inFile.exists() )
            {
                reader = new BufferedReader( new FileReader( inFile ) );
                canRead = true;

            } // end if

        } // end try

        catch (IOException e) {}

        return canRead;

    } // method openReader


    /**
     * openWriter
     *
     * open the file writer
     *
     * @return boolean
     * @throws IOException
     */
    public boolean openWriter()
    {
        boolean canWrite = false;

        try
        {
            writer = new BufferedWriter( new FileWriter( outFile ) );
            canWrite = true;

        } // end try

        catch ( IOException e ) {}

        return canWrite;

    } // method closeWriter


    /**
     * readLine
     *
     * read a line
     *
     * @return String
     */
    public String readLine()
    {
    	String string = "";
    	try
    	{
    		string = reader.readLine();   		
    	}
    	catch ( IOException e ) {}
    	return string;
    	
    } // method getInputFromUser


    /**
     * write
     *
     * write a line
     *
     * @param String
     */
    public void write(String s)
    {
    	try
    	{
    		writer.write( s + "\n" );
    	}
    	catch ( IOException e ) {}
    	
    } // method write

} // class FileIO
