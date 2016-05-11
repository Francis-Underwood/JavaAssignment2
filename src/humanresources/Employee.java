package humanresources;

public class Employee {
	
	private int eid;
	private String fname;
	private String lname;
	
	public Employee (int eid, String fname, String lname) {
		this.eid = eid;
		this.fname = fname;
		this.lname = lname;
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
}
