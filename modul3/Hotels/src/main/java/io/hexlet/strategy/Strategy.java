package io.hexlet.strategy;
import java.util.Map;

public interface Strategy {
    Map<String, Object> convert(Map<String, Object> hotel);
}
