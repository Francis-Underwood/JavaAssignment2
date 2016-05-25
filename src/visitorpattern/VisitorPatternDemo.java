package visitorpattern;

public class VisitorPatternDemo {

	public static void main(String[] args) {
		
		System.out.println("***Visitor Pattern Demo***"); 
		System.out.println();
		
		Employees employees = new Employees();

		employees.attachElement(new Employee("Julia Ann", 57233.00, 4.5, 5.7, 5.6));
		employees.attachElement(new Employee("Riley Steele", 87990.00, 6.5, 2.9, 5.0));
		employees.attachElement(new Employee("Jenna Haze", 45360.00, 5.1, 3.7, 5.3));
		
		Visitor calculator = new AnnualBonusCalculator();
		
		employees.acceptVisitor(calculator);
	}

}
