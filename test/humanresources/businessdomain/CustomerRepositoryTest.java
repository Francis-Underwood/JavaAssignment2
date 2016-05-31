/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package humanresources.businessdomain;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
*
* @author Vincent
*/
public class CustomerRepositoryTest {

	private CustomerRepository custRepo;
	private EmployeeRepository empyRepo;

	@Before
	public void switchToTestDatabaseAndCleanUp() {
		this.custRepo = CustomerRepository.getRepository();
		this.custRepo.setURL("jdbc:mysql://localhost:3306/vinc_humanresource_utest");
		this.custRepo.deleteAll();
		this.empyRepo = EmployeeRepository.getRepository();
		this.empyRepo.setURL("jdbc:mysql://localhost:3306/vinc_humanresource_utest");
		this.empyRepo.deleteAll();
	}

	//@Ignore
        @Test
	public void testInsertOneCustomerRecordIntoDataBase() {
		// Arrange
		Customer temp = new CustomerPayCash(0, 0, "Elegent Angel", null, PaymentMethodOption.CASH);
		Customer tempCopy = null;

		// Act
		int cid = this.custRepo.add(temp);
		tempCopy = this.custRepo.get(cid);

		// Assert
		assertThat(tempCopy.getCname(), is("Elegent Angel"));
	}

	//@Ignore
        @Test
	public void testDeleteAllCustomerRecordsFromDatabase() {
		// Arrange
		List<Customer> cList = new ArrayList<Customer>() {
			{
				add(new CustomerPayCash(0, 0, "Brazzers", null, PaymentMethodOption.CASH));
				add(new CustomerPayWithCreditCard(0, 0, "Diabolic", null, PaymentMethodOption.CREDITCARD));
				add(new CustomerPayWithCreditCard(0, 0, "Diva Futura", null, PaymentMethodOption.CREDITCARD));
			}
		};
		for (Customer temp: cList) {
			int cid = this.custRepo.add(temp);
			temp.setCid(cid);
		}

		// Act
		int res = this.custRepo.deleteAll();

		// Assert
		assertTrue(res > 0);
	}

	//@Ignore
        @Test
	public void testRetrieveAllCustomerRecordsFromDatabase() {
		// Arrange
		List<Customer> cList = new ArrayList<Customer>() {
			{
				add(new CustomerPayCash(0, 0, "Brazzers", null, PaymentMethodOption.CASH));
				add(new CustomerPayWithCreditCard(0, 0, "Diabolic", null, PaymentMethodOption.CREDITCARD));
				add(new CustomerPayWithCreditCard(0, 0, "Diva Futura", null, PaymentMethodOption.CREDITCARD));
			}
		};
		for (Customer temp: cList) {
			int cid = this.custRepo.add(temp);
			temp.setCid(cid);
		}

		// Act
		ArrayList<Customer> cListCopy = this.custRepo.all();

		// Assert
		assertTrue(cListCopy.size() > 0);
	}

	//@Ignore
        @Test
	public void testDeleteOneCustomerByItsId() {
		// Arrange
		List<Customer> cList = new ArrayList<Customer>() {
			{
				add(new CustomerPayCash(0, 0, "Brazzers", null, PaymentMethodOption.CASH));
				add(new CustomerPayWithCreditCard(0, 0, "Diabolic", null, PaymentMethodOption.CREDITCARD));
				add(new CustomerPayWithCreditCard(0, 0, "Diva Futura", null, PaymentMethodOption.CREDITCARD));
			}
		};
		for (Customer temp: cList) {
			int cid = this.custRepo.add(temp);
			temp.setCid(cid);
		}

		// Act
		boolean res = this.custRepo.delete((cList.get(0)).getCid());

		// Assert
		assertTrue(res);
	}

	//@Ignore
        @Test
	public void testUpdateOneCustomerByItsId() {
		// Arrange
		List<Customer> cList = new ArrayList<Customer>() {
			{
				add(new CustomerPayCash(0, 0, "Brazzers", null, PaymentMethodOption.CASH));
				add(new CustomerPayWithCreditCard(0, 0, "Diabolic", null, PaymentMethodOption.CREDITCARD));
				add(new CustomerPayWithCreditCard(0, 0, "Diva Futura", null, PaymentMethodOption.CREDITCARD));
			}
		};
		for (Customer temp: cList) {
			int cid = this.custRepo.add(temp);
			temp.setCid(cid);
		}

		// Act
		(cList.get(0)).setCname("Darling Film Limited");
		this.custRepo.update(cList.get(0));
		Customer cust = this.custRepo.get((cList.get(0)).getCid());

		// Assert
		assertThat(cust.getCname(), is("Darling Film Limited"));
	}

	//@Ignore
        @Test
	public void testRetrieveCustomersByTheirEmployeeId() {
		// Arrange
		Employee etemp = new SalesPerson(0, "Nikki", "Benz", PositionType.SALESPERSON);
		int insertedId = this.empyRepo.add(etemp);
		List<Customer> cList = new ArrayList<Customer>() {
			{
				add(new CustomerPayCash(0, insertedId, "Brazzers", null, PaymentMethodOption.CASH));
				add(new CustomerPayWithCreditCard(0, insertedId, "Diabolic", null, PaymentMethodOption.CREDITCARD));
				add(new CustomerPayWithCreditCard(0, insertedId, "Diva Futura", null, PaymentMethodOption.CREDITCARD));
			}
		};

		for (Customer ctemp: cList) {
			int cid = this.custRepo.add(ctemp);
			ctemp.setCid(cid);
		}
                
		// Act
		ArrayList<Customer> cListCopy = this.custRepo.getByEmployeeId(insertedId);

		// Assert
		assertThat((cListCopy.get(0)).getAgentName(), is("Nikki Benz"));
	}

        //@Ignore
	@Test
	public void testDeleteCustomersByTheirEmployeeId() {
		// Arrange
		Employee etemp = new SalesPerson(0, "Nikki", "Benz", PositionType.SALESPERSON);
		int insertedId = this.empyRepo.add(etemp);
		List<Customer> cList = new ArrayList<Customer>() {
			{
				add(new CustomerPayCash(0, insertedId, "Brazzers", null, PaymentMethodOption.CASH));
				add(new CustomerPayWithCreditCard(0, insertedId, "Diabolic", null, PaymentMethodOption.CREDITCARD));
				add(new CustomerPayWithCreditCard(0, insertedId, "Diva Futura", null, PaymentMethodOption.CREDITCARD));
			}
		};

		for (Customer ctemp: cList) {
			int cid = this.custRepo.add(ctemp);
			ctemp.setCid(cid);
		}
                
		// Act
		int res = this.custRepo.deleteByEmployeeId(insertedId);

		// Assert
		assertThat(res, is(3));
	}

}