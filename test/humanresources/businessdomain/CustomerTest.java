/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Vincent
 */
public class CustomerTest {
   
    private Customer cust;
    
    @Before
    public void createCustomer() {
        this.cust = new Customer(141, 13, "Digital Playground", 
                "Alexis Texas", PaymentMethodOption.CASH);
    }
    
    @Test
    public void testGetCid() {
        // Assert
        assertThat(this.cust.getCid(), is(141));
    }

    @Test
    public void testSetCid() {
        // Act
        this.cust.setCid(90);
        // Assert
        assertThat(this.cust.getCid(), is(90));
    }

    @Test
    public void testGetEid() {
        // Assert
        assertThat(this.cust.getEid(), is(13));
    }

    @Test
    public void testSetEid() {
        // Act
        this.cust.setEid(1005);
        // Assert
        assertThat(this.cust.getEid(), is(1005));
    }

    @Test
    public void testGetCname() {
        // Assert
        assertThat(this.cust.getCname(), is("Digital Playground"));
    }

    @Test
    public void testSetCname() {
        // Act
        this.cust.setCname("Three Degree");
        // Assert
        assertThat(this.cust.getCname(), is("Three Degree"));
    }

    @Test
    public void testGetAgentName() {
        // Assert
        assertThat(this.cust.getAgentName(), is("Alexis Texas"));
    }

    @Test
    public void testSetAgentName() {
        // Act
        this.cust.setAgentName("Jennifer White");
        // Assert
        assertThat(this.cust.getAgentName(), is("Jennifer White"));
    }

    @Test
    public void testGetPaymentMethod() {
        // Assert
        assertThat(this.cust.getPaymentMethod(), is(PaymentMethodOption.CASH));
    }

    @Test
    public void testSetPaymentMethod() {
        // Act
        this.cust.setPaymentMethod(PaymentMethodOption.CREDITCARD);
        // Assert
        assertThat(this.cust.getPaymentMethod(), is(PaymentMethodOption.CREDITCARD));
    }
    
}
