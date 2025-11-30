import java.util.Scanner;

/**
 * AssignmentTwo - A class designed to complete the second assignment tasks.
 *
 * This class focuses on [briefly describe core functionality, e.g., "processing user input data,
 * performing statistical calculations, and generating formatted outputs"].
 * It includes methods for data validation, computation, and result presentation.
 *
 * Dependencies: None (standalone class)
 * Author: [Your Name]
 * Date: [Submission Date]
 */
public class AssignmentTwo {
    // Member Variables (Instance/Class Variables)
    /**
     * Stores the raw input data as an array of integers.
     * This data is initialized via the constructor or setter methods.
     */
    public static void main(String[] args) {

        AssignmentTwo assignment = new AssignmentTwo();
        Scanner scanner = new Scanner(System.in); // Global Scanner for reuse

        // Display interactive menu
        System.out.println("=== Theme Park Management System - Test Tool ===");
        System.out.println("Please select a function to test (enter number 1-7):");
        System.out.println("1 - Part3: Waiting Queue (Add/Remove/Print with manual visitor input)");
        System.out.println("2 - Part4A: Ride History (Add/Check/Count with manual visitor input)");
        System.out.println("3 - Part4B: Ride History Sort (Sort with manual visitor input)");
        System.out.println("4 - Part5: Run Single Ride Cycle (Manual queue visitor input)");
        System.out.println("5 - Part6: Export Ride History to CSV File");
        System.out.println("6 - Part7: Import Ride History from CSV File");
        System.out.println("7 - Exit Program");
        System.out.print("Your selection (1-7): ");

        // Handle user input with exception validation
        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character to avoid input exceptions
        } catch (Exception e) {
            System.out.println("✗ Error: Invalid input! Please enter a number between 1 and 7");
            scanner.close();
            return;
        }

        // Execute selected function (pass global Scanner)
        switch (choice) {
            case 1:
                assignment.partThree(scanner);
                break;
            case 2:
                assignment.partFourA(scanner);
                break;
            case 3:
                assignment.partFourB(scanner);
                break;
            case 4:
                assignment.partFive(scanner);
                break;
            case 5:
                assignment.partSix(scanner);
                break;
            case 6:
                assignment.partSeven(scanner);
                break;
            case 7:
                System.out.println("✓ Program exited successfully!");
                break;
            default:
                System.out.println("✗ Error: Invalid selection! Please enter a number between 1 and 7");
        }

