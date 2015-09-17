package bmw;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EmployeeRepositoryTest {

    EmployeeRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryEmployeeRepository();
    }

    @Test
    public void canSaveAndGetEmployeeById() {
        Employee employee = new Employee("0001");
        repository.save(employee);
        Employee employeeRetrieved = repository.getById("0001");
        Assert.assertNotNull(employeeRetrieved);
        assertEquals("0001", employeeRetrieved.getId());
    }

    @Test
    public void changesInTheEmployeeBeforeSaveAreNotReflectedInTheRepository() {
        Employee employee = new Employee("0001");
        repository.save(employee);
        employee.addWorkedHours(8);

        Employee employeeRetrieved = repository.getById("0001");
        Assert.assertNotNull(employeeRetrieved);
        assertEquals(0, employeeRetrieved.getTotalWorkedHours());
    }

    @Test
    public void changesInTheEmployeeAfterSaveAreNotReflectedInTheRepositoryWithoutASecondSave() {
        Employee employee = new Employee("0001");
        repository.save(employee);

        Employee employeeRetrievedBefore = repository.getById("0001");
        employeeRetrievedBefore.addWorkedHours(8);

        Employee employeeRetrievedAfter = repository.getById("0001");
        assertEquals(0, employeeRetrievedAfter.getTotalWorkedHours());
    }

    @Test
    public void changingAnEmployeeAndSavingItAgain_mustReturnTheUpdatedEmployee() {
        Employee employee = new Employee("0001");
        employee.setHourlyRate(10.0);
        repository.save(employee);

        employee.setHourlyRate(20.0);
        repository.save(employee);

        Employee employeeRetrieved = repository.getById("0001");
        assertEquals(20.0, employeeRetrieved.getHourlyRate(), .001);
    }
}
