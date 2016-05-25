package visitorpattern;

import java.util.ArrayList;

public class Employees {
	
	private ArrayList<Employee> _employeeList = new ArrayList<Employee>();

	public void attachElement(Employee employee) {
		this._employeeList.add(employee);
	}
	
	public void detachElement(Employee employee)
	{
		this._employeeList.remove(employee);
	}
	
	public void acceptVisitor(Visitor visitor)
	{
		for (Employee employee : this._employeeList) {
			employee.accept(visitor);
		}
	}
	
}
