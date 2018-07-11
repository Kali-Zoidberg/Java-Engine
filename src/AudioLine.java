import java.util.Hashtable;
import java.util.Random;


public class AudioLine {
	
	public Hashtable<Integer, Sound> soundTable = new Hashtable<Integer, Sound>();
	
	float fLineVolume = 1.0f;
	float fLineGain = 1.0f;
	Integer liID;
	
	
	String sLineName;
	
	AudioLine(String lineName)
	{
		sLineName = lineName;
		AudioMaster.putLine(this);
	}

	
	/**
	 * Generates a random ID for a Sound.
	 * @return Returns the random ID
	 */
	private int genID() 
	{

		int randID = 0;
		Random rand = new Random();
		
		//Generate random number from 1,000,000,000 to 2,147,483,647
		//Generate while there is a value in the table.
		do { 	
			randID = rand.nextInt(999999999) + 100000000; 
	
		} while(soundTable.containsKey(randID)); 
		
		return randID;
	}
	
	/**
	 * Puts a line onto the hash table.
	 * @param line The Audio line to put onto the hash table
	 * @return Returns false on failure, true on success.
	 */
	public boolean putSound(Sound sound)
	{
		Integer soundID = new Integer(genID());

		if (sound.getID() != 0)
			return false;
		
		sound.setID(soundID.intValue());
		soundTable.put(soundID, sound);
		
		return true;
		
	}
	/**
	 * Creates an integer object for the ID out of a primitive int.
	 * @param id The int id.
	 */
	public void setID(int id) { liID = new Integer(id); }
	
	/**
	 * Returns the integer value of the intergerID object.
	 * @return Returns the integer value.
	 */
	
	public int getID() { 
		try 
		{
		return liID.intValue(); 
		} catch (NullPointerException e)
		{
			return -1;
		}
	}
	
	public String getName() { return sLineName;}
	
}
