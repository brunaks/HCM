package bmw;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculateSalaryTest {
    CreateEmployeeUseCase createEmployee;
    CalculateSalaryUseCase calculateSalary;
    CreateTimeCard createTimeCard;
    Repository repository;

    @Before
    public void setUp() throws Exception {
        repository = new Repository();
    }

    @Test
    public void employeeHasNoTimeCards_SalaryEqualsZero() {
        String id = givenEmployee(22.73);
        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe(0.0);
    }

    @Test
    public void employeeHasOneTimeCard_salaryMustBeEightHoursWorthOfMoney() {
        String id = givenEmployee(22.73);
        givenTimeCard(id, 8, 01, 01, 2015);
        whenCalculatingSalaryOf(id, 1, 2015);
        thenTheSalaryShouldBe(8*22.73);
    }

    private void givenTimeCard(String id, int hoursWorked, int day, int month, int year) {
        createTimeCard = new CreateTimeCard(id, hoursWorked, day, month, year, repository);
        createTimeCard.execute();
    }

    private String givenEmployee(double hourlyRate) {
        createEmployee = new CreateEmployeeUseCase(hourlyRate, repository);
        createEmployee.execute();
        return createEmployee.getEmployeeId();
    }

    private void whenCalculatingSalaryOf(String employeeId, int month, int year) {
        calculateSalary = new CalculateSalaryUseCase(employeeId, year, month, repository);
        calculateSalary.execute();
    }

    private void thenTheSalaryShouldBe(double salary) {
        Assert.assertEquals(salary, calculateSalary.getSalary(), .001);
    }
}
