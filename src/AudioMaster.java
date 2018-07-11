import java.util.Hashtable;
import java.util.Random;

/**
 * This class will control all game audio lines.
 * Here you can adjust the different lines.
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
		System.out.println("Generating Audio Line ID... " + lineID);
		line.setID(genID());
		
		lineTable.put(lineID, line);
		lineIDTable.put(line.getName(), lineID);
		return true;
		
	}
	
	public static int getLineID(String lineName) {
		try
		{
			return lineIDTable.get(lineName); 
		} 
		catch (NullPointerException e)
		{
			return -1;
		}
	}
	
	public static AudioLine getLine(Integer lineID) { return lineTable.get(lineID); }
	
}

