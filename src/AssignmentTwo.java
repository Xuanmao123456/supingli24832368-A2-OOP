import java.util.Scanner;

/**
 * Main class for PROG2004 A2 Theme Park Management System.
 * Interactive menu for testing all parts (Part3 to Part7) of the assignment.
 */
public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo assignment = new AssignmentTwo();
        Scanner scanner = new Scanner(System.in);

        // Interactive menu display
        System.out.println("=== Theme Park Management System - Test Tool ===");
        System.out.println("Please select a function to test (enter number 1-7):");
        System.out.println("1 - Part3: Waiting Queue (Add/Remove/Print)");
        System.out.println("2 - Part4A: Ride History (Add/Check/Count/Print)");
        System.out.println("3 - Part4B: Ride History Sort (Members First + Age Ascending)");
        System.out.println("4 - Part5: Run One Ride Cycle (Max Rider + Cycle Count)");
        System.out.println("5 - Part6: Export Ride History to CSV File");
        System.out.println("6 - Part7: Import Ride History from CSV File");
        System.out.println("7 - Exit Program");
        System.out.print("Your selection (1-7): ");

        // Handle user input
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: Invalid input! Please enter a number between 1 and 7.");
            scanner.close();
            return;
        }

        // Execute selected function
        switch (choice) {
            case 1:
                assignment.partThree();
                break;
            case 2:
                assignment.partFourA();
                break;
            case 3:
                assignment.partFourB();
                break;
            case 4:
                assignment.partFive();
                break;
            case 5:
                assignment.partSix();
                break;
            case 6:
                assignment.partSeven();
                break;
            case 7:
                System.out.println("Program exited successfully!");
                break;
            default:
                System.out.println("Error: Invalid selection! Please enter a number between 1 and 7.");
        }

        scanner.close();
    }

    /**
     * Test Part3: Waiting Queue Functionality
     */
    public void partThree() {
        System.out.println("\n=== Part3: Waiting Queue Test ===");
        Employee operator = new Employee("John Doe", 30, "john@themepark.com", "EMP001", "Ride Operator");
        Ride rollerCoaster = new Ride("Roller Coaster", "Thrill Ride", operator);

        Visitor v1 = new Visitor("Alice", 25, "alice@test.com", "VIS001", true);
        Visitor v2 = new Visitor("Bob", 30, "bob@test.com", "VIS002", false);
        Visitor v3 = new Visitor("Charlie", 22, "charlie@test.com", "VIS003", true);
        Visitor v4 = new Visitor("Diana", 28, "diana@test.com", "VIS004", false);
        Visitor v5 = new Visitor("Ethan", 35, "ethan@test.com", "VIS005", true);

        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);

        rollerCoaster.printQueue();
        rollerCoaster.removeVisitorFromQueue();

        System.out.println("\nAfter removing one visitor:");
        rollerCoaster.printQueue();
    }

    /**
     * Test Part4A: Ride History Functionality
     */
    public void partFourA() {
        System.out.println("\n=== Part4A: Ride History Test ===");
        Employee operator = new Employee("Jane Smith", 28, "jane@themepark.com", "EMP002", "Ride Operator");
        Ride thunderstorm = new Ride("Thunderstorm", "Water Ride", operator);

        Visitor v1 = new Visitor("Frank", 24, "frank@test.com", "VIS006", true);
        Visitor v2 = new Visitor("Grace", 27, "grace@test.com", "VIS007", false);
        Visitor v3 = new Visitor("Henry", 29, "henry@test.com", "VIS008", true);
        Visitor v4 = new Visitor("Ivy", 23, "ivy@test.com", "VIS009", false);
        Visitor v5 = new Visitor("Jack", 32, "jack@test.com", "VIS010", true);

        thunderstorm.addVisitorToHistory(v1);
        thunderstorm.addVisitorToHistory(v2);
        thunderstorm.addVisitorToHistory(v3);
        thunderstorm.addVisitorToHistory(v4);
        thunderstorm.addVisitorToHistory(v5);

        boolean isInHistory = thunderstorm.checkVisitorFromHistory(v3);
        System.out.println("\nIs visitor VIS008-Henry in history? " + (isInHistory ? "Yes" : "No"));
        System.out.println("Total visitors in ride history: " + thunderstorm.numberOfVisitors());
        thunderstorm.printRideHistory();
    }

    /**
     * Test Part4B: Ride History Sort
     */
    public void partFourB() {
        System.out.println("\n=== Part4B: Ride History Sort Test ===");
        Employee operator = new Employee("Mike Wilson", 33, "mike@themepark.com", "EMP003", "Ride Operator");
        Ride ferrisWheel = new Ride("Ferris Wheel", "Family Ride", operator);

        Visitor v1 = new Visitor("Lisa", 26, "lisa@test.com", "VIS011", false);
        Visitor v2 = new Visitor("Mark", 22, "mark@test.com", "VIS012", true);
        Visitor v3 = new Visitor("Nancy", 30, "nancy@test.com", "VIS013", true);
        Visitor v4 = new Visitor("Oscar", 28, "oscar@test.com", "VIS014", false);
        Visitor v5 = new Visitor("Penny", 24, "penny@test.com", "VIS015", true);

        ferrisWheel.addVisitorToHistory(v1);
        ferrisWheel.addVisitorToHistory(v2);
        ferrisWheel.addVisitorToHistory(v3);
        ferrisWheel.addVisitorToHistory(v4);
        ferrisWheel.addVisitorToHistory(v5);

        System.out.println("\nRide history before sorting:");
        ferrisWheel.printRideHistory();
        ferrisWheel.sortRideHistory();

        System.out.println("\nRide history after sorting (Members first + Age ascending):");
        ferrisWheel.printRideHistory();
    }

    /**
     * Test Part5: Run One Ride Cycle
     */
    public void partFive() {
        System.out.println("\n=== Part5: Ride Cycle Test ===");
        Employee operator = new Employee("Sarah Brown", 29, "sarah@themepark.com", "EMP004", "Ride Operator");
        Ride logFlume = new Ride("Log Flume", "Water Ride", operator, 3);

        for (int i = 1; i <= 10; i++) {
            Visitor visitor = new Visitor(
                    "Visitor" + i,
                    20 + i,
                    "visitor" + i + "@test.com",
                    "VIS0" + (15 + i),
                    i % 2 == 0
            );
            logFlume.addVisitorToQueue(visitor);
        }

        System.out.println("Queue before running cycle:");
        logFlume.printQueue();
        logFlume.runOneCycle();

        System.out.println("\nQueue after running cycle:");
        logFlume.printQueue();
        System.out.println("\nRide history (new visitors from this cycle):");
        logFlume.printRideHistory();
        System.out.println("Total cycles run: " + logFlume.getNumOfCycles());
    }

    /**
     * Test Part6: Export to CSV
     * Note: Replace the file path with your actual desktop path
     */
    public void partSix() {
        System.out.println("\n=== Part6: Export Ride History to CSV ===");
        Employee operator = new Employee("David Clark", 31, "david@themepark.com", "EMP005", "Ride Operator");
        Ride bumperCars = new Ride("Bumper Cars", "Family Ride", operator, 4);

        Visitor v1 = new Visitor("Quinn", 25, "quinn@test.com", "VIS026", true);
        Visitor v2 = new Visitor("Rachel", 27, "rachel@test.com", "VIS027", false);
        Visitor v3 = new Visitor("Sam", 23, "sam@test.com", "VIS028", true);
        Visitor v4 = new Visitor("Tina", 29, "tina@test.com", "VIS029", false);
        Visitor v5 = new Visitor("Umar", 26, "umar@test.com", "VIS030", true);

        bumperCars.addVisitorToHistory(v1);
        bumperCars.addVisitorToHistory(v2);
        bumperCars.addVisitorToHistory(v3);
        bumperCars.addVisitorToHistory(v4);
        bumperCars.addVisitorToHistory(v5);

        // Replace with your actual file path (e.g., "C:/Users/YourName/Desktop/rideHistory.csv")
        String filePath = "C:/Users/YourUsername/Desktop/rideHistory.csv";
        bumperCars.exportRideHistory(filePath);
    }

    /**
     * Test Part7: Import from CSV
     * Note: Use the same file path as Part6
     */
    public void partSeven() {
        System.out.println("\n=== Part7: Import Ride History from CSV ===");
        Ride importRide = new Ride("Import Test Ride", "Test", null, 2);

        // Replace with your actual file path (same as Part6)
        String filePath = "C:/Users/YourUsername/Desktop/rideHistory.csv";
        importRide.importRideHistory(filePath);

        System.out.println("\nTotal visitors imported: " + importRide.numberOfVisitors());
        System.out.println("Imported visitors details:");
        importRide.printRideHistory();
    }
}