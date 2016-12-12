import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

public class Listener {
	public Listener() {
		beep();
		if (JOptionPane.YES_OPTION != JOptionPane.showConfirmDialog(null,
				"Listener about to start, \nPress `yes` to start listening", "", JOptionPane.YES_NO_OPTION)) {
			System.exit(0);
		}

		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
		GlobalMouseHook mouseHook = new GlobalMouseHook();
		
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override
			public void keyReleased(GlobalKeyEvent event) {
				System.out.println(event);
				if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_ESCAPE) {
					// Shut down on Escape
					keyboardHook.shutdownHook();
					mouseHook.shutdownHook();
				}

			}
		});
		
		mouseHook.addMouseListener(new GlobalMouseAdapter() {
			@Override
			public void mouseReleased(GlobalMouseEvent event) {
				System.out.println("Mouse: " + event);
			}
		});

		JOptionPane.showMessageDialog(null, "Listening... Press `Esc` to end");
	}

	private void beep() {
		Toolkit.getDefaultToolkit().beep();
	}

}
