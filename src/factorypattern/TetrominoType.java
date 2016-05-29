package factorypattern;

public enum TetrominoType {
	ITetromino("I-Tetromino"), 
	JTetromino("J-Tetromino"),
	LTetromino("L-Tetromino");
    
    private final String _displayName;
    
    private TetrominoType(String value) {
        this._displayName = value;
    }

    public String getDisplayName() {
        return this._displayName;
    }
    
    public static TetrominoType fromString(String value) {
        if (value != null) {
            for (TetrominoType t : TetrominoType.values()) {
                if (value.equalsIgnoreCase(t._displayName)) {
                    return t;
                }
            }
        }
        return null;
    }
    
}
