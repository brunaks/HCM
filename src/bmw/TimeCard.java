package bmw;

public class TimeCard {


    private final int hoursWorked;
    private final int day;
    private int month;
    private int year;

    public TimeCard(int hoursWorked, int day, int month, int year) {
        this.hoursWorked = hoursWorked;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }
}
