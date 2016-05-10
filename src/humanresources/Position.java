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
public enum Position {
    
    SALESPERSON("Salesperson"), OTHERSTUFF("Other stuff");
    
    private final String displayName;
    
    private Position(String value) {
        this.displayName = value;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
