/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

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
	public void switchToTestDatabaseAndCleanUp() {
		this.custRepo = CustomerRepository.getRepository();
		this.custRepo.setURL("jdbc:mysql://localhost:3306/vinc_humanresource_utest");
		
		this.custRepo.deleteAll();
                
                this.empyRepo = EmployeeRepository.getRepository();
                this.empyRepo.setURL("jdbc:mysql://localhost:3306/vinc_humanresource_utest");
                
	}
        
        
        
    @Test
    public void testInsertOneEmployeeRecordIntoDataBase() {
        // Arrange
		Employee etemp = new SalesPerson(0, "Nikki", "Benz", PositionType.SALESPERSON);
		Employee etempCopy = null;

		// Act
		int eid = this.empyRepo.add(etemp);
		etempCopy = this.empyRepo.get(eid);

		// Assert
		assertThat(etempCopy.getFname(), is("Nikki"));
    }

   /*     
    @Test
    public void testGet() {
    }

    @Test
    public void testAll() {
    }

    @Test
    public void testGetByPosition() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testDeleteAll() {
    }

    */
    
}
