package factorypattern;

public class TetrominoFactory {
	public Tetromino getTetromino(TetrominoType tType, String c) {
		switch (tType) {
			case ITetromino:
				return new ITetromino(c);
			case JTetromino:
				return new JTetromino(c);
			case LTetromino:
				return new LTetromino(c);
			default:
				return null;
		}
	}
}
