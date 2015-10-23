package bmw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TimeCards {
    private static final double EXTRA_PAY_RATE = 1.5;
    private static final int MAX_NORMAL_HOURS = 8;
    private List<TimeCard> list;

    public TimeCards() {
        list = new ArrayList<>();
    }

    private TimeCards(Collection<TimeCard> timeCards) {
        this();
        list.addAll(timeCards);
    }

    public void add(TimeCard timeCard) {
        list.add(timeCard);
    }

    public TimeCards copy() {
        return new TimeCards(list);
    }

    public TimeCards forPeriod(int month, int year) {
        List<TimeCard> timeCardsForPeriod = new ArrayList<>();
        for (TimeCard timeCard : list)
            if (timeCard.getMonth() == month && timeCard.getYear() == year)
                timeCardsForPeriod.add(timeCard);
        return new TimeCards(timeCardsForPeriod);
    }

    public double getTotalPaidHours() {
        int totalWorkedHours = getTotalWorkedHours();

        int totalExpectedHours = MAX_NORMAL_HOURS * list.size();

        if (totalWorkedHours > totalExpectedHours)
            return totalExpectedHours + ((totalWorkedHours - totalExpectedHours) * EXTRA_PAY_RATE);
        else
            return totalWorkedHours;
    }

    private int getTotalWorkedHours() {
        int totalWorkedHours = 0;
        for (TimeCard timeCard : list)
            totalWorkedHours += timeCard.getHoursWorked();
        return totalWorkedHours;
    }
}
