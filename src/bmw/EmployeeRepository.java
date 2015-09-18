package bmw;

public interface EmployeeRepository {
    String createId();

    void save(HourlyEmployee employee);

    HourlyEmployee getById(String employeeId);
}
