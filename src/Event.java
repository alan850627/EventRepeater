
public class Event {
	
	String type;
	Object data;

	public Event() {
		type = null;
		data = null;
	}
	
	public Event(String t, Object d) {
		type = t;
		data = d;
	}
}
