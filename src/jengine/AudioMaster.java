package jengine;
import java.util.Hashtable;
import java.util.Random;

/**
 * This class will control all game audio lines.
 * Here you can adjust the different lines.
 * Maybe Audio should have one AudioMaster class?????
 * @author Nicholas 'Kali' Chow
 */
public class AudioMaster {
	
	
	public static Hashtable<Integer, AudioLine> lineTable = new Hashtable<Integer, AudioLine>();
	private static Hashtable<String, Integer> lineIDTable = new Hashtable<String, Integer>();
	/**
	 * Generates a random ID for a line.
	 * @return Returns the random ID
	 */
	private static int genID() 
	{

		int randID = 0;
		Random rand = new Random();
		
		//Generate random number from 100,000,000 to 999,999,999
		//Generate while there is a value in the table.
		do { 	
			randID = rand.nextInt(999999999) + 100000000; 
	
		} while(lineTable.containsKey(randID)); 
		
		return randID;
	}
	
	/**
	 * Scales the master volume of the game.
	 * @param volFrac The amount you wish to scale the game by.
	 */
	public static void scaleVolume(float volFrac)
	{
		Iterable<AudioLine> audioLines = lineTable.values();
		for (AudioLine audioLine: audioLines)
		{
			audioLine.scaleLineVol(volFrac);
		}
	}
	
	/**
	 * Puts a line onto the hash table.
	 * @param line The Audio line to put onto the hash table
	 * @return Returns false on failure, true on success.
	 */
	public static boolean putLine(AudioLine line)
	{
		Integer lineID;
		if (line.getID() != 0 && line.getID() != -1)
			return false;
		
		lineID = new Integer(genID());
		line.setID(genID());
		
		lineTable.put(lineID, line);
		lineIDTable.put(line.getName(), lineID);
		return true;
		
	}
	
	/**
	 * Obtains the line id of a given line name.
	 * @param lineName The name of the desired line
	 * @return Returns an Integer wrapper object of the line's id.
	 */
	public static Integer getLineID(String lineName) {
		try
		{
			return lineIDTable.get(lineName); 
		} 
		catch (NullPointerException e)
		{
			return -1;
		}
	}
	/**
	 * Retrieves the AudioLine object from the hasbtable.
	 * @param lineID The Integer ID of the desired audio line.
	 * @return Returns the AudioLine object of the desired line.
	 */
	public static AudioLine getLine(Integer lineID) { return lineTable.get(lineID); }
	
}

