package bmw;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String id;
    private double hourlyRate;
    List<Integer> workedHours = new ArrayList<>();

    public Employee(String employeeId) {
        this.id = employeeId;
    }

    public String getId() {
        return id;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void addWorkedHours(int hoursWorked) {
        workedHours.add(hoursWorked);
    }

    public Iterable<Integer> getWorkedHours() {
        return workedHours;
    }
}
