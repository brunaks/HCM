package bmw;

import java.util.ArrayList;
import java.util.List;

public class HourlyEmployee {
    public static final double EXTRA_PAY_RATE = 1.5;
    public static final int MAX_NORMAL_HOURS = 8;
    private String name;
    private String id;
    private double hourlyRate;
    List<TimeCard> timeCards;

    public HourlyEmployee(String employeeId) {
        this(employeeId, "", 0.0, new ArrayList<>());
    }

    private HourlyEmployee(String id, String name, double hourlyRate, List<TimeCard> timeCards) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.timeCards = new ArrayList<>();
        this.timeCards.addAll(timeCards);
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
        return new HourlyEmployee(id, name, hourlyRate, timeCards);
    }

    double calculateSalary(int year, int month) {
        return getTotalPaidHours(getTimeCardsForPeriod(month, year)) * hourlyRate;
    }

    private List<TimeCard> getTimeCardsForPeriod(int month, int year) {
        List<TimeCard> timeCardsForPeriod = new ArrayList<>();
        for (TimeCard timeCard : timeCards)
            if (timeCard.getMonth() == month && timeCard.getYear() == year)
                timeCardsForPeriod.add(timeCard);
        return timeCardsForPeriod;
    }

    private double getTotalPaidHours(List<TimeCard> timeCardsForPeriod) {
        int totalWorkedHours = getTotalWorkedHours(timeCardsForPeriod);
        int totalExpectedHours = MAX_NORMAL_HOURS * timeCardsForPeriod.size();

        if (totalWorkedHours > totalExpectedHours)
            return totalExpectedHours + ((totalWorkedHours - totalExpectedHours) * EXTRA_PAY_RATE);
        else
            return totalWorkedHours;
    }

    private int getTotalWorkedHours(List<TimeCard> timeCardsForPeriod) {
        int totalWorkedHours = 0;
        for (TimeCard timeCard : timeCardsForPeriod)
            totalWorkedHours += timeCard.getHoursWorked();
        return totalWorkedHours;
    }
}
