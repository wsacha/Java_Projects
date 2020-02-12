import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FulfillmentCenterContainer {
    Map<String, FulfillmentCenter> centers = new HashMap<>();

    public void addCenter(String name, double weight) {
        centers.put(name, new FulfillmentCenter(name, weight));
    }

    public void addCenter(FulfillmentCenter tmp) {
        centers.put(tmp.nameWarehouse, tmp);
    }

    public void removeCenter(String name) {
        centers.remove(name);
    }

    public List<FulfillmentCenter> findEmpty() {
        List<FulfillmentCenter> emptyCenters = new ArrayList<>();
        for (String key : centers.keySet()) {
            FulfillmentCenter tmp = centers.get(key);
            if (tmp.products.isEmpty()) emptyCenters.add(tmp);
        }
        return emptyCenters;
    }
    public void summary(){
        System.out.println("Magazyny:");
        for(String tmp : centers.keySet())
            centers.get(tmp).summary();
    }
}
