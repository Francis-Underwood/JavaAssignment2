/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

import java.util. * ;
import org.junit. * ;
import static org.junit.Assert. * ;
import static org.hamcrest.CoreMatchers. * ;

/**
 *
 * @author Vincent
 */
public class CustomerRepositoryTest {

	private CustomerRepository custRepo;

	@Before
	public void switchToTestDatabase() {
		this.custRepo = CustomerRepository.getRepository();
		this.custRepo.setURL("jdbc:mysql://localhost:3306/vinc_humanresource_utest");
	}

	/*
    @Before
    public void fillDummyCustomers() {
        this.cust = new Customer(141, 13, "Digital Playground", 
                "Alexis Texas", PaymentMethodOption.CASH);
    }
*/

	@Test
	public void testInsertOneCustomerRecordIntoDataBase() {
		// Arrange
		Customer temp = new CustomerPayCash(0, 0, "Elegent Angel", null, PaymentMethodOption.CASH);
		Customer tempCopy = null;

		// Act
		int cid = this.custRepo.add(temp);
		tempCopy = this.custRepo.getById(cid);

		// Assert
		assertThat(tempCopy.getCname(), is("Elegent Angel"));
	}

	/**/
	@Test
	public void testDeleteAllCustomerRecordsFromDatabase() {
		// Arrange
		List < Customer > cList = new ArrayList < Customer > () {
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

	
    @Test
    public void testRetrieveAllCustomerRecordsFromDatabase() {
        // Arrange
		List < Customer > cList = new ArrayList < Customer > () {
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
    

    @Test
    public void testRetrieveCustomersByItsEmployeeId() {
        
    }
    
/*
    @Test
    public void testUpdate() {
    }


    @Test
    public void testDelete() {
    }

    @Test
    public void testDeleteByEmployeeId() {
    }
    */

}