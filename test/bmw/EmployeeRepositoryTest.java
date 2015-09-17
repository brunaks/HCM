package bmw;

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
        assertEquals("0001", employeeRetrieved.getId());
    }

    @Test
    public void changesInTheEmployeeBeforeSaveAreNotReflectedInTheRepository() {
        Employee employee = new Employee("0001");
        repository.save(employee);
        employee.setName("Name 1");

        Employee employeeRetrieved = repository.getById("0001");
        assertEquals("", employeeRetrieved.getName());
    }

    @Test
    public void changesInTheEmployeeAfterSaveAreNotReflectedInTheRepositoryWithoutASecondSave() {
        Employee employee = new Employee("0001");
        repository.save(employee);

        Employee employeeRetrievedBefore = repository.getById("0001");
        employeeRetrievedBefore.setName("Name 1");

        Employee employeeRetrievedAfter = repository.getById("0001");
        assertEquals("", employeeRetrievedAfter.getName());
    }

    @Test
    public void changingAnEmployeeAndSavingItAgain_mustReturnTheUpdatedEmployee() {
        Employee employee = new Employee("0001");
        employee.setName("Name 1");
        repository.save(employee);

        employee.setName("Name 2");
        repository.save(employee);

        Employee employeeRetrieved = repository.getById("0001");
        assertEquals("Name 2", employeeRetrieved.getName());
    }
}
