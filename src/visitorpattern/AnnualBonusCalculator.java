package visitorpattern;

public class AnnualBonusCalculator extends Visitor {
    private final double FUND_BASE_RATE = 0.015;
    private final int NUMBER_OF_FACTOR = 3;
    @Override
    public void visit(Element element) {
        Employee emp = (Employee)element;
        double bonus = emp.getAnnualWage() * FUND_BASE_RATE * 
                        (emp.getFirstGoalGrade() + emp.getSecondGoalGrade() + 
                        emp.getIndividualPerformanceGrade()) / NUMBER_OF_FACTOR;
        System.out.println("Employee " + emp.getName() + " should get bonus: " + bonus);
    }
}
