/**
 * Employee class that inherits from Person abstract class.
 * Represents theme park employees with unique attributes (employee ID, position).
 */
public class Employee extends Person {
    private String employeeId;
    private String position;

    public Employee() {}

    public Employee(String name, int age, String email, String employeeId, String position) {
        super(name, age, email);
        this.employeeId = employeeId;
        this.position = position;
    }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    @Override
    public String toString() {
        return "Employee{" + super.toString() + ", employeeId='" + employeeId + "', position='" + position + "'}";
    }
}