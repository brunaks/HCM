package bmw;

public class CreateTimeCard implements UseCase {
    private final String employeeId;
    private final int hoursWorked;
    private final int day;
    private final int month;
    private final int year;
    private Repository repository;

    public CreateTimeCard(String employeeId, int hoursWorked, int day, int month, int year, Repository repository) {
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
        this.day = day;
        this.month = month;
        this.year = year;
        this.repository = repository;
    }

    public void execute() {
        repository.addTimeCardToEmployee(employeeId, year, month, day, hoursWorked);
    }
}
