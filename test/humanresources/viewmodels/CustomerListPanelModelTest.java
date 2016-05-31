/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.viewmodels;

import humanresources.businessdomain.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Vincent
 */
public class CustomerListPanelModelTest {

    private CustomerListPanelModel custModel;

    @Before
    public void initModel() {
        ArrayList < Customer > cList = new ArrayList < Customer > () {
            {
                add(new CustomerPayCash(141, 4, "Brazzers", "Brooklyn Lee", PaymentMethodOption.CASH));
                add(new CustomerPayWithCreditCard(142, 8, "Diabolic", "Leilani Leeane", PaymentMethodOption.CREDITCARD));
                add(new CustomerPayWithCreditCard(143, 70, "Diva Futura", "Bibi Jones", PaymentMethodOption.CREDITCARD));
            }
        };
        this.custModel = new CustomerListPanelModel(cList);
    }
        
    //@Ignore
    @Test
    public void testGetColumnCount() {
        // Assert
        assertThat(this.custModel.getColumnCount(), is(4));
    }

    //@Ignore
    @Test
    public void testGetRowCount() {
        // Assert
        assertThat(this.custModel.getRowCount(), is(3));
    }

    //@Ignore
    @Test
    public void testGetColumnName() {
        // Assert
        assertThat(this.custModel.getColumnName(0), is("Customer Id"));
        assertThat(this.custModel.getColumnName(1), is("Customer Name"));
        assertThat(this.custModel.getColumnName(2), is("Payment Method"));
        assertThat(this.custModel.getColumnName(3), is("Agent Name"));
    }

    //@Ignore
    @Test
    public void testGetValueAt() {
        // Assert
        assertThat(this.custModel.getValueAt(0, 2), is("Cash"));
        assertThat(this.custModel.getValueAt(1, 1), is("Diabolic"));
        assertThat(this.custModel.getValueAt(2, 3), is("Bibi Jones"));
        assertThat(this.custModel.getValueAt(99, 3), is(""));
    }

}