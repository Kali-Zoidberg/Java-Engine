package jengine;

/*
 * Maybe this should just inherit sound sample space instead??
 * 
 */
public class SoundEmittor extends Component {
	private float fVolumeDropOff = 0.0f;
	public String sFileName;
	public Sound sound;
	
	public SoundEmittor(GameObject mentor, String name, String filename, String lineName) 
	{
		super(mentor, name);
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
	

	public void setVolumeDropOff(float decibelPerUnit){fVolumeDropOff = decibelPerUnit; }
	
	public float getVolumeDropOff() {return fVolumeDropOff; }
	
		
}
