/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Vincent
 */
public class PaymentMethodOptionTest {

    @Test
    public void testGetDisplayName() {
        // Arrange
        PaymentMethodOption myCashOpt = PaymentMethodOption.CASH;
        PaymentMethodOption myCreditOpt = PaymentMethodOption.CREDITCARD;
        // Act
        String actualResultCash = myCashOpt.getDisplayName();
        String actualResultCredit = myCreditOpt.getDisplayName();
        // Assert
        assertThat(actualResultCash, equalTo("Cash"));
        assertThat(actualResultCredit, equalTo("Credit card"));
    }

    @Test
    public void testFromString() {
        // Arrange
        String cash = "Cash";
        String credit = "Credit card";
        // Act
        PaymentMethodOption myActualCashOpt = PaymentMethodOption.fromString(cash);
        PaymentMethodOption myActualCreditOpt = PaymentMethodOption.fromString(credit);
        // Assert
        assertThat(myActualCashOpt, equalTo(PaymentMethodOption.CASH));
        assertThat(myActualCreditOpt, equalTo(PaymentMethodOption.CREDITCARD));
    }
    
}
