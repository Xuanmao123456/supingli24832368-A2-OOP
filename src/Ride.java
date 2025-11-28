import java.util.Queue;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Ride class that implements RideInterface.
 * Manages ride details, waiting queue, ride history, and cycle operations.
 * Uses Queue for waiting visitors and LinkedList for ride history (ULO3: Advanced collection usage).
 */
public class Ride implements RideInterface {
    private String rideName;
    private String rideType;
    private Employee operator;
    private Queue<Visitor> waitingQueue = new LinkedList<>();
    private LinkedList<Visitor> rideHistory = new LinkedList<>();
    private int maxRider = 1;
    private int numOfCycles = 0;

    public Ride() {}

    public Ride(String rideName, String rideType, Employee operator) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
    }

    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.setMaxRider(maxRider);
    }

    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }

    public String getRideType() { return rideType; }
    public void setRideType(String rideType) { this.rideType = rideType; }

    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }

    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) {
        if (maxRider >= 1) {
            this.maxRider = maxRider;
        } else {
            System.out.println("Error: Max rider must be ≥ 1. Set to default (1).");
            this.maxRider = 1;
        }
    }

    public int getNumOfCycles() { return numOfCycles; }

    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingQueue.add(visitor);
            System.out.println("Successfully added visitor to queue: " + visitor.getVisitorId() + "-" + visitor.getName());
        } else {
            System.out.println("Error: Visitor object is null. Cannot add to queue.");
        }
    }

    @Override
    public void removeVisitorFromQueue() {
        if (!waitingQueue.isEmpty()) {
            Visitor removed = waitingQueue.poll();
            System.out.println("Successfully removed visitor from queue: " + removed.getVisitorId() + "-" + removed.getName());
        } else {
            System.out.println("Error: Queue is empty. Cannot remove visitor.");
        }
    }

    @Override
    public void printQueue() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Queue status: No waiting visitors");
            return;
        }
        System.out.println("Queue status (Total: " + waitingQueue.size() + " visitors):");
        int index = 1;
        for (Visitor visitor : waitingQueue) {
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            rideHistory.add(visitor);
            System.out.println("Successfully added visitor to ride history: " + visitor.getVisitorId() + "-" + visitor.getName());
        } else {
            System.out.println("Error: Visitor object is null. Cannot add to history.");
        }
    }

    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor == null) {
            System.out.println("Error: Visitor object is null. Cannot check history.");
            return false;
        }
        for (Visitor v : rideHistory) {
            if (v.getVisitorId().equals(visitor.getVisitorId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int numberOfVisitors() {
        return rideHistory.size();
    }

    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("Ride history: No records");
            return;
        }
        System.out.println("Ride history (Total: " + rideHistory.size() + " visitors):");
        Iterator<Visitor> iterator = rideHistory.iterator();
        int index = 1;
        while (iterator.hasNext()) {
            Visitor visitor = iterator.next();
            System.out.println(index + ". " + visitor);
            index++;
        }
    }

    public void sortRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("Error: Ride history is empty. Cannot sort.");
            return;
        }
        Collections.sort(rideHistory, new VisitorComparator());
        System.out.println("Ride history sorted successfully!");
    }

    @Override
    public void runOneCycle() {
        System.out.println("\n=== Running Ride Cycle ===");
        if (operator == null) {
            System.out.println("Error: No operator assigned. Cannot run ride cycle.");
            return;
        }
        if (waitingQueue.isEmpty()) {
            System.out.println("Error: Waiting queue is empty. Cannot run ride cycle.");
            return;
        }
        int ridersToTake = Math.min(maxRider, waitingQueue.size());
        System.out.println("This cycle will take " + ridersToTake + " visitors (Max capacity: " + maxRider + ")");

        for (int i = 0; i < ridersToTake; i++) {
            Visitor visitor = waitingQueue.poll();
            addVisitorToHistory(visitor);
        }

        numOfCycles++;
        System.out.println("Ride cycle completed! Total cycles run: " + numOfCycles);
    }

    public void exportRideHistory(String filePath) {
        System.out.println("\n=== Exporting Ride History to File ===");
        if (rideHistory.isEmpty()) {
            System.out.println("Error: Ride history is empty. No data to export.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("visitorId,name,age,email,isMember");
            writer.newLine();

            for (Visitor visitor : rideHistory) {
                String line = String.join(",",
                        visitor.getVisitorId(),
                        visitor.getName(),
                        String.valueOf(visitor.getAge()),
                        visitor.getEmail(),
                        String.valueOf(visitor.isMember())
                );
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Export successful! File path: " + filePath);
        } catch (IOException e) {
            System.out.println("Error: Failed to export file. Reason: " + e.getMessage());
        }
    }

    public void importRideHistory(String filePath) {
        System.out.println("\n=== Importing Ride History from File ===");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length != 5) {
                    System.out.println("Warning: Skipping invalid line: " + line);
                    continue;
                }

                String visitorId = parts[0];
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String email = parts[3];
                boolean isMember = Boolean.parseBoolean(parts[4]);

                Visitor visitor = new Visitor(name, age, email, visitorId, isMember);
                addVisitorToHistory(visitor);
            }
            System.out.println("Import successful! Total visitors imported: " + rideHistory.size());
        } catch (IOException e) {
            System.out.println("Error: Failed to import file. Reason: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid data format (age must be integer). Reason: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Ride{rideName='" + rideName + "', rideType='" + rideType + "', operator=" + operator + ", maxRider=" + maxRider + "}";
    }
}