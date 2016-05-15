/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.systemevent;

import java.util.EventListener;

/**
 *
 * @author Vincent
 */
public interface IViewCustomersListener extends EventListener {
    public void ViewCustomers(ViewCustomersEvent evt);
}
