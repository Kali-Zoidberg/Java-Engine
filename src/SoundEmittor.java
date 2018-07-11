
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
	
	
	public void setSoundClip(String filename) 
	{ 
		sFileName = filename; 
		sound.open();
		//set sound thingy to file name?
	}
	
	
	
	
		
}
