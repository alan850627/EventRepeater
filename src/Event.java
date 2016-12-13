import java.sql.Timestamp;

public class Event {
	public static final int KEYPRESS = 0;
	public static final int KEYRELEASE = 1;
	public static final int MOUSEPRESS_LEFT = 2;
	public static final int MOUSERELEASE_LEFT = 3;
	public static final int MOUSEPRESS_RIGHT = 4;
	public static final int MOUSERELEASE_RIGHT = 5;

	int type;
	Object data;
	Timestamp time;

	public Event() {
		type = -1;
		data = null;
	}
	
	public Event(int t, Object d) {
		time = new Timestamp(System.currentTimeMillis());
		type = t;
		data = d;
	}
}
