package mediatorpattern;

import java.util.Hashtable;

public abstract class Mediator {
	
	private final Hashtable<String, Colleague> _colleagues = new Hashtable<String, Colleague>();
	
	public void register(String name, Colleague colleague) {
		_colleagues.put(name, colleague);
	}
	
	// TODO: un-register
	// TODO: block
	// TODO: un-block
    
	public void send(String from, String to, String message) {	// TODO: broadcast to multiple target
    	Colleague destination = (Colleague)this._colleagues.get(to);
    	if (destination != null) {
    		destination.receive(from, message);
    	}
    }
}
