package humanresources;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class EmployeeRepository {

    private String url = "jdbc:mysql://localhost:3306/vinc_humanresource";
    private String user = "root";
    private String password = "";
    private EmployeeFactory empFactory = new EmployeeFactory();
    private CustomerRepository custRepty;

    private static EmployeeRepository employeeRepository;	//singleton pattern

    private EmployeeRepository() {
        this.custRepty = CustomerRepository.getRepository();
    }

    public ArrayList<Employee> all() {
        String sql = "SELECT `Id`, `FirstName`, `LastName`, `Position` FROM `employee`";
        ArrayList<Employee> employees = new ArrayList<Employee>();
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) {
                Employee e = empFactory.createEmployee(
                        PositionType.fromString(rs.getString("Position")),
                        rs.getInt("Id"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                employees.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("First: " + ex.toString());
        }

        return employees;
    }

    public ArrayList<Employee> getByPosition(PositionType p) {
        String sql = "SELECT `Id`, `FirstName`, `LastName`, `Position` " + 
                    "FROM `employee` " + 
                    "WHERE `Position` = '" + p.getDisplayName() + "'";
        ArrayList<Employee> employees = new ArrayList<Employee>();
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee e = empFactory.createEmployee(
                        PositionType.fromString(rs.getString("Position")),
                        rs.getInt("Id"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
                employees.add(e);
            }
        } catch (SQLException ex) {
            System.out.println("First: " + ex.toString());
        }

        return employees;
    }
    
    public boolean update(Employee empy) {
        String sql = "UPDATE `employee` "
                + "SET `FirstName` = ?, "
                 + "`LastName` = ? "
                + "WHERE `Id` = ?";
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, empy.getFname());
            pstmt.setString(2, empy.getLname());
            pstmt.setInt(3, empy.getEid());
            
            int rowAffected = pstmt.executeUpdate();
            System.out.println(String.format("Row updated %d", rowAffected));
            if (rowAffected > 0) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public int add(Employee empy) {
        String sql = "INSERT INTO `employee`(`FirstName`, `LastName`, `Position`) "
            + "VALUES(?,?,?)";
        
        ResultSet rs = null;
        int candidateId = 0;
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setString(1, empy.getFname());
            pstmt.setString(2, empy.getLname());
            pstmt.setString(3, empy.getPosition().getDisplayName());
            
            int rowAffected = pstmt.executeUpdate();
            
            if (rowAffected == 1) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    candidateId = rs.getInt(1);
                }
            }
                
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (rs != null)  {
                    rs.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return candidateId;
    }
    
    public boolean delete(int eid) {
        String sql = "DELETE FROM `employee` WHERE `Id` = ?";
        
        boolean res = this.custRepty.deleteByEmployeeId(eid);
        
        if (res) {
            try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setInt(1, eid);
                int rowsDeleted = pstmt.executeUpdate();
                if (rowsDeleted > 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
                return false;
            }
        }
        else {
            return false;
        }
    }
	
    public static EmployeeRepository getRepository() {
        if (null == employeeRepository) {
            employeeRepository = new EmployeeRepository();
        } 
        else {
        }
        return employeeRepository;
    }
	
}