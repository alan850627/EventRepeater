import java.awt.AWTException;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Listener mainListener = new Listener();
		while(mainListener.listening) {
			// do nothing
			try {
				Thread.sleep(128);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JOptionPane.showMessageDialog(null, "Listening Complete");
		if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(null,
				"Execute Now? \nFor emergency, press `esc`", "", JOptionPane.YES_NO_OPTION)) {
			System.exit(0);
		}
		
		EventRepeater repeater = new EventRepeater(mainListener.events, 1);
		repeater.repeatEvents();
	}	
}
