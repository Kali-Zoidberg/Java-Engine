package jengine;
import java.util.Hashtable;
import java.util.Random;


public class AudioLine {
	
	public Hashtable<Integer, Sound> soundTable = new Hashtable<Integer, Sound>();
	
	private float fLineVolume = 1.0f;
	private float fLineVolDB = 1.0f;
	private Integer liID;
	
	
	private String sLineName;
	
	public AudioLine(String lineName)
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
	 * Sets the volume for all sounds on the line to the specified decibel value.
	 * @param volDB The decibel value to set the sounds to.
	 * 
	 * Todo: Make it so that it's relative. 
	 */
	public void setLineVolDB(float volDB)
	{
		fLineVolume = ChowFunctions.dbToLinearFrac(volDB);
		fLineVolDB = volDB;
		Iterable<Sound> sounds = soundTable.values();
		for (Sound sound: sounds)
		{
			sound.setVolDB(volDB);
		}
	}
	
	/**
	 * Sets the volume for all sounds on the line to the specified value.
	 * @param volFrac The linear value from 0 to 1.0 that you wish to set the line volume to.
	 * TODO: Make it relative.
	 */
	public void setLineVolLinear(float volFrac)
	{
		fLineVolume = volFrac;
		fLineVolDB = ChowFunctions.LinearFracToDB(volFrac);
		Iterable<Sound> sounds = soundTable.values();
		for (Sound sound: sounds)
		{
			sound.setVolLinear(volFrac);
		}
	}
	
	public void scaleLineVol(float volFrac)
	{
		fLineVolume = volFrac;
		fLineVolDB = ChowFunctions.LinearFracToDB(volFrac);
		Iterable<Sound> sounds = soundTable.values();
		for (Sound sound: sounds)
		{
			sound.scaleVolume(volFrac);
		}
	}
	
	/**
	 * Returns the specified sound id from the table.
	 * @param id The id of the sound you wish to obtain
	 * @return Returns the sound object of the sound id.
	 */
	public Sound getSound(Integer id) {return soundTable.get(id); }
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
			return liID; 
		} catch (NullPointerException e)
		{
			return -1;
		}
	}
	
	public String getName() { return sLineName;}
	
	/**
	 * Getter for the linear representation of the volume.
	 * @return Returns the linear volume representation
	 */
	public float getLineVolLinear() { return fLineVolume;}
	/**
	 * Getter for the decibel representation of the volume.
	 * @return Returns the decibel representation of the volume.
	 */
	public float getLineVolDB() { return fLineVolDB; }
	
}
