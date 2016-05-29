package factorypattern;

public class FactoryPatternDemo {

	public static void main(String[] args) {
		System.out.println("***Factory Pattern Demo***"); 
		System.out.println();
		
		TetrominoFactory factory = new TetrominoFactory();
		
		Tetromino l1 = factory.getTetromino(TetrominoType.LTetromino, "red");
		l1.render();
		System.out.println();
		
		Tetromino j1 = factory.getTetromino(TetrominoType.JTetromino, "green");
		j1.render();
		System.out.println();
		
		Tetromino i1 = factory.getTetromino(TetrominoType.ITetromino, "pink");
		i1.render();
		System.out.println();
		
		Tetromino l2 = factory.getTetromino(TetrominoType.LTetromino, "purple");
		l2.render();
		System.out.println();

	}

}
