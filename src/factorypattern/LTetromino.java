package factorypattern;

public class LTetromino extends Tetromino {
    public LTetromino(String c) {
        super(c);
    }
    @Override
    public void render() {
        System.out.println("A " + this.getColor() + " L-Tetromino is falling...");
    }
}
