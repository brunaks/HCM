package bmw;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private static int incrementalId;
    private Map<String, Employee> employeesSaved = new HashMap<>();

    public String createId() {
        return String.valueOf(++incrementalId);
    }

    public void save(Employee employee) {
        employeesSaved.put(employee.getId(), employee.copy());
    }

    public Employee getById(String employeeId) {
        return employeesSaved.get(employeeId).copy();
    }
}
