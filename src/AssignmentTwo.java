/**
 * Main class for PROG2004 A2 Theme Park Management System.
 * Contains test methods for all parts (Part3 to Part7) of the assignment.
 */
public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo assignment = new AssignmentTwo();
        // Uncomment one method at a time to test
        // assignment.partThree();
        // assignment.partFourA();
        // assignment.partFourB();
        // assignment.partFive();
        // assignment.partSix();
        // assignment.partSeven();
    }

    /**
     * Test Part3: Waiting queue functionality (add/remove/print)
     */
    public void partThree() {
        System.out.println("=== Part3 Queue Function Test ===");
        // 1. Create a Ride object (need Employee as operator first)
        Employee operator = new Employee("John Doe", 30, "john@themepark.com", "EMP001", "Ride Operator");
        Ride rollerCoaster = new Ride("Roller Coaster", "Thrill Ride", operator);

        // 2. Create 5 visitor objects (meet "at least 5" requirement)
        Visitor v1 = new Visitor("Alice", 25, "alice@test.com", "VIS001", true);
        Visitor v2 = new Visitor("Bob", 30, "bob@test.com", "VIS002", false);
        Visitor v3 = new Visitor("Charlie", 22, "charlie@test.com", "VIS003", true);
        Visitor v4 = new Visitor("Diana", 28, "diana@test.com", "VIS004", false);
        Visitor v5 = new Visitor("Ethan", 35, "ethan@test.com", "VIS005", true);

        // 3. Add visitors to queue
        rollerCoaster.addVisitorToQueue(v1);
        rollerCoaster.addVisitorToQueue(v2);
        rollerCoaster.addVisitorToQueue(v3);
        rollerCoaster.addVisitorToQueue(v4);
        rollerCoaster.addVisitorToQueue(v5);

        // 4. Print queue
        rollerCoaster.printQueue();

        // 5. Remove one visitor
        rollerCoaster.removeVisitorFromQueue();

        // 6. Print queue again (verify removal)
        System.out.println("\nAfter removing a visitor：");
        rollerCoaster.printQueue();
    }

    /**
     * Test Part4A: Ride history functionality (add/check/count/print with Iterator)
     */
    public void partFourA() {
        System.out.println("\n=== Part4A Riding history function test ===");
        // 1. Create Ride and operator
        Employee operator = new Employee("Jane Smith", 28, "jane@themepark.com", "EMP002", "Ride Operator");
        Ride thunderstorm = new Ride("Thunderstorm", "Water Ride", operator);

        // 2. Create 5 visitors (meet "at least 5" requirement)
        Visitor v1 = new Visitor("Frank", 24, "frank@test.com", "VIS006", true);
        Visitor v2 = new Visitor("Grace", 27, "grace@test.com", "VIS007", false);
        Visitor v3 = new Visitor("Henry", 29, "henry@test.com", "VIS008", true);
        Visitor v4 = new Visitor("Ivy", 23, "ivy@test.com", "VIS009", false);
        Visitor v5 = new Visitor("Jack", 32, "jack@test.com", "VIS010", true);

        // 3. Add to ride history
        thunderstorm.addVisitorToHistory(v1);
        thunderstorm.addVisitorToHistory(v2);
        thunderstorm.addVisitorToHistory(v3);
        thunderstorm.addVisitorToHistory(v4);
        thunderstorm.addVisitorToHistory(v5);

        // 4. Check if a visitor is in history
        boolean isInHistory = thunderstorm.checkVisitorFromHistory(v3);
        System.out.println("\nVisitor" + v3.getVisitorId() + "-" + v3.getName() + "Is it in history?、？" + (isInHistory ? "Yes" : "No"));

        // 5. Print total number of visitors in history
        System.out.println("Total number of visitors in cycling history：" + thunderstorm.numberOfVisitors());

        // 6. Print ride history (with Iterator)
        thunderstorm.printRideHistory();
    }

    /**
     * Test Part4B: Sort ride history with custom Comparator (member first + age ascending)
     */
    public void partFourB() {
        System.out.println("=== Part4B Riding history sorting test ===");
        // 1. Create Ride and operator
        Employee operator = new Employee("Mike Wilson", 33, "mike@themepark.com", "EMP003", "Ride Operator");
        Ride ferrisWheel = new Ride("Ferris Wheel", "Family Ride", operator);

        // 2. Create 5 visitors (mix members/non-members, different ages)
        Visitor v1 = new Visitor("Lisa", 26, "lisa@test.com", "VIS011", false); // Non-member, 26
        Visitor v2 = new Visitor("Mark", 22, "mark@test.com", "VIS012", true);  // Member, 22
        Visitor v3 = new Visitor("Nancy", 30, "nancy@test.com", "VIS013", true); // Member, 30
        Visitor v4 = new Visitor("Oscar", 28, "oscar@test.com", "VIS014", false); // Non-member, 28
        Visitor v5 = new Visitor("Penny", 24, "penny@test.com", "VIS015", true); // Member, 24

        // 3. Add to ride history
        ferrisWheel.addVisitorToHistory(v1);
        ferrisWheel.addVisitorToHistory(v2);
        ferrisWheel.addVisitorToHistory(v3);
        ferrisWheel.addVisitorToHistory(v4);
        ferrisWheel.addVisitorToHistory(v5);

        // 4. Print before sorting
        System.out.println("\nRiding history before sorting：");
        ferrisWheel.printRideHistory();

        // 5. Sort
        ferrisWheel.sortRideHistory();

        // 6. Print after sorting (verify result)
        System.out.println("\nSorted cycling history (members first + ascending order by age)）：");
        ferrisWheel.printRideHistory();
    }

    /**
     * Test Part5: Run one ride cycle (maxRider + numOfCycles)
     */
    public void partFive() {
        System.out.println("=== Part5 Riding cycle test ===");
        // 1. Create operator
        Employee operator = new Employee("Sarah Brown", 29, "sarah@themepark.com", "EMP004", "Ride Operator");

        // 2. Create Ride (maxRider=3, max 3 riders per cycle)
        Ride logFlume = new Ride("Log Flume", "Water Ride", operator, 3);

        // 3. Add 10 visitors to queue (meet "at least 10" requirement)
        for (int i = 1; i <= 10; i++) {
            Visitor visitor = new Visitor(
                    "Visitor" + i,
                    20 + i,
                    "visitor" + i + "@test.com",
                    "VIS0" + (15 + i),
                    i % 2 == 0 // Even ID = member
            );
            logFlume.addVisitorToQueue(visitor);
        }

        // 4. Print queue before running
        System.out.println("\nQueue before running：");
        logFlume.printQueue();

        // 5. Run one cycle
        logFlume.runOneCycle();

        // 6. Print queue after running (7 left)
        System.out.println("\nThe queue after running：");
        logFlume.printQueue();

        // 7. Print ride history (3 new visitors)
        System.out.println("\nRiding history (newly added in this cycle）：");
        logFlume.printRideHistory();

        // 8. Print total cycles
        System.out.println("Number of operating cycles：" + logFlume.getNumOfCycles());
    }

    /**
     * Test Part6: Export ride history to CSV file
     */
    public void partSix() {
        System.out.println("=== Part6 Export riding history test ===");
        // 1. Create operator and Ride
        Employee operator = new Employee("David Clark", 31, "david@themepark.com", "EMP005", "Ride Operator");
        Ride bumperCars = new Ride("Bumper Cars", "Family Ride", operator, 4);

        // 2. Add 5 visitors to history (meet "at least 5" requirement)
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

        // 3. Export to CSV (replace with your desktop path)
        String filePath = "C:/Users/你的用户名/Desktop/rideHistory.csv";
        bumperCars.exportRideHistory(filePath);
    }

    /**
     * Test Part7: Import ride history from CSV file
     */
    public void partSeven() {
        System.out.println("=== Part7 导入骑行历史测试 ===");
        // 1. Create new Ride (empty history)
        Ride importRide = new Ride("Import Test Ride", "Test", null, 2);

        // 2. Import CSV from Part6 (same path)
        String filePath = "C:/Users/你的用户名/Desktop/rideHistory.csv";
        importRide.importRideHistory(filePath);

        // 3. Verify import result
        System.out.println("\nTotal number of visitors after import：" + importRide.numberOfVisitors());
        System.out.println("Visitor details after import：");
        importRide.printRideHistory();
    }
}