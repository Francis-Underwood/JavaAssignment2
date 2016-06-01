package decoratorpattern;

public class ContrastFilter implements IComponent {
    private IComponent _component;
    private int _strength;
    public ContrastFilter(IComponent com, int strength) {
        this._component = com;
        this._strength = strength;
    }
    public String operate() {
        String s = this._component.operate();
        return s + " adjusted contrast by strength of " + this._strength + ",";
    }
}
