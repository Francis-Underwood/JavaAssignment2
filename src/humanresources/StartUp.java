/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources;
import java.util.*;
/**
 *
 * @author Vincent
 */
public class StartUp {
    
    static EmployeeFactory empF = new EmployeeFactory();
    static EmployeeRepository rep; 
    
    static CustomerFactory cusF = new CustomerFactory();
    static CustomerRepository crep; 

    public static void main(String[] args) {
        
        crep = CustomerRepository.getRepository();
        
        
        // delete
        /*
        boolean res = crep.delete(17);
        System.out.println("Shay: " + res);
        */
        
        // insert
        /*
        Customer cd = cusF.createCustomer(0, 4, "Glider", "", PaymentMethodOption.CASH);
        crep.add(cd);
        */
        
        // update
        /*
        ArrayList<Customer> clist = crep.getByEmployeeId(4);
        Customer cc = clist.get(1);
        cc.setCname("Magental");
        crep.update(cc);
        */
        
        
        
        // select
        /*
        //ArrayList<Customer> clist = crep.all();
        ArrayList<Customer> clist = crep.getByEmployeeId(4);
        System.out.println("Shay: " + clist.size());
        
        for (Customer c : clist) {
            System.out.println("Customer name: " + c.getCname() + "\t\t\t" + "Agent name: " + c.getAgentName());
            //System.out.println();
        }
        */
        
        
        
        rep = EmployeeRepository.getRepository();
        
        // delete
        /*
        boolean res = rep.delete(3);
        System.out.println("Shay: " + res);
        */
        
        
        // insert
        /*
        Employee yy = empF.createEmployee(PositionType.OTHERS, 0, "Billy", "Batson");
        int eid = rep.add(yy);
        if (eid>0) {
            yy.setEid(eid);
        }
        ArrayList<Employee> elist = rep.getByPosition(PositionType.OTHERS);
        System.out.println("Shay: " + elist.size());
        */
        
        // update
        /*
        //ArrayList<Employee> elist = rep.getByPosition(PositionType.SALESPERSON);     
        //System.out.println("Shay: " + elist.size());
        //Employee xx = elist.get(1);
        //System.out.println("Shay: " + xx.getFname());
        //xx.setFname("Halle");
        //rep.update(xx);
        */
        
    }

}