        scanner.close(); // Close Scanner uniformly at last
    }

    /**
     * Utility method: Add a single visitor interactively (reuse Scanner to avoid duplicate creation)
     * @param scanner Global Scanner object
     * @return Visitor object created by user input
     */
    private Visitor addVisitorInteractively(Scanner scanner) {
        System.out.println("\n--- Please enter visitor information ---");

        // 1. Input name (non-empty validation)
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.println("✗ Name cannot be empty!");
            System.out.print("Name: ");
            name = scanner.nextLine().trim();
        }

        // 2. Input age (validation: integer + ≥0)
        int age = -1;
        while (age < 0) {
            System.out.print("Age (≥0): ");
            String ageInput = scanner.nextLine().trim();
            try {
                age = Integer.parseInt(ageInput);
                if (age < 0) {
                    System.out.println("✗ Age cannot be negative!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Age must be a number!");
            }
        }

        // 3. Input email (non-empty validation)
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        while (email.isEmpty()) {
            System.out.println("✗ Email cannot be empty!");
            System.out.print("Email: ");
            email = scanner.nextLine().trim();
        }

        // 4. Input visitor ID (non-empty validation)
        System.out.print("Visitor ID (Example: VIS001): ");
        String visitorId = scanner.nextLine().trim();
        while (visitorId.isEmpty()) {
            System.out.println("✗ Visitor ID cannot be empty!");
            System.out.print("Visitor ID: ");
            visitorId = scanner.nextLine().trim();
        }

        // 5. Input membership status (validation: y/n)
        boolean isMember = false;
        while (true) {
            System.out.print("Is member (y/n): ");
            String memberInput = scanner.nextLine().trim().toLowerCase();
            if (memberInput.equals("y")) {
                isMember = true;
                break;
            } else if (memberInput.equals("n")) {
                isMember = false;
                break;
            } else {
                System.out.println("✗ Only y or n is allowed!");
            }
        }

        // Create and return visitor object
        Visitor visitor = new Visitor(name, age, email, visitorId, isMember);
        System.out.println("✓ Visitor created successfully: " + name + " (ID: " + visitorId + ")");
        return visitor;
    }

    /**
     * Part3: Waiting Queue Test (supports manual visitor input)
     * @param scanner Global Scanner
     */
    public void partThree(Scanner scanner) {
        System.out.println("\n=== Part3: Waiting Queue Test ===");
        // Create operator and ride facility
        Employee operator = new Employee("John Doe", 35, "john@themepark.com", "EMP001", "Ride Operator");
        Ride rollerCoaster = new Ride("Roller Coaster", "Thrill Ride", operator);

        // Let user select number of visitors to add
        int visitorCount = 0;
        while (visitorCount <= 0) {
            System.out.print("Enter number of visitors to add to queue (≥1): ");
            String countInput = scanner.nextLine().trim();
            try {
                visitorCount = Integer.parseInt(countInput);
                if (visitorCount <= 0) {
                    System.out.println("✗ Count must be ≥1!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Count must be a number!");
            }
        }

        // Add specified number of visitors in loop
        for (int i = 0; i < visitorCount; i++) {
            System.out.println("\n--- Adding " + (i + 1) + "th visitor ---");
            Visitor visitor = addVisitorInteractively(scanner);
            rollerCoaster.addVisitorToQueue(visitor);
        }

        // Print queue status
        System.out.println("\n=== Current Queue Status ===");
        rollerCoaster.printQueue();

        // Remove first visitor from queue
        System.out.println("\n=== Remove First Visitor from Queue ===");
        rollerCoaster.removeVisitorFromQueue();

        // Print queue status after removal
        System.out.println("\n=== Queue Status After Removal ===");
        rollerCoaster.printQueue();
    }

    /**
     * Part4A: Ride History Test (supports manual visitor input)
     * @param scanner Global Scanner
     */
    public void partFourA(Scanner scanner) {
        System.out.println("\n=== Part4A: Ride History Test ===");
        // Create operator and ride facility
        Employee operator = new Employee("Jane Smith", 32, "jane@themepark.com", "EMP002", "Ride Operator");
        Ride thunderstorm = new Ride("Thunderstorm", "Water Ride", operator);

        // Let user select number of visitors to add
        int visitorCount = 0;
        while (visitorCount <= 0) {
            System.out.print("Enter number of visitors to add to history (≥1): ");
            String countInput = scanner.nextLine().trim();
            try {
                visitorCount = Integer.parseInt(countInput);
                if (visitorCount <= 0) {
                    System.out.println("✗ Count must be ≥1!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Count must be a number!");
            }
        }

        // Add specified number of visitors in loop
        Visitor targetVisitor = null; // Target visitor for subsequent query
        for (int i = 0; i < visitorCount; i++) {
            System.out.println("\n--- Adding " + (i + 1) + "th visitor ---");
            Visitor visitor = addVisitorInteractively(scanner);
            thunderstorm.addVisitorToHistory(visitor);
            // Save first visitor as query example
            if (i == 0) {
                targetVisitor = visitor;
            }
        }

        // Check if target visitor exists in history
        if (targetVisitor != null) {
            System.out.println("\n=== Check Visitor in History ===");
            boolean isInHistory = thunderstorm.checkVisitorFromHistory(targetVisitor);
            System.out.println("Is visitor " + targetVisitor.getVisitorId() + "-" + targetVisitor.getName() + " in history: " + (isInHistory ? "Yes" : "No"));
        }

        // Print history statistics and details
        System.out.println("\n=== Ride History Statistics ===");
        System.out.println("Total visitors in ride history: " + thunderstorm.numberOfVisitors());
        System.out.println("\n=== Ride History Details ===");
        thunderstorm.printRideHistory();
    }

    /**
     * Part4B: Ride History Sort Test (supports manual visitor input)
     * @param scanner Global Scanner
     */
    public void partFourB(Scanner scanner) {
        System.out.println("\n=== Part4B: Ride History Sort Test ===");
        // Create operator and ride facility
        Employee operator = new Employee("Mike Wilson", 30, "mike@themepark.com", "EMP003", "Ride Operator");
        Ride ferrisWheel = new Ride("Ferris Wheel", "Family Ride", operator);

        // Let user select number of visitors to add
        int visitorCount = 0;
        while (visitorCount <= 0) {
            System.out.print("Enter number of visitors to add to history (≥1): ");
            String countInput = scanner.nextLine().trim();
            try {
                visitorCount = Integer.parseInt(countInput);
                if (visitorCount <= 0) {
                    System.out.println("✗ Count must be ≥1!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Count must be a number!");
            }
        }

        // Add specified number of visitors in loop
        for (int i = 0; i < visitorCount; i++) {
            System.out.println("\n--- Adding " + (i + 1) + "th visitor ---");
            Visitor visitor = addVisitorInteractively(scanner);
            ferrisWheel.addVisitorToHistory(visitor);
        }

        // Print history before sorting
        System.out.println("\n=== Ride History Before Sorting ===");
        ferrisWheel.printRideHistory();

        // Execute sorting
        System.out.println("\n=== Execute Sorting ===");
        ferrisWheel.sortRideHistory();

        // Print history after sorting
        System.out.println("\n=== Ride History After Sorting ===");
        ferrisWheel.printRideHistory();
    }

    /**
     * Part5: Ride Cycle Test (supports manual queue visitor input)
     * @param scanner Global Scanner
     */
    public void partFive(Scanner scanner) {
        System.out.println("\n=== Part5: Ride Cycle Test ===");
        // Create operator and ride facility (max rider: 3)
        Employee operator = new Employee("Sarah Brown", 28, "sarah@themepark.com", "EMP004", "Ride Operator");
        Ride logFlume = new Ride("Log Flume", "Water Ride", operator, 3);

        // Let user select number of visitors to add to queue
        int visitorCount = 0;
        while (visitorCount <= 0) {
            System.out.print("Enter number of visitors to add to queue (≥1): ");
            String countInput = scanner.nextLine().trim();
            try {
                visitorCount = Integer.parseInt(countInput);
                if (visitorCount <= 0) {
                    System.out.println("✗ Count must be ≥1!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Count must be a number!");
            }
        }

        // Add specified number of visitors to queue in loop
        for (int i = 0; i < visitorCount; i++) {
            System.out.println("\n--- Adding " + (i + 1) + "th visitor to queue ---");
            Visitor visitor = addVisitorInteractively(scanner);
            logFlume.addVisitorToQueue(visitor);
        }

        // Print queue status before cycle
        System.out.println("\n=== Queue Status Before Cycle ===");
        logFlume.printQueue();

        // Run single cycle
        logFlume.runOneCycle();

        // Print queue status after cycle
        System.out.println("\n=== Queue Status After Cycle ===");
        logFlume.printQueue();

        // Print new history from this cycle
        System.out.println("\n=== New Ride History from This Cycle ===");
        logFlume.printRideHistory();

        // Print total cycle count
        System.out.println("\n=== Cycle Statistics ===");
        System.out.println("Total cycles run: " + logFlume.getNumOfCycles());
    }

    /**
     * Part6: CSV Export Test (add visitors to history first)
     * @param scanner Global Scanner
     */
    public void partSix(Scanner scanner) {
        System.out.println("\n=== Part6: Export Ride History to CSV ===");
        // Create operator and ride facility
        Employee operator = new Employee("David Clark", 31, "david@themepark.com", "EMP005", "Ride Operator");
        Ride bumperCars = new Ride("Bumper Cars", "Family Ride", operator, 4);

        // Add visitors to history first
        int visitorCount = 0;
        while (visitorCount <= 0) {
            System.out.print("Enter number of visitors to add to history (≥1): ");
            String countInput = scanner.nextLine().trim();
            try {
                visitorCount = Integer.parseInt(countInput);
                if (visitorCount <= 0) {
                    System.out.println("✗ Count must be ≥1!");
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Count must be a number!");
            }
        }
        for (int i = 0; i < visitorCount; i++) {
            System.out.println("\n--- Adding " + (i + 1) + "th visitor ---");
            Visitor visitor = addVisitorInteractively(scanner);
            bumperCars.addVisitorToHistory(visitor);
        }

        // Let user input CSV save path
        System.out.print("\nEnter CSV file save path (Example: C:/Users/YourUsername/Desktop/rideHistory.csv): ");
        String filePath = scanner.nextLine().trim();
        while (filePath.isEmpty()) {
            System.out.println("✗ Path cannot be empty!");
            System.out.print("CSV file save path: ");
            filePath = scanner.nextLine().trim();
        }

        // Export to CSV
        bumperCars.exportRideHistory(filePath);
    }

    /**
     * Part7: CSV Import Test
     * @param scanner Global Scanner
     */
    public void partSeven(Scanner scanner) {
        System.out.println("\n=== Part7: Import Ride History from CSV ===");
        // Create empty ride facility
        Ride importRide = new Ride("Import Test Ride", "Test", null, 2);

        // Let user input CSV file path
        System.out.print("Enter CSV file path (Example: C:/Users/YourUsername/Desktop/rideHistory.csv): ");
        String filePath = scanner.nextLine().trim();
        while (filePath.isEmpty()) {
            System.out.println("✗ Path cannot be empty!");
            System.out.print("CSV file path: ");
            filePath = scanner.nextLine().trim();
        }

        // Import from CSV
        importRide.importRideHistory(filePath);

        // Verify import result
        System.out.println("\n=== Import Result Verification ===");
        System.out.println("Total visitors imported: " + importRide.numberOfVisitors());
        System.out.println("\n=== Imported Visitor Details ===");
        importRide.printRideHistory();
    }
}