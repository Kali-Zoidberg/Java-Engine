
public class UpdateRunnable implements Runnable{
	
	public void run() {
		try {
			Render.calculateFrameRate(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initializeController(Controls controller) {
		
	}
	/*
	public static void main(String args[]) throws InterruptedException {
		Thread ur_thread = new Thread( new UpdateRunnable());
		ur_thread.start();

	}
	*/
}
