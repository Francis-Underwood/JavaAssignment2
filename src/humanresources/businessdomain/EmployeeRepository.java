package humanresources.businessdomain;

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
    private CustomerRepository custRepty = new CustomerRepository();

    //private static EmployeeRepository employeeRepository;	// TODO: have to remove singleton to mock it up?

    public EmployeeRepository() {}
    
    public Employee get(int eid) throws SQLException {
        String sql = "SELECT `Id`, `FirstName`, `LastName`, `Position` " + 
                    "FROM `employee` " + 
                    "WHERE `Id` = " + eid;
        Employee empy = null;
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                empy = empFactory.createEmployee(
                        PositionType.fromString(rs.getString("Position")),
                        rs.getInt("Id"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"));
            }
            
        }
         catch (SQLException ex) {
            System.out.println("Aletta: " + ex.toString());
            throw ex;
        }
        return empy;
    }
    
    public ArrayList<Employee> all() throws SQLException {
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
            System.out.println("Aletta: " + ex.toString());
            throw ex;
        }

        return employees;
    }

    public ArrayList<Employee> getByPosition(PositionType p) throws SQLException {
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
            System.out.println("Aletta: " + ex.toString());
            throw ex;
        }

        return employees;
    }
    
    public boolean update(Employee empy) throws SQLException {
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
            //System.out.println(String.format("Row updated %d", rowAffected));
            if (rowAffected > 0) {
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException ex) {
            System.out.println("Aletta: " + ex.getMessage());
            throw ex;
        }
    }
    
    public int add(Employee empy) throws SQLException {
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
            System.out.println("Aletta: " + ex.getMessage());
            throw ex;
        } 
        finally {
            try {
                if (rs != null)  {
                    rs.close();
                }
            } 
            catch (SQLException ex) {
                System.out.println("Aletta: " + ex.getMessage());
                throw ex;
            }
        }
        
        return candidateId;
    }
    
    public boolean delete(int eid) throws SQLException {
        String sql = "DELETE FROM `employee` WHERE `Id` = ?";
        int res = -1;
        try {
            res = this.custRepty.deleteByEmployeeId(eid);   // TODO: how to simulate that when mocking up
        }
        catch (SQLException ex) {
            throw ex;
        }
        if (res > -1) {
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
                System.out.println("Aletta" + ex.getMessage());
                throw ex;
                //return false;
            }
        }
        else {
            return false;
        }
    }
    
    public int deleteAll() throws SQLException {
        String sql = "DELETE FROM `employee`";
        
        int res = -1;
        
        try {
            res = this.custRepty.deleteAll();
        }
        catch (SQLException ex) {
            System.out.println("Aletta: " + ex.getMessage());
            throw ex;
            //return false;
        }
        
        if (res > -1) {
            try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                int rowsDeleted = pstmt.executeUpdate();
                return rowsDeleted;
            }
            catch (SQLException ex) {
                System.out.println("Aletta: " + ex.getMessage());
                throw ex;
                //return -1;
            }
        }
        else {
            return -1;
        }
    }
    
    // for unit testing
    public void setURL(String url) {
        this.url = url;
    }
	
}