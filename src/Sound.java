import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 * TODO: Multi-threading... maybe.
 * - waittime for clip to play.
 * -Clip duration
 * @author Chow
 *
 */

public class Sound {

	private Integer siID;
	private Integer liID;
	private AudioLine audioLine;
	private String sSoundName;
	private String sLineName;
	private String sFileName;
	private File fSoundFile;
	private float dVolumeLinear = 0;
	private float dVolumeDB = 0;
	private double dRadius = 0;
	private double dFrequency = 0;
	private long lStartTime = 0;
	private long lEndTime = 0;
	private Clip soundClip;
	private String sTag;
	
	Sound(String name, String fileName, String lineName)
	{
		sSoundName = name;
		sFileName = fileName;
		sLineName = lineName;
		
		liID = AudioMaster.getLineID(sLineName);
		this.putOnLine(liID);
	}
	
	/**
	 * Puts the sound on a new Audio Line.
	 */
	private void putOnLine(Integer lineID)
	{
		System.out.println("Line ID: " + lineID);
		if (lineID > 0)
		{
			audioLine = AudioMaster.getLine(lineID);
			audioLine.putSound(this);
		}	
	}
	
	/**
	 * Opens the specified audio file.
	 */
	public void open()
	{
		try 
		{
			if (soundClip != null && soundClip.isOpen()) //If open, then close it and reopen.
				soundClip.close();
			
			fSoundFile = new File(sFileName);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(fSoundFile);              
		    soundClip = AudioSystem.getClip();

		    //Open the sound file before playing to ensure the sound plays as commanded.
		    soundClip.open(audioIn);
			
	        
		} catch (UnsupportedAudioFileException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (LineUnavailableException e) 
		{
			//Print stack trace so game doesn't crash on consumer.
			System.out.println(e.getMessage());
		}
		
	}

	public void printClipInfo()
	{
		if (soundClip.isOpen()) {
			System.out.printf("Frame length: %d\n", soundClip.getFrameLength());
			System.out.println("Frame Position: " + soundClip.getFramePosition());
			System.out.println("Microsecond Length: " + soundClip.getMicrosecondLength());
			System.out.println("Microsecond position: " + soundClip.getMicrosecondPosition());
		}
	}
	/**
	 * Plays the sound clip and scales the volume based on the line's settings.
	 */
	public void play() {
		//Plays the sound.
		if (soundClip.isOpen())
		{
			if (audioLine != null && audioLine.getLineVolLinear() != 1.0f)
				this.scaleVolume(audioLine.getLineVolLinear());
			
			soundClip.start();
			
		}
	}
	

	/**
	 *Checks the desired duration by checking the bounds of the sound clip.
	 *@param startTime Start time of the desired duration.
	 *@param endTime End time of the desired duration.
	 */
	private boolean durationInBounds(long startTime, long endTime)
	{
		
		long clipEndTime = soundClip.isOpen() ? 0: soundClip.getMicrosecondLength();
			
		return !(startTime > endTime || startTime < 0 
				|| endTime < 0 || startTime > clipEndTime);
		
	}
	
	
	/**
	 * Sets the Sound emittors sound clip.
	 * Wav files 8khz-48khz, 8bit and 16 bit is supported.
	 * @param filename The file name of the sound clip to set.
	 */
	public void setSoundClip(String filename) 
	{ 
		sFileName = filename; 
		this.open();
	}
	
	/**
	 * Sets the duration of the audio clip in milliseconds.
	 * @param msStartTime The start time of the audio clip in milliseconds
	 * @param msEndTime The end time of the audio clip in milliseconds
	 */
	public boolean setDuration(long msStartTime, long msEndTime) {
		if (!this.durationInBounds(msStartTime, msEndTime))
			return false;
		
		lStartTime = msStartTime;
		lEndTime = msEndTime;
		double frameTimeRatio = (double) soundClip.getFrameLength() / soundClip.getMicrosecondLength();
		int frameStartTime = (int) (frameTimeRatio *  lStartTime);
		int frameEndTime = (int) ( frameTimeRatio * lEndTime);
		soundClip.setLoopPoints(frameStartTime, frameEndTime);
		return true;
	}
	
	/**
	 * Sets the volume of the Sound using Decibels. (Allows for better fine tuning.)
	 * @param decibel Increase the volume by the specified decibel amount.
	 */
	public void setVolDB(float decibel)
	{
		if (soundClip.isOpen())
		{
			FloatControl volControl = this.getVolControl();	
			
			if (decibel < volControl.getMaximum())
			{
				dVolumeDB = decibel;
				dVolumeLinear = ChowFunctions.dbToLinearFrac(decibel);
			}
			else 
			{
				dVolumeDB = volControl.getMaximum();
				dVolumeLinear = 1f;
			}
			
			volControl.setValue(dVolumeDB);

		}
	}
	
	
	/**
	 * LinearVolume Control.
	 * @param The volume to set the clip to. Use 0-100 with 100 being the maximum 
	 * amplitude. of the speakers.
	 */
	
	public void setVolLinear(float volFrac)
	{
		volFrac = volFracInBounds(volFrac);
		dVolumeLinear = volFrac;
		
		float volDB = ChowFunctions.LinearFracToDB(volFrac);
		dVolumeDB = volDB;
		this.setVolDB(volDB);
			
	}
	
	/**
	 * Scales the the volume of the sound based on the fraction.
	 * @param volFrac The fractional amount you wish to scale the sound's volume by.
	 */
	public void scaleVolume(float volFrac)
	{
		volFrac = volFracInBounds(volFrac);
		FloatControl volControl = this.getVolControl();
		float baseVol = volControl.getValue();
		float linearVol = ChowFunctions.dbToLinearFrac(baseVol);
		float scaledVolLinear = linearVol * volFrac;
		float scaledVolDB = ChowFunctions.LinearFracToDB(scaledVolLinear);
		this.setVolDB(scaledVolDB);
		
		dVolumeLinear = scaledVolLinear;
		dVolumeDB = scaledVolDB;
		System.out.println("\nSound: " + sSoundName);
		System.out.printf("base vol: %f, linearVol: %f\n"
				+ "scaledVolLinear:  %f scaledVolDB: %f\n",
				baseVol, linearVol, scaledVolLinear, scaledVolDB);
	}
	
	private float volFracInBounds(float volFrac)
	{
		return (volFrac <= 0 ? 0.0005f : volFrac);
	}

	private FloatControl getVolControl() { return ((FloatControl) soundClip.getControl(
			FloatControl.Type.MASTER_GAIN)); }
	public String getSoundClip() { return sFileName; }
	public float getVolumeLinear() { return dVolumeLinear; }
	public float getVolumeDecibel() { return dVolumeDB; }
	public double getRadius() { return dRadius; }
	public double getFrequency() { return dFrequency; }
	public long getDuration() { return lEndTime - lStartTime; }
	public String getTag() { return sTag; }
	
	/**
	 * Creates an integer object for the ID out of a primitive int.
	 * @param id The int id.
	 */
	public void setID(int id) { siID = new Integer(id); }
	
	/**
	 * Returns the integer value of the intergerID object.
	 * @return Returns the integer value.
	 */
	
	public int getID() { 
		try
		{
			return siID; 
		} catch (NullPointerException e) {
			
			return 0;
		}
	}
}
