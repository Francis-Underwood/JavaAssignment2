/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources;
import java.lang.*;
/**
 *
 * @author Vincent
 */
public class StartUp {
    
    public static void main(String[] args) {
        for (Position pos : Position.values()) {
            System.out.print(pos.getDisplayName() + " "); 
         }
    }
    
}
