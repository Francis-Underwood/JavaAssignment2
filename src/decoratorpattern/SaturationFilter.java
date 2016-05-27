package decoratorpattern;

public class SaturationFilter implements IComponent {
	private IComponent _component;
	private int _strength;
	public SaturationFilter(IComponent com, int strength) {
		this._component = com;
		this._strength = strength;
	}
	public String operate() {
		String s = this._component.operate();
		return s + " adjusted saturation by strength of " + this._strength + ",";
	}
}
