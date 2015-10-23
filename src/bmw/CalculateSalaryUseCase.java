package bmw;

public class CalculateSalaryUseCase implements UseCase {
    private String employeeId;
    private int year;
    private int month;
    private EmployeeRepository repository;
    private double salary;

    public CalculateSalaryUseCase(String employeeId, int year, int month, EmployeeRepository repository) {
        this.employeeId = employeeId;
        this.year = year;
        this.month = month;
        this.repository = repository;
    }

    public void execute() {
        HourlyEmployee employee = repository.getById(employeeId);
        salary = employee.calculateSalary(year, month);
    }

    public double getSalary() {
        return salary;
    }
}
