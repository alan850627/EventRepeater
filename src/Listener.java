import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Listener {

	public ArrayList<Event> events = new ArrayList<Event>();
	public boolean listening = false;

	private boolean leftMousePressed = false;
	private boolean rightMousePressed = false;
	
	private GlobalKeyboardHook keyboardHook;
	private GlobalMouseHook mouseHook;

	public Listener() {
		beep();
		if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(null,
				"Listener about to start, \nPress `yes` to start listening", "", JOptionPane.YES_NO_OPTION)) {
			System.exit(0);
		}

		listening = true;
		keyboardHook = new GlobalKeyboardHook();
		mouseHook = new GlobalMouseHook();

		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override
			public void keyPressed(GlobalKeyEvent event) {
				if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
					// Shut down on Escape
					keyboardHook.shutdownHook();
					mouseHook.shutdownHook();
					listening = false;
				} else {
					beep();
					System.out.println("Keyboard: " + event);
					events.add(new Event(Event.KEYPRESS, event));
				}
			}

			@Override
			public void keyReleased(GlobalKeyEvent event) {
				if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
					// Shut down on Escape
					keyboardHook.shutdownHook();
					mouseHook.shutdownHook();
					listening = false;
				} else {
					beep();
					System.out.println("Keyboard: " + event);
					events.add(new Event(Event.KEYRELEASE, event));
				}
			}
		});

		mouseHook.addMouseListener(new GlobalMouseAdapter() {
			@Override
			public void mousePressed(GlobalMouseEvent event) {
				if (!leftMousePressed
						&& (event.getButtons() & GlobalMouseEvent.BUTTON_LEFT) != GlobalMouseEvent.BUTTON_NO) {
					// press left mouse
					beep();
					System.out.println("Mouse: " + event);
					events.add(new Event(Event.MOUSEPRESS_LEFT, event));
					leftMousePressed = true;
				}
				if (!rightMousePressed
						&& (event.getButtons() & GlobalMouseEvent.BUTTON_RIGHT) != GlobalMouseEvent.BUTTON_NO) {
					// press right mouse
					beep();
					System.out.println("Mouse: " + event);
					events.add(new Event(Event.MOUSEPRESS_RIGHT, event));
					rightMousePressed = true;
				}
			}

			@Override
			public void mouseReleased(GlobalMouseEvent event) {
				if (leftMousePressed
						&& (event.getButtons() & GlobalMouseEvent.BUTTON_LEFT) == GlobalMouseEvent.BUTTON_NO) {
					// release left mouse
					beep();
					System.out.println("Mouse: " + event);
					events.add(new Event(Event.MOUSERELEASE_LEFT, event));
					leftMousePressed = false;
				}
				if (rightMousePressed
						&& (event.getButtons() & GlobalMouseEvent.BUTTON_RIGHT) == GlobalMouseEvent.BUTTON_NO) {
					// release right mouse
					beep();
					System.out.println("Mouse: " + event);
					events.add(new Event(Event.MOUSERELEASE_RIGHT, event));
					rightMousePressed = false;
				}
			}
		});
	}

	private void beep() {
		Toolkit.getDefaultToolkit().beep();
	}

}
