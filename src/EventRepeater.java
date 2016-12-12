import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
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

	public EventRepeater(ArrayList<Event> e, int n) throws AWTException {
		numToRepeat = n;
		events = e;
		robot = new Robot();
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
		
	}

	public void runEvents() {
		for (Event event : events) {
			if (event.type == Event.KEYBOARD) {
			}
			if (event.type == Event.MOUSE) {
				
			}
		}
	}
}
