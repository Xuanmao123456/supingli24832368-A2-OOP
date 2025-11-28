/**
 * Abstract base class representing a general person in the theme park system.
 * Contains common attributes (name, age, email) for all person types (Employee/Visitor).
 * Cannot be instantiated directly (abstract class requirement for ULO2).
 */
public abstract class Person {
    private String name;
    private int age;
    private String email;

    public Person() {}

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", email='" + email + "'}";
    }
}