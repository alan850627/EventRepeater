
public class Main {

	public static void main(String[] args) throws InterruptedException {
		Listener mainListener = new Listener();
		while(mainListener.listening) {
			// do nothing
			Thread.sleep(128);
		}
	}	
}
