package io.hexlet;

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
            return mapper.readValue(content, new TypeReference<List<Map<String, Object>>>() { });
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    // BEGIN (write your solution here)

    // END
}

