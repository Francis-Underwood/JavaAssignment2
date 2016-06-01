package factorypattern;

public class JTetromino extends Tetromino {
    public JTetromino(String c) {
        super(c);
    }
    @Override
    public void render() {
        System.out.println("A " + this.getColor() + " J-Tetromino is falling...");
    }
}
