/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources;

import javax.swing.*;
import java.awt.*;
import humanresources.testing.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Vincent
 */
public class StartUpTest {
/*
    private JFrame container;
    
    @Before
    public void setup() {
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        this.container = StartUp.getRootContainer();
    }
      
    @After
    public void cleanup() {
        JPanel customerPanel = (JPanel)TestUtils.getChildNamed(this.container, "customerGrid");
        if (customerPanel != null) {
            customerPanel.setName("Katsuni");
        }
    }
*/  
    
    
    
    
 /*   
    @Ignore
    @Test
    public void testClickAllEmployeesNavigateToEmployeeGridPanel() throws Exception {
	JMenuItem allEmployees = (JMenuItem)TestUtils.getChildNamed(this.container, "allEmployees");
	assertNotNull(allEmployees);
        allEmployees.doClick();
        JPanel employeePanel = (JPanel)TestUtils.getChildNamed(this.container, "employeeGrid");
        assertNotNull(employeePanel);
        assertTrue(employeePanel.isShowing());
    }
    
    @Ignore
    @Test
    public void testClickAllSalespersonsNavigateToEmployeeGridPanel() throws Exception {
	JMenuItem allSalespersons = (JMenuItem)TestUtils.getChildNamed(this.container, "allSalespersons");
	assertNotNull(allSalespersons);
        allSalespersons.doClick();
        JPanel employeePanel = (JPanel)TestUtils.getChildNamed(this.container, "employeeGrid");
        assertNotNull(employeePanel);
        assertTrue(employeePanel.isShowing());
    }
    
    @Ignore
    @Test
    public void testClickAllOtherStaffsNavigateToEmployeeGridPanel() throws Exception {
	JMenuItem allOtherstaffs = (JMenuItem)TestUtils.getChildNamed(this.container, "allOtherstaffs");
	assertNotNull(allOtherstaffs);
        allOtherstaffs.doClick();
        JPanel employeePanel = (JPanel)TestUtils.getChildNamed(this.container, "employeeGrid");
        assertNotNull(employeePanel);
        assertTrue(employeePanel.isShowing());
    }
    
        
    @Ignore
    @Test
    public void testViewCustomersOfSalesPerson() throws Exception {
        JTable employeeTable = (JTable)TestUtils.getChildNamed(this.container, "employeeTable");	// TestUtils is a 3rd party lib
	assertNotNull(employeeTable);
        // select a sales person, and click view button
        employeeTable.setRowSelectionInterval(1, 1);
        JButton viewButtonInEmployeePanel = (JButton)TestUtils.getChildNamed(this.container, "viewButtonInEmployeePanel");	// TestUtils is a 3rd party lib
	assertNotNull(viewButtonInEmployeePanel);
        viewButtonInEmployeePanel.doClick();
        // check if the element shows
        JPanel customerPanel = (JPanel)TestUtils.getChildNamed(this.container, "customerGrid");
        assertNotNull(customerPanel);
        assertTrue(customerPanel.isShowing());
    }
  */
    
    
    //@Ignore
    @Test
    public void testClickAllCustomerNavigateToCustomerGridPanel() throws Exception {
        //junit.textui.TestRunner.run(StartUp.class);
        //junit.textui.TestRunner.run(testClass);
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
	JMenuItem allCustomers = (JMenuItem)TestUtils.getChildNamed(container, "allCustomers");	// TestUtils is a 3rd party lib
	assertNotNull(allCustomers);
        allCustomers.doClick();
        JPanel customerPanel = (JPanel)TestUtils.getChildNamed(container, "customerGrid");
        assertNotNull(customerPanel);
        assertTrue(customerPanel.isShowing());
    }

    
    //@Ignore
    @Test
    public void testViewCustomersOfOtherStaff() throws Exception {
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
        JTable employeeTable = (JTable)TestUtils.getChildNamed(container, "employeeTable");	// TestUtils is a 3rd party lib
	assertNotNull(employeeTable);
        // select a other staff, and click view button
        employeeTable.setRowSelectionInterval(0, 0);
        JButton viewButtonInEmployeePanel = (JButton)TestUtils.getChildNamed(container, "viewButtonInEmployeePanel");	// TestUtils is a 3rd party lib
	assertNotNull(viewButtonInEmployeePanel);
        viewButtonInEmployeePanel.doClick();
        // check if the element is null
        JPanel customerPanel = (JPanel)TestUtils.getChildNamed(container, "customerGrid");
        //assertNull(customerPanel);
        //assertTrue(customerPanel == null || !customerPanel.isShowing());
        assertTrue(customerPanel.isShowing());
    }
    
/*
    @Test
    public void testShowMessageBox() {
    }
  */  
}
