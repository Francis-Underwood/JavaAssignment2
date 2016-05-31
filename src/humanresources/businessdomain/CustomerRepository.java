/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humanresources.businessdomain;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
/**
 *
 * @author Vincent
 */
public class CustomerRepository {
    private String url = "jdbc:mysql://localhost:3306/vinc_humanresource";
    private String user = "root";
    private String password = "";
    private CustomerFactory custFactory = new CustomerFactory();
    
    private static CustomerRepository customerRepository;	//singleton pattern
    
    private CustomerRepository() {}
    
    public ArrayList<Customer> all() {
        String sql = "SELECT `c`.`Id`, `EmployeeId`, `Name`, `PaymentMethod`, " + 
                    "IFNULL(CONCAT(`e`.`FirstName`, ' ', `e`.`LastName`), '') AS `AgentName` " +
                    "FROM `customer` `c` LEFT JOIN `employee` `e` " + 
                    "ON `c`.`EmployeeId` = `e`.`Id` " +
                    "ORDER BY `EmployeeId`, c.`Id`";
        ArrayList<Customer> customers = new ArrayList<Customer>();
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) 
        {
            while (rs.next()) {
                Customer c = custFactory.createCustomer(
                        rs.getInt("Id"),
                        rs.getInt("EmployeeId"),
                        rs.getString("Name"),
                        rs.getString("AgentName"),
                        PaymentMethodOption.fromString(rs.getString("PaymentMethod")));
                customers.add(c);
            }
        }
        catch (SQLException ex) {
            System.out.println("First: " + ex.toString());
        }

        return customers;
    }
    
    public Customer get(int cid) {
        String sql = "SELECT `c`.`Id`, `EmployeeId`, `Name`, `PaymentMethod`, " + 
                    "IFNULL(CONCAT(`e`.`FirstName`, ' ', `e`.`LastName`), '') AS `AgentName` " +
                    "FROM `customer` `c` LEFT JOIN `employee` `e` " + 
                    "ON `c`.`EmployeeId` = `e`.`Id` " +
                    "WHERE `c`.`Id` = " + cid + " " + 
                    "ORDER BY `EmployeeId`, c.`Id`";
        Customer c = null;
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            c = custFactory.createCustomer(
                        rs.getInt("Id"),
                        rs.getInt("EmployeeId"),
                        rs.getString("Name"),
                        rs.getString("AgentName"),
                        PaymentMethodOption.fromString(rs.getString("PaymentMethod")));
            
        } 
        catch (SQLException ex) {
            System.out.println("First: " + ex.toString());
        }
        return c;
    }
    
    public ArrayList<Customer> getByEmployeeId(int eid) {
        String sql = "SELECT `c`.`Id`, `EmployeeId`, `Name`, `PaymentMethod`, " + 
                    "CONCAT(`e`.`FirstName`, ' ', `e`.`LastName`) AS `AgentName` " + 
                    "FROM `customer` `c` INNER JOIN `employee` `e` "  + 
                    "ON `c`.`EmployeeId` = `e`.`Id` " + 
                    "WHERE `EmployeeId` = " + eid + " " + 
                    "ORDER BY `EmployeeId`, c.`Id`";
        ArrayList<Customer> customers = new ArrayList<Customer>();
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer c = custFactory.createCustomer(
                        rs.getInt("Id"),
                        rs.getInt("EmployeeId"),
                        rs.getString("Name"),
                        rs.getString("AgentName"),
                        PaymentMethodOption.fromString(rs.getString("PaymentMethod")));
                customers.add(c);
            }
        } 
        catch (SQLException ex) {
            System.out.println("First: " + ex.toString());
        }

        return customers;
    }
    
    public boolean update(Customer cust) {
        String sql = "UPDATE `customer` "
                + "SET `Name` = ?, "
                + "`PaymentMethod` = ? "
                + "WHERE `Id` = ?";
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setString(1, cust.getCname());
            pstmt.setString(2, cust.getPaymentMethod().getDisplayName());
            pstmt.setInt(3, cust.getCid());
            
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
    
    public int add(Customer cust) {
        String sql = "INSERT INTO `customer`(`EmployeeId`, `Name`, `PaymentMethod`) VALUES(?,?,?)";
        
        ResultSet rs = null;
        int candidateId = 0;
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            pstmt.setInt(1, cust.getEid());
            pstmt.setString(2, cust.getCname());
            pstmt.setString(3, cust.getPaymentMethod().getDisplayName());
            
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
        } 
        finally {
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
    
    public boolean delete(int cid) {
        String sql = "DELETE FROM `customer` WHERE `Id` = ?";
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, cid);
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
    
    public int deleteByEmployeeId(int eid) {
        String sql = "DELETE FROM `customer` WHERE `EmployeeId` = ?";
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
            pstmt.setInt(1, eid);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
        
    }
    
    public int deleteAll() {
        String sql = "DELETE FROM `customer`";
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {   
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted;
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
    }
    
    public static CustomerRepository getRepository() {
        if (null == customerRepository) {
            customerRepository = new CustomerRepository();
        } 
        else {
        }
        return customerRepository;
    }
    
    public void setURL(String url) {
        this.url = url;
    }
    
}
