package models;
import java.util.*;
class Bootstrap {
    public static List<Map<String, Object>> sample(List<Map<String, Object>> data, Random rand) {
        List<Map<String, Object>> sample = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            int k = rand.nextInt(data.size());
            sample.add(data.get(k));
        }
        return sample;
    }
}