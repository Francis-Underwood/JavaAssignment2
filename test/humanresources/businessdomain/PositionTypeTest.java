/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Vincent
 */
public class PositionTypeTest {

    @Test
    public void testGetDisplayName() {
        // Arrange
        PositionType mySalesPerson = PositionType.SALESPERSON;
        PositionType myOthers = PositionType.OTHERS;
        // Act
        String actualResultSalesPerson = mySalesPerson.getDisplayName();
        String actualResultOthers = myOthers.getDisplayName();
        // Assert
        assertThat(actualResultSalesPerson, equalTo("Salesperson"));
        assertThat(actualResultOthers, equalTo("Others"));
    }

    @Test
    public void testFromString() {
        // Arrange
        String salesPerson = "Salesperson";
        String others = "Others";
        // Act
        PositionType myActualSalesPerson = PositionType.fromString(salesPerson);
        PositionType myActualOthers = PositionType.fromString(others);
        // Assert
        assertThat(myActualSalesPerson, equalTo(PositionType.SALESPERSON));
        assertThat(myActualOthers, equalTo(PositionType.OTHERS));
    }
    
}
