package io.hexlet;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Пустой список на входе
        Map<Integer, Map<Integer, Double>> result1 = calculateProbabilities(new ArrayList<Integer>());
        System.out.println(result1); // => {}

        Map<Integer, Map<Integer, Double>> result2 = calculateProbabilities(
                List.of(1, 3, 1, 5, 1, 2, 1, 6)
        );
        System.out.println(result2);
        // => {1={2=0.25, 3=0.25, 5=0.25, 6=0.25}, 2={1=1.0}, 3={1=1.0}, 5={1=1.0}, 6={}}

    }

    public static Map<Integer, Map<Integer, Double>> calculateProbabilities(List<Integer> numbers) {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        if (numbers.isEmpty()) {
            return map;
        }

        var set = numbers.stream().collect(Collectors.toSet());
        for (var st : set) {
            Map<Integer, Double> mp = new HashMap<>();
            for (int i = 1; i < numbers.size(); i++) {
                var befNum = numbers.get(i - 1);
                var tekNum = numbers.get(i);
                if (st == befNum) {
                    var count = mp.getOrDefault(tekNum, 0.0);
                    mp.put(tekNum, ++count);
                }
            }
            map.put(st, mp);
        }

        var entries = map.entrySet();
        for (var entry : entries) {
            var mp = entry.getValue();
            var entries2 = mp.entrySet();
            var count = 0;
            for (var entry2 : entries2) {
                count+=entry2.getValue();
            }
            for (var entry2 : entries2) {
                var tekValue=entry2.getValue();
                entry2.setValue(tekValue/count);
            }
        }
        return map;
    }

    ////ВАРИАНТ ОТ HEXLET
    private static long countElements(List<Integer> elements, int element) {
        return elements.stream()
                .filter(currentElement -> currentElement == element)
                .count();
    }

    private static Map<Integer, Double> findProbabilityForElement(List<Integer> elements, int element) {
        List<Integer> filtered = IntStream.range(1, elements.size())
                .filter(index -> elements.get(index - 1) == element)
                .mapToObj(i -> elements.get(i))
                .toList();
        return filtered.stream()
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),
                        current ->  (double) countElements(filtered, current) / filtered.size()
                ));
    }

    public static Map<Integer, Map<Integer, Double>> calculateProbabilities2(List<Integer> droppedNumbers) {
        return droppedNumbers.stream()
                .distinct()
                .collect(Collectors.toMap(
                        Function.identity(),
                        current -> findProbabilityForElement(droppedNumbers, current)
                ));
    }
}