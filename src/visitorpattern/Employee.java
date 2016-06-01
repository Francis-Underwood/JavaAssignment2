package visitorpattern;

public class Employee extends Element{
    private String _name;
    private double _annualWage;
    private double _firstGoalGrade;
    private double _secondGoalGrade;
    private double _individualPerformanceGrade;
    public Employee(String name, double annualWage, double firstGoalGrade, 
                    double secondGoalGrade, double individualPerformanceGrade) {
        this._name = name;
        this._annualWage = annualWage;
        this._firstGoalGrade = firstGoalGrade;
        this._secondGoalGrade = secondGoalGrade;
        this._individualPerformanceGrade = individualPerformanceGrade;
    }
    public String getName() {
        return this._name;
    }
    public double getAnnualWage() {
        return this._annualWage;
    }
    public double getFirstGoalGrade() {
        return this._firstGoalGrade;
    }
    public double getSecondGoalGrade() {
        return this._secondGoalGrade;
    }
    public double getIndividualPerformanceGrade() {
        return this._individualPerformanceGrade;
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
