package bmw;

import org.junit.Assert;
import org.junit.Test;

public class CalculateSalaryTest {
    CreateEmployeeUseCase createEmployee;
    CalculateSalaryUseCase calculateSalary;

    @Test
    public void employeeWorkedZeroHours_SalaryEqualsZero() {
        String id = givenEmployee(22.73);
        whenCalculatingSalaryOf(id, 1);
        thenTheSalaryShouldBe(0.0);
    }

    private String givenEmployee(double hourlyRate) {
        createEmployee = new CreateEmployeeUseCase(hourlyRate);
        createEmployee.execute();
        return createEmployee.getEmployeeId();
    }

    private void whenCalculatingSalaryOf(String employeeId, int month) {
        calculateSalary = new CalculateSalaryUseCase(employeeId, month);
        calculateSalary.execute();
    }

    private void thenTheSalaryShouldBe(double salary) {
        Assert.assertEquals(salary, calculateSalary.getSalary(), .001);
    }
}
