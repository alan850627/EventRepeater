import java.sql.Timestamp;

public class Event {
	public static final int KEYPRESS = 0;
	public static final int KEYRELEASE = 1;
	public static final int MOUSEPRESS = 2;
	public static final int MOUSERELEASE = 3;

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
