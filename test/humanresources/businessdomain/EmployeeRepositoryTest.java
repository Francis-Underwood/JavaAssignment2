/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

import java.util.*;
import java.sql.SQLException;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers. * ;

/**
 *
 * @author Vincent
 */
public class EmployeeRepositoryTest {
 
    private CustomerRepository custRepo;
    private EmployeeRepository empyRepo;

    @Before
    public void switchToTestDatabaseAndCleanUp() throws SQLException {
        this.custRepo = new CustomerRepository();
        this.custRepo.setURL("jdbc:mysql://localhost:3306/vinc_humanresource_utest");
        this.custRepo.deleteAll();
        this.empyRepo = new EmployeeRepository();
        this.empyRepo.setURL("jdbc:mysql://localhost:3306/vinc_humanresource_utest");
        this.empyRepo.deleteAll();
    }
        
        
    //@Ignore
    @Test
    public void testInsertOneEmployeeRecordIntoDataBase() throws SQLException {
        // Arrange
        Employee etemp = new SalesPerson(0, "Nikki", "Benz", PositionType.SALESPERSON);
        Employee etempCopy = null;

        // Act
        int eid = this.empyRepo.add(etemp);
        etempCopy = this.empyRepo.get(eid);

        // Assert
        assertThat(etempCopy.getFname(), is("Nikki"));
    }
    
    //@Ignore
    @Test
    public void testDeleteAllEmployeeRecordsFromDatabase() throws SQLException {
        // Arrange
        List<Employee> eList = new ArrayList<Employee>() {
            {
                add(new OtherStaff(0, "Flower", "Tucci", PositionType.OTHERS));
                add(new SalesPerson(0, "Lizz", "Tayler", PositionType.SALESPERSON));
                add(new SalesPerson(0, "Bibi", "Jones", PositionType.SALESPERSON));
            }
        };
        for (Employee temp: eList) {
            int cid = this.empyRepo.add(temp);
            temp.setEid(cid);
        }

        // Act
        int res = this.empyRepo.deleteAll();

        // Assert
        assertTrue(res > 0);
    }

    //@Ignore
    @Test
    public void testRetrieveAllEmployeeRecordsFromDatabase() throws SQLException {
        // Arrange
        List<Employee> eList = new ArrayList<Employee>() {
            {
                add(new OtherStaff(0, "Flower", "Tucci", PositionType.OTHERS));
                add(new SalesPerson(0, "Lizz", "Tayler", PositionType.SALESPERSON));
                add(new SalesPerson(0, "Bibi", "Jones", PositionType.SALESPERSON));
            }
        };
        for (Employee temp: eList) {
            int cid = this.empyRepo.add(temp);
            temp.setEid(cid);
        }

        // Act
        ArrayList<Employee> eListCopy = this.empyRepo.all();

        // Assert
        assertTrue(eListCopy.size() > 0);
    }

    //@Ignore
    @Test
    public void testDeleteOneEmployeeRecordByItsId() throws SQLException {
        // Arrange
        List<Employee> eList = new ArrayList<Employee>() {
            {
                add(new OtherStaff(0, "Flower", "Tucci", PositionType.OTHERS));
                add(new SalesPerson(0, "Lizz", "Tayler", PositionType.SALESPERSON));
                add(new SalesPerson(0, "Bibi", "Jones", PositionType.SALESPERSON));
            }
        };
        for (Employee temp: eList) {
            int cid = this.empyRepo.add(temp);
            temp.setEid(cid);
        }

        // Act
        boolean res = this.empyRepo.delete((eList.get(0)).getEid());

        // Assert
        assertTrue(res);
    }

    //@Ignore
    @Test
    public void testUpdateOneEmployeeRecordByItsId() throws SQLException {
        // Arrange
        List<Employee> eList = new ArrayList<Employee>() {
            {
                add(new OtherStaff(0, "Flower", "Tucci", PositionType.OTHERS));
                add(new SalesPerson(0, "Lizz", "Tayler", PositionType.SALESPERSON));
                add(new SalesPerson(0, "Bibi", "Jones", PositionType.SALESPERSON));
            }
        };
        for (Employee temp: eList) {
            int cid = this.empyRepo.add(temp);
            temp.setEid(cid);
        }

        // Act
        (eList.get(0)).setFname("Stormy");
        this.empyRepo.update(eList.get(0));
        Employee empy = this.empyRepo.get((eList.get(0)).getEid());

        // Assert
        assertThat(empy.getFname(), is("Stormy"));
    }

    //@Ignore
    @Test
    public void testRetrieveOneEmployeeRecordByItsId() throws SQLException {
        // Arrange
        List<Employee> eList = new ArrayList<Employee>() {
            {
                add(new OtherStaff(0, "Flower", "Tucci", PositionType.OTHERS));
                add(new SalesPerson(0, "Lizz", "Tayler", PositionType.SALESPERSON));
                add(new SalesPerson(0, "Bibi", "Jones", PositionType.SALESPERSON));
            }
        };
        for (Employee temp: eList) {
            int cid = this.empyRepo.add(temp);
            temp.setEid(cid);
        }

        // Act
        Employee empy = this.empyRepo.get((eList.get(1)).getEid());

        // Assert
        assertThat(empy.getLname(), is("Tayler"));
    }

    //@Ignore
    @Test
    public void testRetrieveEmployeeRecordsByTheirPositionType() throws SQLException {
        // Arrange
        List<Employee> eList = new ArrayList<Employee>() {
            {
                add(new OtherStaff(0, "Flower", "Tucci", PositionType.OTHERS));
                add(new SalesPerson(0, "Lizz", "Tayler", PositionType.SALESPERSON));
                add(new SalesPerson(0, "Bibi", "Jones", PositionType.SALESPERSON));
            }
        };
        for (Employee temp: eList) {
            int cid = this.empyRepo.add(temp);
            temp.setEid(cid);
        }

        // Act
        ArrayList<Employee> salesList = this.empyRepo.getByPosition(PositionType.SALESPERSON);

        // Assert
        assertThat(salesList.size(), is(2));
    }
    
}
