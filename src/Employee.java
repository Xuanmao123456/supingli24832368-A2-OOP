/**
 * Employee class that inherits from Person abstract class.
 * Represents theme park employees with unique attributes (employee ID, position).
 */
public class Employee extends Person {
    // Unique instance variables for employees
    private String employeeId;
    private String position;

    /**
     * No-arg constructor (consistent with parent class structure)
     */
    public Employee() {}

    /**
     * Parameterized constructor to initialize both parent and child attributes
     * @param name Employee's full name (inherited from Person)
     * @param age Employee's age (inherited from Person)
     * @param email Employee's contact email (inherited from Person)
     * @param employeeId Unique ID for the employee (e.g., EMP001)
     * @param position Job position (e.g., Ride Operator, Security)
     */
    public Employee(String name, int age, String email, String employeeId, String position) {
        super(name, age, email); // Call parent class constructor to initialize inherited attributes
        this.employeeId = employeeId;
        this.position = position;
    }

    // Getter and Setter methods for employee-specific attributes
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    /**
     * Override toString to include both parent and employee-specific attributes
     * @return Formatted string with full employee information
     */
    @Override
    public String toString() {
        return "Employee{" + super.toString() + ", employeeId='" + employeeId + "', position='" + position + "'}";
    }
}