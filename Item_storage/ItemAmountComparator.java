import java.util.Comparator;
public class ItemAmountComparator implements Comparator<Item>{

    @Override
    public int compare(Item o1, Item o2) {
        return o1.amount-o2.amount;
    }
}
