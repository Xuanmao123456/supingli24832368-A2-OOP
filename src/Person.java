
/**
 * Abstract base class representing a general person in the theme park system.
 * Contains common attributes (name, age, email) for all person types (Employee/Visitor).
 * Cannot be instantiated directly (abstract class requirement for ULO2).
 */
public abstract class Person {
    // Common instance variables for all persons
    private String name;
    private int age;
    private String email;

    /**
     * No-arg constructor (required for inheritance flexibility)
     */
    public Person() {}

    /**
     * Parameterized constructor to initialize all common attributes
     * @param name Full name of the person
     * @param age Age of the person (non-negative integer)
     * @param email Contact email of the person
     */
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    // Getter and Setter methods for encapsulation (ULO2: OOP principle)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    /**
     * Override toString to print person information in readable format
     * Required for HD level (easy debugging and output)
     * @return Formatted string with person's attributes
     */
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}