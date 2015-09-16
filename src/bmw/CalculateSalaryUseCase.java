package bmw;

import java.util.List;
import java.util.function.BinaryOperator;

public class CalculateSalaryUseCase implements UseCase {

    private final String employeeId;
    private int year;
    private final int month;
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
        List<Integer> hoursWorked = repository.getHoursWorked(employeeId, year, month);
        int totalWorkedHours = 0;
        for(int item : hoursWorked){
            totalWorkedHours += item;
        }
        salary = totalWorkedHours * hourlyRate;
    }

    public double getSalary() {
        return salary;
    }
}
