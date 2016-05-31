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
public class EmployeeFactoryTest {
    
    private EmployeeFactory empyFactory;

    @Before
    public void initializeCustomerFactory() {
        this.empyFactory = new EmployeeFactory();
    }
    
    //@Ignore
    @Test
    public void testCreateSalesPerson() {
        // Act
        Employee temp = this.empyFactory.createEmployee(PositionType.SALESPERSON, 141, "Teagan", "Presley");
        // Assert
	assertTrue(temp instanceof SalesPerson);
    }
    
    //@Ignore
    @Test
    public void testCreateOtherStaff() {
        // Act
        Employee temp = this.empyFactory.createEmployee(PositionType.OTHERS, 141, "Teagan", "Presley");
        // Assert
	assertTrue(temp instanceof OtherStaff);
    }
    
}
