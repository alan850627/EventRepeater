import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.KeyStroke;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class EventRepeater {
	private ArrayList<Event> events = new ArrayList<Event>();
	private int numToRepeat = 0;
	private boolean everythingOk = true;
	private Robot robot;

	public EventRepeater() {
		events = null;
	}

	public EventRepeater(ArrayList<Event> e, int n) {
		numToRepeat = n;
		events = e;

		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override
			public void keyReleased(GlobalKeyEvent event) {
				if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
					// Shut down on Escape
					everythingOk = false;
					keyboardHook.shutdownHook();
				}
			}
		});
	}

	public void repeatEvents() {
		for (int i = 0; i < numToRepeat; i++) {
			runEvents();
		}
	}

	public void runEvents() {
		for (Event event : events) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (event.type == Event.KEYPRESS) {
				int ks = proccessKeyEvent((GlobalKeyEvent) event.data);
				robot.keyPress(ks);
			}
			if (event.type == Event.KEYRELEASE) {
				int ks = proccessKeyEvent((GlobalKeyEvent) event.data);
				robot.keyRelease(ks);
			}
			if (event.type == Event.MOUSEPRESS) {

			}
			if (event.type == Event.MOUSERELEASE) {

			}
		}
	}
	
	private int proccessKeyEvent(GlobalKeyEvent e) {
		int keyChar = e.getVirtualKeyCode();
		int ks = 0;
		switch (keyChar) {
		case 13:
			ks = KeyEvent.VK_ENTER;
			break;
		case 160:
			ks = KeyEvent.VK_SHIFT;
			break;
		case 162: 
			ks = KeyEvent.VK_CONTROL;
			break;
		default:
			ks = KeyEvent.getExtendedKeyCodeForChar(keyChar);
		}		
		return ks;
	}
}
