/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources;

/**
 *
 * @author Vincent
 */
public enum PositionType {

    SALESPERSON("Salesperson"), OTHERS("Others");

    private final String displayName;

    private PositionType(String value) {
        this.displayName = value;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public static PositionType fromString(String value) {
        if (value != null) {
            for (PositionType pos : PositionType.values()) {
                if (value.equalsIgnoreCase(pos.displayName)) {
                    return pos;
                }
            }
        }
        return null;
    }
}
