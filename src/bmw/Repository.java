package bmw;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static int incrementalId;
    private ArrayList<Employee> employeesSaved = new ArrayList<Employee>();

    public String createId() {
        return String.valueOf(++incrementalId);
    }

    public void saveEmployee(String employeeId, double hourlyRate) {
        Employee employee = new Employee(employeeId);
        employee.setHourlyRate(hourlyRate);
        employeesSaved.add(employee);
    }

    public void addTimeCardToEmployee(String employeeId, int year, int month, int day, int hoursWorked) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {employee.addWorkedHours(hoursWorked); }
    }

    private Employee getEmployeeById(String employeeId) {
        for (Employee employee: employeesSaved) {
            if (employee.getId().equalsIgnoreCase(employeeId)) {
                return employee;
            }
        }
        return null;
    }

    public double getHourlyRate(String employeeId) {
        return getEmployeeById(employeeId).getHourlyRate();
    }

    public List<Integer> getHoursWorked(String employeeId, int year, int month) {
        return getEmployeeById(employeeId).workedHours;
    }
}
