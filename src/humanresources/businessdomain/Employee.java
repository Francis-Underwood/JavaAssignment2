package humanresources.businessdomain;

public class Employee {

    private int eid;
    private String fname;
    private String lname;
    private PositionType position;

    public Employee(int eid, String fname, String lname, PositionType pos) {
        this.eid = eid;
        this.fname = fname;
        this.lname = lname;
        this.position = pos;
    }

    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    
    public void setPosition(PositionType position) {
        this.position = position;
    }

    public PositionType getPosition() {
        return this.position;
    }

}
