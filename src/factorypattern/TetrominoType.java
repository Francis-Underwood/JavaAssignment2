package factorypattern;

public enum TetrominoType {
    ITetromino("I-Tetromino"), 
    JTetromino("J-Tetromino"),
    LTetromino("L-Tetromino");
    
    private final String _displayName;
    
    private TetrominoType(String value) {
        this._displayName = value;
    }
}
