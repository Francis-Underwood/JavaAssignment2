package mediatorpattern;

public abstract class Colleague {
	private Mediator _mediator;
	private String _name;
	
	public Colleague(Mediator mediator, String name) {
		this._mediator = mediator;
		this._name = name;
		this._mediator.register(this._name, this);
	}
	
	public String getName() {
		return this._name;
	}
	
	public void send(String to, String message) {
		this._mediator.send(this._name, to, message);
	}
	
	public void receive(String from, String message) {
		System.out.println(this._name + " received message from " + from + ": " + message);
		System.out.println("");
	}
}
