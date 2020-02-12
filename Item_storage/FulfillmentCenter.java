import java.util.*;

public class FulfillmentCenter {
    String nameWarehouse;
    double maxWeight;
    double recentlyWeight = 0;
    List<Item> products = new ArrayList<>();
    public FulfillmentCenter(){
        this.nameWarehouse="None";
        this.maxWeight=50;
        this.recentlyWeight=1;
    }
    public FulfillmentCenter(String name,double weight)
    {
        this.nameWarehouse=name;
        this.maxWeight=weight;
    }

    public void addProduct(Item item) {
        boolean isntThere = true;

        if ((recentlyWeight + item.getAmount() * item.getWeight()) < maxWeight) {
            recentlyWeight =recentlyWeight+ item.getAmount() * item.getWeight();
            for (Item tmp : products) {
                if (tmp.getName() == item.getName()) {
                    tmp.amount = tmp.amount + item.amount;
                    isntThere = false;
                    break;
                }
            }
            if (isntThere)
                products.add(item);
        } else
            System.err.println("za duza waga");
    }

    public void getProduct(Item item) {
        boolean isntThere = true;
        for (Item tmp : products) {
            if (0 == tmp.compareTo(item)) {
                isntThere = false;
                if (tmp.getAmount() != 1) {
                    tmp.amount--;
                    recentlyWeight = recentlyWeight - tmp.weight;
                    System.out.println("Zmniejszam ilosc produktu o 1");
                    break;
                } else {
                    recentlyWeight = recentlyWeight - tmp.weight;
                    products.remove(tmp);
                    System.out.println("Usuwam produkt");
                    break;
                }

            }
        }
        if (isntThere == true)
            System.out.println("nie bylo produktu");
    }


    public void removeProduct(Item item) {
        boolean isntThere = true;
        for (Item tmp : products) {
            if (tmp.compareTo(item) == 0) {
                isntThere = false;
                recentlyWeight = recentlyWeight - (tmp.getAmount() * tmp.getWeight());
                products.remove(tmp);
                break;
            }
        }
        if (isntThere) System.out.println("Nie ma danego produktu");
        else System.out.println("Usunieto produkt");
    }


    public Item search(String name) {
        Item tmp= new Item(name,0,ItemCondition.NEW,0);
        int index = Collections.binarySearch(products, tmp);
        return products.get(index);

    }


    public List<Item> searchPartial(String namePart) {

        List<Item> similarProducts = new ArrayList<Item>();
        for (Item i : products) {
            /*
            if (0 <= i.compareTo(new Item(namePart,0, ItemCondition.NEW, 0))) {
                similarProducts.add(i);
            }*/
            if(i.name.contains(namePart))
                similarProducts.add(i);
        }
        return similarProducts;
    }


    public int countByCondition(ItemCondition cond) {

        int n = 0;
        for(Item i: products) {
            if(i.condition==cond) {
                n+=i.amount;
            }
        }
        return n;
    }


    public void summary() {
        System.out.println("\nNazwa: " + nameWarehouse + ", Zapelnienie: " + 100*recentlyWeight/maxWeight + "% Max: " + maxWeight + " Rec: " + recentlyWeight);
        for (Item tmp : products)
            tmp.print();
    }


    public List<Item> sortByName() {
        List<Item> nameSort = new ArrayList<Item>();
        for(Item i: products) {
            nameSort.add(i);
        }
        Collections.sort(nameSort);
        return nameSort;
    }


    public List<Item> sortByAmount() {
        List<Item> amountSort = new ArrayList<Item>();
        for(Item i: products) {
            amountSort.add(i);
        }
        amountSort.sort(new ItemAmountComparator());
        return amountSort;
    }

    public Item max() {
        return Collections.max(products,new ItemAmountComparator());
    }

}
