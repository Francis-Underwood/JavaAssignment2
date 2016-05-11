package humanresources;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRepository {

    private String url = "jdbc:mysql://localhost:3306/vinc_humanresource";
    private String user = "root";
    private String password = "";
    private EmployeeFactory empFactory = new EmployeeFactory();

    private static EmployeeRepository employeeRepository;	//singleton pattern

    private EmployeeRepository() {}

    public ArrayList<Employee> all() {
        String sql = "SELECT `Id`, `FirstName`, `LastName`, `Position` FROM `employee`";
        ArrayList<Employee> employees = new ArrayList<Employee>();
        
        try (Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee e = empFactory.createEmployee(
                        Position.fromString(rs.getString("Position")),
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

        
/*	
	public Employee select(String key, EntityService<String, Employee> db) {
		return db.get(key);
	}
	
	public void add(String key, Employee val, EntityService<String, Employee> db) {
		db.add(key, val);
	}
	
	public void update(String key, Employee val, EntityService<String, Employee> db) {
		db.update(key, val);
	}
	
	public void delete(String key, EntityService<String, Employee> db) {
		db.delete(key);
	}
	
	public boolean containsKey(String key, EntityService<String, Employee> db) {
		return db.containsKey(key);
	}
*/	
	public static EmployeeRepository getRepository() {
		if (null==employeeRepository) {
			employeeRepository = new EmployeeRepository();
		}
		else {}
		return employeeRepository;
	}
	
}