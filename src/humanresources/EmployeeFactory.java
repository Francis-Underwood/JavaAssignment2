package humanresources;

// factory pattern
public final class EmployeeFactory {
	public Employee createEmployee(Position pos, int eid, String fname, String lname) {
		switch (pos) {
			case SALESPERSON:
				return new SalesPerson(eid, fname, lname);
			case OTHERS:
				//break;
			default:
				return new OtherStaff(eid, fname, lname);
		}
	}
}
