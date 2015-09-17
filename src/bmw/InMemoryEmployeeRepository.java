package bmw;

import java.util.ArrayList;

public class InMemoryEmployeeRepository implements EmployeeRepository {
    private static int incrementalId;
    private ArrayList<Employee> employeesSaved = new ArrayList<>();

    public String createId() {
        return String.valueOf(++incrementalId);
    }

    public void save(Employee employee) {
        employeesSaved.add(employee);
    }

    public Employee getById(String employeeId) {
        for (Employee employee : employeesSaved)
            if (employee.getId().equalsIgnoreCase(employeeId))
                return employee;
        return new Employee(employeeId);
    }
}
