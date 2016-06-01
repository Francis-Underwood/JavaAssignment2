package factorypattern;

public class ITetromino extends Tetromino {
    public ITetromino(String c) {
        super(c);
    }
    @Override
    public void render() {
        System.out.println("A " + this.getColor() + " I-Tetromino is falling...");
    }
}
