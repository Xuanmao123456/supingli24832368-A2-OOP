import java.util.Comparator;

/**
 * Custom Comparator for sorting Visitor objects (ULO3: Comparator usage).
 * Sort rule: 1. Members first (isMember = true), 2. Age ascending (youngest first).
 */
public class VisitorComparator implements Comparator<Visitor> {
    @Override
    public int compare(Visitor v1, Visitor v2) {
        int memberCompare = Boolean.compare(v2.isMember(), v1.isMember());
        if (memberCompare != 0) {
            return memberCompare;
        }
        return Integer.compare(v1.getAge(), v2.getAge());
    }
}