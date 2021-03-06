package bmw;

public class CreateTimeCard implements UseCase {
    private String employeeId;
    private int hoursWorked;
    private int day;
    private int month;
    private int year;
    private EmployeeRepository repository;

    public CreateTimeCard(String employeeId, int hoursWorked, int day, int month, int year, EmployeeRepository repository) {
        this.employeeId = employeeId;
        this.hoursWorked = hoursWorked;
        this.day = day;
        this.month = month;
        this.year = year;
        this.repository = repository;
    }

    public void execute() {
        HourlyEmployee employee = repository.getById(employeeId);
        TimeCard timeCard = new TimeCard(hoursWorked, day, month, year);
        employee.addTimeCard(timeCard);
        repository.save(employee);
    }
}
