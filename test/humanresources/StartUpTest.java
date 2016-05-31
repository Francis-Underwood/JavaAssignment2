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
    
    @After
    public void cleanUpThePanels() throws InterruptedException {
        Thread.sleep(1000);
        StartUp.cleanUp();
        Thread.sleep(1000);
    }
    
    //@Ignore
    @Test
    public void testClickAllEmployeesNavigateToEmployeeGridPanel() throws Exception {
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
	JMenuItem allEmployees = (JMenuItem)TestUtils.getChildNamed(container, "allEmployees");
	assertNotNull(allEmployees);
        allEmployees.doClick();
        Thread.sleep(200);
        JPanel employeePanel = (JPanel)TestUtils.getChildNamed(container, "employeeGrid");
        assertNotNull(employeePanel);
        assertTrue(employeePanel.isShowing());
    }
    
    //@Ignore
    @Test
    public void testClickAllSalespersonsNavigateToEmployeeGridPanel() throws Exception {
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
	JMenuItem allSalespersons = (JMenuItem)TestUtils.getChildNamed(container, "allSalespersons");
	assertNotNull(allSalespersons);
        allSalespersons.doClick();
        Thread.sleep(200);
        JPanel employeePanel = (JPanel)TestUtils.getChildNamed(container, "employeeGrid");
        assertNotNull(employeePanel);
        assertTrue(employeePanel.isShowing());
    }
    
    //@Ignore
    @Test
    public void testClickAllOtherStaffsNavigateToEmployeeGridPanel() throws Exception {
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
	JMenuItem allOtherstaffs = (JMenuItem)TestUtils.getChildNamed(container, "allOtherstaffs");
	assertNotNull(allOtherstaffs);
        allOtherstaffs.doClick();
        Thread.sleep(200);
        JPanel employeePanel = (JPanel)TestUtils.getChildNamed(container, "employeeGrid");
        assertNotNull(employeePanel);
        assertTrue(employeePanel.isShowing());
    }
        
    //@Ignore
    @Test
    public void testViewCustomersOfSalesPerson() throws Exception {
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
        JTable employeeTable = (JTable)TestUtils.getChildNamed(container, "employeeTable");
	assertNotNull(employeeTable);
        // select a sales person, and click view button
        employeeTable.setRowSelectionInterval(1, 1);
        JButton viewButtonInEmployeePanel = (JButton)TestUtils.getChildNamed(container, "viewButtonInEmployeePanel");
	assertNotNull(viewButtonInEmployeePanel);
        viewButtonInEmployeePanel.doClick();
        Thread.sleep(200);
        // check if the element shows
        JPanel customerPanel = (JPanel)TestUtils.getChildNamed(container, "customerGrid");
        assertNotNull(customerPanel);
        assertTrue(customerPanel.isShowing());
    }
    
    //@Ignore
    @Test
    public void testClickAllCustomerNavigateToCustomerGridPanel() throws Exception {
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
	JMenuItem allCustomers = (JMenuItem)TestUtils.getChildNamed(container, "allCustomers");
	assertNotNull(allCustomers);
        allCustomers.doClick();
        Thread.sleep(200);
        JPanel customerPanel = (JPanel)TestUtils.getChildNamed(container, "customerGrid");
        assertNotNull(customerPanel);
        assertTrue(customerPanel.isShowing());
    }
    
    //@Ignore
    @Test
    public void testViewCustomersOfOtherStaff() throws Exception {
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
        JTable employeeTable = (JTable)TestUtils.getChildNamed(container, "employeeTable");
	assertNotNull(employeeTable);
        // select a other staff, and click view button
        employeeTable.setRowSelectionInterval(0, 0);
        JButton viewButtonInEmployeePanel = (JButton)TestUtils.getChildNamed(container, "viewButtonInEmployeePanel");
	assertNotNull(viewButtonInEmployeePanel);
        viewButtonInEmployeePanel.doClick();
        Thread.sleep(200);
        // check if the element is null
        JPanel customerPanel = (JPanel)TestUtils.getChildNamed(container, "customerGrid");
        assertNull(customerPanel);
    }
    
/*
    @Test
    public void testShowMessageBox() {
    }
  */  
}
