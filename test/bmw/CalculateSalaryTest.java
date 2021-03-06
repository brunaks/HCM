package bmw;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CalculateSalaryTest {
    CreateEmployeeUseCase createEmployee;
    CalculateSalaryUseCase calculateSalary;
    CreateTimeCard createTimeCard;
    EmployeeRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryEmployeeRepository();
    }

    @Test
    public void employeeHasNoTimeCards_SalaryEqualsZero() {
        String id = givenEmployee(22.73);
        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe(0.0);
    }

    @Test
    public void employeeHasThreeTimeCards_salaryMustBeEquivalent() {
        String id = givenEmployee(22.73);
        givenTimeCard(id, 8, 1, 1, 2015);
        givenTimeCard(id, 8, 2, 1, 2015);
        givenTimeCard(id, 8, 3, 1, 2015);
        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe(24 * 22.73);
    }

    @Test
    public void employeeWithExtraHour_theExtraHourIsPaidTimeAndAHalf() {
        String id = givenEmployee(22.73);
        givenTimeCard(id, 9, 1, 1, 2015);
        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe((8 * 22.73) + (1.5 * 22.73));
    }

    @Test
    public void employeeWithExtraHourAndDayWithLessThanEightHours_theHoursAreCompensated() {
        String id = givenEmployee(22.73);
        givenTimeCard(id, 9, 1, 1, 2015);
        givenTimeCard(id, 7, 2, 1, 2015);
        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe(16 * 22.73);
    }

    @Test
    public void employeeHasTimeCardsInDifferentMonths_salaryMustConsiderOnlyTimeCardsWithinEachMonth() {
        String id = givenEmployee(22.73);

        givenTimeCard(id, 8, 1, 1, 2015);
        givenTimeCard(id, 8, 1, 2, 2015);

        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe(8 * 22.73);

        whenCalculatingSalaryOf(id, 2, 2015);
        thenTheSalaryShouldBe(8 * 22.73);
    }

    @Test
    public void employeeWithExtraHourAndDayWithLessThanEightHours_inAnotherMonth_thenHoursAreNotCompensated() {
        String id = givenEmployee(22.73);
        givenTimeCard(id, 9, 1, 1, 2015);
        givenTimeCard(id, 7, 1, 2, 2015);

        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe(9.5 * 22.73);

        whenCalculatingSalaryOf(id, 2, 2015);
        thenTheSalaryShouldBe(7 * 22.73);
    }

    @Test
    public void employeeHasTimeCardsInDifferentYears_salaryMustConsiderOnlyTimeCardsWithinEachYear() {
        String id = givenEmployee(22.73);

        givenTimeCard(id, 8, 1, 1, 2015);
        givenTimeCard(id, 8, 1, 1, 2016);

        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe(8 * 22.73);

        whenCalculatingSalaryOf(id, 1, 2016);
        thenTheSalaryShouldBe(8 * 22.73);
    }

    @Ignore
    @Test
    public void employeeHasHoursWorkedOnASaturday_HoursMuBePayedTimeAndAHalf() {
        String id = givenEmployee(22.73);
        givenTimeCard(id, 8, 24, 10, 2015);
        whenCalculatingSalaryOf(id, 10, 2015);
        thenTheSalaryShouldBe((8 * 1.5) * 22.73);
    }


    private String givenEmployee(double hourlyRate) {
        createEmployee = new CreateEmployeeUseCase(hourlyRate, repository);
        createEmployee.execute();
        return createEmployee.getEmployeeId();
    }

    private void givenTimeCard(String id, int hoursWorked, int day, int month, int year) {
        createTimeCard = new CreateTimeCard(id, hoursWorked, day, month, year, repository);
        createTimeCard.execute();
    }

    private void whenCalculatingSalaryOf(String employeeId, int month, int year) {
        calculateSalary = new CalculateSalaryUseCase(employeeId, year, month, repository);
        calculateSalary.execute();
    }

    private void thenTheSalaryShouldBe(double salary) {
        Assert.assertEquals(salary, calculateSalary.getSalary(), .001);
    }
}
