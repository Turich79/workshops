package io.hexlet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class App {
    public static Map<String, String> genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, String> result = new LinkedHashMap<>();

        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());

        for (String key : keysSet) {
            System.out.println(key);

            var hasOne = map1.containsKey(key);
            var hasTwo = map2.containsKey(key);
            if (hasOne && !hasTwo) {
                result.put(key, "deleted");
            } else if (!hasOne && hasTwo) {
                result.put(key, "added");
            } else {
                if (!map1.get(key).equals(map2.get(key))) {
                    result.put(key, "changed");
                } else {
                    result.put(key, "unchanged");
                }
            }
        }
        return result;
    }
}
