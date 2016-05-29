package factorypattern;

public abstract class Tetromino {
	private String _color;
	public Tetromino(String c) {
		this._color = c;
	}
	public String getColor() {
		return this._color;
	}
	public abstract void render();
}
