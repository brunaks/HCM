package bmw;

public interface EmployeeRepository {
    String createId();

    void save(Employee employee);

    Employee getById(String employeeId);
}
