
public class Event {
	public static final int KEYPRESS = 0;
	public static final int KEYRELEASE = 1;
	public static final int MOUSEPRESS = 2;
	public static final int MOUSERELEASE = 3;

	int type;
	Object data;

	public Event() {
		type = -1;
		data = null;
	}
	
	public Event(int t, Object d) {
		type = t;
		data = d;
	}
}
