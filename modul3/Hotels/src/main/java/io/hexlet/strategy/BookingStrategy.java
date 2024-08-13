package io.hexlet.strategy;
import java.util.Map;

public final class BookingStrategy implements Strategy {
    public Map<String, Object> convert(Map<String, Object> hotel) {
        double bookingConvertRate = 75; // Booking exchange rate

        return Map.of(
                "name", hotel.get("name"),
                "cost", (double) hotel.get("cost") * bookingConvertRate
        );
    }
}
