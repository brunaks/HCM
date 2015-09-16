package bmw;

public class CreateEmployeeUseCase implements UseCase {
    private double hourlyRate;
    private String id;
    private Repository repository;

    public CreateEmployeeUseCase(double hourlyRate, Repository repository) {
        this.hourlyRate = hourlyRate;
        this.repository = repository;
    }

    public void execute() {
        id = repository.createId();
        repository.saveEmployee(id, hourlyRate);
    }

    public String getEmployeeId() {
        return id;
    }
}
