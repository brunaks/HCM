package bmw;

import java.util.ArrayList;

public class Employee {

    private String id;
    private double hourlyRate;
    ArrayList<Integer> workedHours = new ArrayList<>();


    public Employee(String employeeId) {
        this.id = employeeId;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getId() {
        return id;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void addWorkedHours(int hoursWorked) {
        workedHours.add(hoursWorked);
    }
}
