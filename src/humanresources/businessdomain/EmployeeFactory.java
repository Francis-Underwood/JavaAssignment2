package humanresources.businessdomain;

// factory pattern
public final class EmployeeFactory {
    public Employee createEmployee(PositionType pos, int eid, String fname, String lname) {
        switch (pos) {
            case SALESPERSON:
                return new SalesPerson(eid, fname, lname, pos);
            case OTHERS:
            //break;
            default:
                return new OtherStaff(eid, fname, lname, pos);
        }
    }
}
