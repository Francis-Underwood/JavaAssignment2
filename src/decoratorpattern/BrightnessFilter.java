package decoratorpattern;

public class BrightnessFilter implements IComponent {
	private IComponent _component;
	private int _strength;
	public BrightnessFilter(IComponent com, int strength) {
		this._component = com;
		this._strength = strength;
	}
	public String operate() {
		String s = this._component.operate();
		return s + " adjusted brightness by strength of " + this._strength + ",";
	}
}
