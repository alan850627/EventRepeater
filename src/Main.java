import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Listener mainListener = new Listener();
		while(mainListener.listening) {
			// do nothing
			Thread.sleep(128);
		}
		
		JOptionPane.showMessageDialog(null, "Listening Complete");
		if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(null,
				"Execute Now? \nFor emergency, press `esc`", "", JOptionPane.YES_NO_OPTION)) {
			System.exit(0);
		}
	}	
}
