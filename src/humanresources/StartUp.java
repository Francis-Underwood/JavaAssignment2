/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Vincent
 */
public class StartUp {

    public static void main(String[] args) {
        

        String url = "jdbc:mysql://localhost:3306/vinc_humanresource";
        String user = "root";
        String password = "";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            stmt  = conn.createStatement();
            String sql = "SELECT `Id`, `FirstName`, `LastName`, `Position` FROM `employee`";
        
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
   System.out.println(
           rs.getInt("Id") + "\t" + 
           rs.getString("FirstName") + "\t" + 
                      rs.getString("LastName")  + "\t" +
                      rs.getString("Position")
   );
                    
}
        }
        catch (SQLException ex) {
		System.out.println("First: "+ex.toString());
        }
         finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            		System.out.println(ex.toString());
            }
        }
        
/*
        for (Position pos : Position.values()) {
            System.out.print(pos.getDisplayName() + " ");
        }
*/
    }

}
