package io.hexlet;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import io.hexlet.strategy.Strategy;
import io.hexlet.strategy.OstrovokStrategy;
import io.hexlet.strategy.BookingStrategy;
import io.hexlet.strategy.KayakStrategy;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


public final class Gateway {

    private List<Map<String, Object>> hotelsByService = getData("data.json");

    private static List<Map<String, Object>> getData(String fileName) {
        Path filePath = Paths.get("src", "main", "resources", fileName).toAbsolutePath().normalize();
        ObjectMapper mapper = new ObjectMapper();
        try {
            var content = Files.readString(filePath).trim();
            return mapper.readValue(content, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // BEGIN (write your solution here)
    public List<Map<String, Object>> findAll(Map<String, Integer> predicates) {
        double min = 0;
        double max = 999999999;
        Strategy ostrovok = new OstrovokStrategy();
        Strategy kayak = new KayakStrategy();
        Strategy booking = new BookingStrategy();
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        if (predicates.containsKey("min")) {
            min = predicates.get("min");
        }
        if (predicates.containsKey("max")) {
            max = predicates.get("max");
        }
        for (var service : this.hotelsByService) {
            var serviceName = service.get("service");
            List<Map> entries = (List<Map>) service.get("hotels");
            for (var entry : entries) {
                if (serviceName.equals("ostrovok")) {
                    map = ostrovok.convert(entry);
                } else if (serviceName.equals("booking")) {
                    map = booking.convert(entry);
                } else {
                    map = kayak.convert(entry);
                }
                double tekCost = (double) map.get("cost");
                if (tekCost >= min && tekCost <= max) {
                    Map<String, Object> offer = new HashMap<>();
                    offer.put("hotel", map);
                    offer.put("service", serviceName);
                    list.add(offer);
                }
            }
        }
        return list;
    }

    // END
}

