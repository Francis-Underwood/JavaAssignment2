/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.systemevent;

import java.util.EventObject;

/**
 *
 * @author Vincent
 */
public class ViewCustomersEvent extends EventObject {
    private int employeeId;
    public ViewCustomersEvent(Object source, int eid) {
        super(source);
        this.employeeId = eid;
    }
    public int getEmployeeId() {
        return employeeId;
    }
}
