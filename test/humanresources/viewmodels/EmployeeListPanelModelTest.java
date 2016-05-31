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
public class EmployeeListPanelModelTest {
    
    private EmployeeListPanelModel empyModel;
    
    @Before
    public void initModel() {
        ArrayList<Employee> eList = new ArrayList<Employee>() {
                {
                        add(new OtherStaff(0, "Flower", "Tucci", PositionType.OTHERS));
                        add(new SalesPerson(0, "Lizz", "Tayler", PositionType.SALESPERSON));
                        add(new SalesPerson(0, "Bibi", "Jones", PositionType.SALESPERSON));
                }
        };
        this.empyModel = new EmployeeListPanelModel(eList);
    }

    @Test
    public void testGetColumnCount() {
        // Assert
	assertThat(this.empyModel.getColumnCount(), is(4));
    }

    @Test
    public void testGetRowCount() {
        // Assert
	assertThat(this.empyModel.getRowCount(), is(3));
    }

    @Test
    public void testGetColumnName() {
        // Assert
        assertThat(this.empyModel.getColumnName(0), is("Employee Id"));
        assertThat(this.empyModel.getColumnName(1), is("Employee First Name"));
        assertThat(this.empyModel.getColumnName(2), is("Employee Last Name"));
        assertThat(this.empyModel.getColumnName(3), is("Category"));
    }

    @Test
    public void testGetValueAt() {
        // Assert
        assertThat(this.empyModel.getValueAt(0, 2), is("Tucci"));
        assertThat(this.empyModel.getValueAt(1, 1), is("Lizz"));
        assertThat(this.empyModel.getValueAt(2, 3), is("Salesperson"));
        assertThat(this.empyModel.getValueAt(99, 3), is(""));
    }

}
