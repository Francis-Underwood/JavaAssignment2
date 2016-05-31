/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import humanresources.testing.*;
import humanresources.businessdomain.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.stream.Collectors;

/**
 *
 * @author Vincent
 */
public class StartUpTest {
    
    private EmployeeRepository mockEmpyRepo;
    private CustomerRepository mockCustRepo;
    
    
    @Before
    public void setup() {
        
        this.mockEmpyRepo = new EmployeeRepository() {
            private ArrayList<Employee> eList = new ArrayList<Employee>() {
                {
                    add(new OtherStaff(7, "Flower", "Tucci", PositionType.OTHERS));
                    add(new SalesPerson(6, "Lizz", "Tayler", PositionType.SALESPERSON));
                    add(new SalesPerson(3, "Bibi", "Jones", PositionType.SALESPERSON));
                }
            };
            
            @Override
            public Employee get(int eid) {
                Employee e = eList.stream()
                    .filter(cus -> cus.getEid() == 6)
                    .findFirst()
                    .get();
                return e;
            }
            
            @Override
            public ArrayList<Employee> all() {
                return eList;
            }
            
            @Override
            public ArrayList<Employee> getByPosition(PositionType p) {
                ArrayList<Employee> res = eList.stream()
                    .filter(emp -> emp.getPosition()== p)
                    .collect(Collectors.toCollection(ArrayList::new));
                return res;
            }
        };
        
        
        
        this.mockCustRepo = new CustomerRepository() {
            private ArrayList<Customer> cList = new ArrayList<Customer>() {
                    {
                        add(new CustomerPayCash(141, 3, "Brazzers", null, PaymentMethodOption.CASH));
                        add(new CustomerPayWithCreditCard(142, 7, "Diabolic", null, PaymentMethodOption.CREDITCARD));
                        add(new CustomerPayWithCreditCard(143, 7, "Diva Futura", null, PaymentMethodOption.CREDITCARD));
                    }
		};
            @Override
            public ArrayList<Customer> all() {
                return cList;
            }
            
            @Override
            public Customer get(int cid) {
                Customer c = cList.stream()
                    .filter(cus -> cus.getCid() == 142)
                    .findFirst()
                    .get();
                return c;
            }
            
            @Override
            public ArrayList<Customer> getByEmployeeId(int eid) {
                ArrayList<Customer> res = cList.stream()
                    .filter(cus -> cus.getEid() == 7)
                    .collect(Collectors.toCollection(ArrayList::new));
                return res;
            }



            
            
        };
        
        
    }
    
    
    
    @After
    public void cleanUpThePanels() throws InterruptedException {
        Thread.sleep(1000);
        StartUp.cleanUp();
        Thread.sleep(1000);
    }
    
    //@Ignore
    @Test
    public void testClickAllEmployeesNavigateToEmployeeGridPanel() throws Exception {
        StartUp.setCustRepo(this.mockCustRepo);
        StartUp.setEmpyRepo(this.mockEmpyRepo);
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
        StartUp.setCustRepo(this.mockCustRepo);
        StartUp.setEmpyRepo(this.mockEmpyRepo);
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
        StartUp.setCustRepo(this.mockCustRepo);
        StartUp.setEmpyRepo(this.mockEmpyRepo);
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
        StartUp.setCustRepo(this.mockCustRepo);
        StartUp.setEmpyRepo(this.mockEmpyRepo);
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
        StartUp.setCustRepo(this.mockCustRepo);
        StartUp.setEmpyRepo(this.mockEmpyRepo);
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
        StartUp.setCustRepo(this.mockCustRepo);
        StartUp.setEmpyRepo(this.mockEmpyRepo);
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

    //@Ignore
    @Test
    public void testShowMessageBox() throws Exception {
        StartUp.setCustRepo(this.mockCustRepo);
        StartUp.setEmpyRepo(this.mockEmpyRepo);
        StartUp.main(new String[] {"Stoya", "Jesse", "Shay"});
        JFrame container = StartUp.getRootContainer();
        StartUp.showMessageBox("Unit Testing");
        Thread.sleep(200);
        JInternalFrame messageBoxPane = (JInternalFrame)TestUtils.getChildNamed(container, "messageBoxPane");
	assertNotNull(messageBoxPane);
    }
    
}
