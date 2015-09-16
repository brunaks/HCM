package bmw;

public class CalculateSalaryUseCase implements UseCase {
    private String employeeId;
    private int year;
    private int month;
    private Repository repository;
    private double salary;

    public CalculateSalaryUseCase(String employeeId, int year, int month, Repository repository) {
        this.employeeId = employeeId;
        this.year = year;
        this.month = month;
        this.repository = repository;
    }

    public void execute() {
        double hourlyRate = repository.getHourlyRate(employeeId);
        Iterable<Integer> hoursWorked = repository.getHoursWorked(employeeId, year, month);
        int totalWorkedHours = 0;
        for (int item : hoursWorked)
            totalWorkedHours += item;
        salary = totalWorkedHours * hourlyRate;
    }

    public double getSalary() {
        return salary;
    }
}
