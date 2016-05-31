/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Vincent
 */
public class EmployeeTest {
    
    private Employee empy;
    
    @Before
    public void createEmployee() {
        this.empy = new Employee(141, "Alexis", "Texas", PositionType.SALESPERSON);
    }
    
    
    @Test
    public void testGetEid() {
        // Assert
        assertThat(this.empy.getEid(), is(141));
    }

    @Test
    public void testSetEid() {
        // Act
        this.empy.setEid(90);
        // Assert
        assertThat(this.empy.getEid(), is(90));
    }

    @Test
    public void testGetFname() {
        // Assert
        assertThat(this.empy.getFname(), is("Alexis"));
    }

    @Test
    public void testGetLname() {
        // Assert
        assertThat(this.empy.getLname(), is("Texas"));
    }

    @Test
    public void testSetFname() {
        // Act
        this.empy.setFname("Nadia");
        // Assert
        assertThat(this.empy.getFname(), is("Nadia"));
    }

    @Test
    public void testSetLname() {
        // Act
        this.empy.setLname("Sutra");
        // Assert
        assertThat(this.empy.getLname(), is("Sutra"));
    }

    @Test
    public void testSetPosition() {
        // Act
        this.empy.setPosition(PositionType.OTHERS);
        // Assert
        assertThat(this.empy.getPosition(), is(PositionType.OTHERS));
    }

    @Test
    public void testGetPosition() {
        // Assert
        assertThat(this.empy.getPosition(), is(PositionType.SALESPERSON));
    }
    
}
