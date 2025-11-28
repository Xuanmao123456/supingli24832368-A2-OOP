/**
 * Visitor class that inherits from Person abstract class.
 * Represents theme park visitors with unique attributes (visitor ID, membership status).
 */
public class Visitor extends Person {
    private String visitorId;
    private boolean isMember;

    public Visitor() {}

    public Visitor(String name, int age, String email, String visitorId, boolean isMember) {
        super(name, age, email);
        this.visitorId = visitorId;
        this.isMember = isMember;
    }

    public String getVisitorId() { return visitorId; }
    public void setVisitorId(String visitorId) { this.visitorId = visitorId; }

    public boolean isMember() { return isMember; }
    public void setMember(boolean member) { isMember = member; }

    @Override
    public String toString() {
        return "Visitor{" + super.toString() + ", visitorId='" + visitorId + "', isMember=" + isMember + "}";
    }
}