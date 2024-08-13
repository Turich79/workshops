package io.hexlet.strategy;
import java.util.Map;
import java.util.HashMap;

public final class KayakStrategy implements Strategy {
    public Map<String, Object> convert(Map<String, Object> hotel) {
        return new HashMap(hotel);
    }
}
