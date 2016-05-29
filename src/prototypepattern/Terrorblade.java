package prototypepattern;

public class Terrorblade extends Character {
	public Terrorblade(int l, int p) {
		super(l, p);
	}
	@Override
	public void render() {
		System.out.println("A Terrorblade is rendered, with life of " + 
				this.getLife() + ", and power of " + this.getPower() + ".");
	}
}
