package bmw;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public static final double EXTRA_PAY_RATE = 1.5;
    public static final int MAX_NORMAL_HOURS = 8;
    private String name;
    private String id;
    private double hourlyRate;
    List<Integer> workedHours;

    public Employee(String employeeId) {
        this(employeeId, "", 0.0, new ArrayList<>());
    }

    private Employee(String id, String name, double hourlyRate, List<Integer> workedHours) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.workedHours = new ArrayList<>();
        this.workedHours.addAll(workedHours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void addWorkedHours(int hoursWorked) {
        workedHours.add(hoursWorked);
    }

    public Employee copy() {
        return new Employee(id, name, hourlyRate, workedHours);
    }

    double calculateSalary() {
        double salary = 0.0;
        for (int hoursWorked : workedHours)
            salary += calculateDaySalary(hoursWorked);
        return salary;
    }

    private double calculateDaySalary(int hoursWorked) {
        return hourlyRate * toPaidHours(hoursWorked);
    }

    private double toPaidHours(int hoursWorked) {
        if (hasExtraHours(hoursWorked))
            return applyExtraPayRate(hoursWorked);
        else
            return hoursWorked;
    }

    private boolean hasExtraHours(int hoursWorked) {
        return hoursWorked > MAX_NORMAL_HOURS;
    }

    private double applyExtraPayRate(int hoursWorked) {
        int extraHours = hoursWorked - MAX_NORMAL_HOURS;
        return MAX_NORMAL_HOURS + (extraHours * EXTRA_PAY_RATE);
    }
}
