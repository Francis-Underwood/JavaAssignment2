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

    public static void main(String[] args) {
        rep = EmployeeRepository.getRepository();
        
        
        // insert
        Employee yy = empF.createEmployee(PositionType.OTHERS, 0, "Billy", "Batson");
        int eid = rep.add(yy);
        if (eid>0) {
            yy.setEid(eid);
        }
        
        ArrayList<Employee> elist = rep.getByPosition(PositionType.OTHERS);
        System.out.println("Shay: " + elist.size());
        
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
