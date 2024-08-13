package io.hexlet.strategy;
import java.util.Map;

public final class OstrovokStrategy implements Strategy {
    public Map<String, Object> convert(Map<String, Object> hotel) {
        double ostrovokFee = 0.12; // Plus 12 percents

        return Map.of(
                "name", hotel.get("name"),
                "cost", (double) hotel.get("cost") + (double) hotel.get("cost") * ostrovokFee
        );
    }
}
