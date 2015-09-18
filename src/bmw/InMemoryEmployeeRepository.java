package bmw;

import java.util.HashMap;
import java.util.Map;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private static int incrementalId;
    private Map<String, HourlyEmployee> employeesSaved = new HashMap<>();

    public String createId() {
        return String.valueOf(++incrementalId);
    }

    public void save(HourlyEmployee employee) {
        employeesSaved.put(employee.getId(), employee.copy());
    }

    public HourlyEmployee getById(String employeeId) {
        return employeesSaved.get(employeeId).copy();
    }
}
