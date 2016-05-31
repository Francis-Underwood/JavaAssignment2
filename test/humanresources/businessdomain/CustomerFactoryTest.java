/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent
 */
public class CustomerFactoryTest {
    
    private CustomerFactory custFactory;
    
    @Before
    public void initializeCustomerFactory() {
        this.custFactory = new CustomerFactory();
    }
    
    //@Ignore
    @Test
    public void testCreateCustomerPayCash() {
        // Act
        Customer temp = this.custFactory.createCustomer(141, 80, "Elegent Angel", null, PaymentMethodOption.CASH);
        // Assert
	assertTrue(temp instanceof CustomerPayCash);
    }
    
    //@Ignore
    @Test
    public void testCreateCustomerPayWithCreditCard() {
        // Act
        Customer temp = this.custFactory.createCustomer(141, 80, "Elegent Angel", null, PaymentMethodOption.CREDITCARD);
        // Assert
	assertTrue(temp instanceof CustomerPayWithCreditCard);
    }
    
}
