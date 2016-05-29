package prototypepattern;

public abstract class Character implements Cloneable{
	private int _life;
	private int _power;
	
	public Character(int l, int p) {
		this._life = l;
		this._power = p;
	}
	
	public abstract void render();
	
	public int getLife() {
		return this._life;
	}
	
	public void setLife(int l) {
		this._life = l;
	}
	
	public int getPower() {
		return this._power;
	}
	
	public Object clone() {
		Object clone = null;
      
		try {
			clone = super.clone();
         
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
      
      return clone;
   }
}
