import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class EventRepeater {
	private ArrayList<Event> events = new ArrayList<Event>();
	private int numToRepeat = 0;
	private boolean everythingOk = true;
	private Robot robot;
	private GlobalKeyboardHook keyboardHook;

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

		keyboardHook = new GlobalKeyboardHook();
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override
			public void keyReleased(GlobalKeyEvent event) {
				if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
					// Shut down on Escape
					everythingOk = false;
				}
			}
		});
	}

	public void repeatEvents() {
		String file = "";
		StringSelection stringSelection;
		for (int i = 1; everythingOk && i < numToRepeat; i++) {
			stringSelection = new StringSelection(file + (i+1) + ".pdf");
			Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
			clpbrd.setContents(stringSelection, null);
			
			runEvents();
		}
		keyboardHook.shutdownHook();
	}

	public void runEvents() {
		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			try {
				if (i == 0) {
					Thread.sleep(1000);
				} else {
					Thread.sleep(event.time.getTime() - events.get(i - 1).time.getTime());
				}
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
			if (event.type == Event.MOUSEPRESS_LEFT) {
				GlobalMouseEvent e = (GlobalMouseEvent) event.data;
				robot.mouseMove(e.getX(), e.getY());
				robot.mousePress(InputEvent.BUTTON1_MASK);
			}
			if (event.type == Event.MOUSEPRESS_RIGHT) {
				GlobalMouseEvent e = (GlobalMouseEvent) event.data;
				robot.mouseMove(e.getX(), e.getY());
				robot.mousePress(InputEvent.BUTTON3_MASK);
			}
			if (event.type == Event.MOUSERELEASE_LEFT) {
				GlobalMouseEvent e = (GlobalMouseEvent) event.data;
				robot.mouseMove(e.getX(), e.getY());
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			if (event.type == Event.MOUSERELEASE_RIGHT) {
				GlobalMouseEvent e = (GlobalMouseEvent) event.data;
				robot.mouseMove(e.getX(), e.getY());
				robot.mouseRelease(InputEvent.BUTTON3_MASK);
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
			break;
		}
		return ks;
	}
}
