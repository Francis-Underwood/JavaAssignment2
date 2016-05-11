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
public enum PaymentMethod {
    
    CASH("Cash"), CREDITCARD("Credit card");
    
    private final String displayName;
    
    private PaymentMethod(String value) {
        this.displayName = value;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
