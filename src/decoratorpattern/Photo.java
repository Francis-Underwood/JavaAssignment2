package decoratorpattern;

public class Photo implements IComponent{
	private String _name;
	public Photo(String n) {
		this._name = n;
	}
	public String operate() {
		return this._name + "'s photo";
	}
}
