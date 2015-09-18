package bmw;

public class CreateEmployeeUseCase implements UseCase {
    private double hourlyRate;
    private String id;
    private EmployeeRepository repository;

    public CreateEmployeeUseCase(double hourlyRate, EmployeeRepository repository) {
        this.hourlyRate = hourlyRate;
        this.repository = repository;
    }

    public void execute() {
        id = repository.createId();
        HourlyEmployee employee = new HourlyEmployee(id);
        employee.setHourlyRate(hourlyRate);
        repository.save(employee);
    }

    public String getEmployeeId() {
        return id;
    }
}
