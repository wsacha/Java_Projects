public class Item implements Comparable<Item> {
    public String name;
    public double weight;
    ItemCondition condition;
    public int amount;

    public Item() {
    }

    ;

    public Item(String name, double weight, ItemCondition condition, int amount) {
        this.name = name;
        this.weight = weight;
        this.condition = condition;
        this.amount = amount;
    }

    ;

    public void print() {
        System.out.println("Towar: " + name + ", masa: " + weight + ", stan: " + condition + ", ilosc: " + amount);
    }

    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.name);
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setCondition(ItemCondition condition) {
        this.condition = condition;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getWeight() {
        return weight;
    }
}
