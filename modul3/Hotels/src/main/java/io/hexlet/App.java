package io.hexlet;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

class App {
    // BEGIN (write your solution here)
    public static Map<String, Object> findTheCheapestService(Map<String, Integer> predicates) {
        var gateway = new Gateway();
        double max = 99999999;
        List<Map<String, Object>> list = gateway.findAll(predicates);
        Map<String, Object> minOffert = new HashMap<>();

        for (var tekStr : list) {
            var serviceName = tekStr.get("service");
            Map<String, Object> hotel = (Map<String, Object>) tekStr.get("hotel");
            double tekCost = (double) hotel.get("cost");
            if (tekCost < max) {
                max = tekCost;
                minOffert.put("hotel", hotel);
                minOffert.put("service", serviceName);
            }
        }
        return minOffert;
    }

    public static Map<String, Object> findTheCheapestService() {
        Map<String, Integer> predicates = new HashMap<>();
        return findTheCheapestService(predicates);
    }
    // END
}
