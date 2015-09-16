package bmw;

public interface Repository {
    String createId();

    void saveEmployee(String employeeId, double hourlyRate);

    void addTimeCardToEmployee(String employeeId, int year, int month, int day, int hoursWorked);

    double getHourlyRate(String employeeId);

    Iterable<Integer> getHoursWorked(String employeeId, int year, int month);
}
