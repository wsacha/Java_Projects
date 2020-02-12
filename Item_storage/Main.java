import java.util.List;

public class Main {

    public static void main(String[] args) {
    FulfillmentCenter o = new FulfillmentCenter("Weapons",60);


        Item item=new Item("Swords",5,ItemCondition.REFURBISHED,3);
        o.addProduct(item);
        item=new Item("Shortbows",3,ItemCondition.REFURBISHED,5);
        o.addProduct(item);
        o.addProduct(new Item("Crossbows",6,ItemCondition.NEW,1));
        o.addProduct(new Item("Longbows",4,ItemCondition.USED,2));
        o.addProduct(new Item("Maces",5,ItemCondition.USED,2));
        o.addProduct(new Item("Machetes",3,ItemCondition.USED,4));
        o.summary();
        //
        o.getProduct(item);
        o.summary();
        //
        System.out.println("Deleting Bows");
        o.removeProduct(item);
        o.summary();
        //
        System.out.println("Looking for Crossbows: ");
        o.search("Crossbows").print();
        //
        System.out.println("Amount of USED weapons: " + o.countByCondition(ItemCondition.USED));
        //
        System.out.println("Sorted by Amount...");
        List<Item> byAmountSorted=o.sortByAmount();
        for(Item tmp: byAmountSorted) {
            tmp.print();
        }
        //
        System.out.println("Sorted by Name...");
        List<Item> byNameSort= o.sortByName();
        for(Item tmp: byAmountSorted) {
            tmp.print();
        }
        //
        System.out.println("Max: " );
        o.max().print();
        FulfillmentCenterContainer contain = new FulfillmentCenterContainer();
        contain.addCenter("Storage1", 20.0);
        contain.addCenter("Storage2", 40.0);
        contain.addCenter(o);
        List<FulfillmentCenter> empty_centers=contain.findEmpty();
        System.out.println("\nFIND EMPTY CENTERS:");
        for (int j=0;j<empty_centers.size();j++) {
            empty_centers.get(j).summary();
        }
        contain.summary();
        List<Item> a=o.searchPartial("bows");
        for(Item tmp : a)
        {
            tmp.print();
        }

    }
}
