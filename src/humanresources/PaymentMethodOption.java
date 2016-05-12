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
public enum PaymentMethodOption {
    
    CASH("Cash"), CREDITCARD("Credit card");
    
    private final String displayName;
    
    private PaymentMethodOption(String value) {
        this.displayName = value;
    }

    public String getDisplayName() {
        return this.displayName;
    }
    
    public static PaymentMethodOption fromString(String value) {
        if (value != null) {
            for (PaymentMethodOption pto : PaymentMethodOption.values()) {
                if (value.equalsIgnoreCase(pto.displayName)) {
                    return pto;
                }
            }
        }
        return null;
    }
    
}
