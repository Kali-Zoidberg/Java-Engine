
/*
 * Maybe this should just inherit sound sample space instead??
 * 
 */
public class SoundEmittor extends GameObject {
	
	public String sFileName;
	public Sound sound;
	
	SoundEmittor(double x, double y, String name) 
	{
		super(x, y, name);
	}
	
	SoundEmittor(double x, double y, String name, String filename, String lineName) 
	{
		super(x, y, name);
		
		sound = new Sound(name, filename, lineName);
		sFileName = filename;
		sound.open();
	}
	
	/**
	 * Plays the sound emittor's sound file.
	 */
	public void play() {
		//Plays the sound.
		sound.play();
		
	}
	
	/**
	 * Sets the Sound emittors sound clip.
	 * Wav files 8khz-48khz, 8bit and 16 bit is supported.
	 * @param filename The file name of the sound clip to set.
	 */
	public void setSoundClip(String filename) 
	{ 
		sFileName = filename; 
		sound.open();
	}
	
	
	
	
		
}
