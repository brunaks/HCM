package bmw;

public class HourlyEmployee {
    private String name;
    private String id;
    private double hourlyRate;
    TimeCards timeCards;

    public HourlyEmployee(String employeeId) {
        this(employeeId, "", 0.0, new TimeCards());
    }

    private HourlyEmployee(String id, String name, double hourlyRate, TimeCards timeCards) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.timeCards = timeCards;
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

    public void addTimeCard(TimeCard timeCard) {
        this.timeCards.add(timeCard);
    }

    public HourlyEmployee copy() {
        return new HourlyEmployee(id, name, hourlyRate, timeCards.copy());
    }

    double calculateSalary(int month, int year) {
        return timeCards.forPeriod(month, year).getTotalPaidHours() * hourlyRate;
    }
}
