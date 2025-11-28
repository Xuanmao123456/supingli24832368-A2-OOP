/**
 * Interface for theme park ride operations.
 * Defines mandatory methods for queue management, ride history, and cycle execution (ULO2: Interface principle).
 * All methods must be implemented by the Ride class.
 */
public interface RideInterface {
    // Part3: Queue management methods
    /**
     * Add a visitor to the ride's waiting queue
     * @param visitor The visitor to add (non-null)
     */
    void addVisitorToQueue(Visitor visitor);

    /**
     * Remove the first visitor from the waiting queue (FIFO rule)
     */
    void removeVisitorFromQueue();

    /**
     * Print all visitors in the waiting queue (with index and details)
     */
    void printQueue();

    // Part4: Ride history management methods
    /**
     * Add a visitor to the ride's history list
     * @param visitor The visitor who completed the ride (non-null)
     */
    void addVisitorToHistory(Visitor visitor);

    /**
     * Check if a visitor exists in the ride history (by visitor ID)
     * @param visitor The visitor to check (non-null)
     * @return True if exists, False otherwise
     */
    boolean checkVisitorFromHistory(Visitor visitor);

    /**
     * Return the total number of visitors in the ride history
     * @return Integer count of visitors
     */
    int numberOfVisitors();

    /**
     * Print all visitors in the ride history (must use Iterator for ULO3)
     */
    void printRideHistory();

    // Part5: Ride cycle execution method
    /**
     * Run one cycle of the ride (move visitors from queue to history, update cycle count)
     */
    void runOneCycle();
}