package io.hexlet;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        ////функциональный интерфейс PREDICATE
        ////проверяет какое-либо условие и возвращает True или False
        Predicate<Integer> isEvenNumber = x -> x % 2 == 0;
        System.out.println("Predicate: " + isEvenNumber.test(2));
        System.out.println("Predicate: " + isEvenNumber.test(5));

        ////функциональный интерфейс CONSUMER
        ////принимает какой то аргумент, совершает действия и ничего не выводит
        Consumer<String> greeting = x -> System.out.println("Hello " + x + " !!!");
        greeting.accept("Consumer: " + "Vasya");

        ////функциональный интерфейс SUPPLIER
        ////ничего не принимает, но возращает некоторый объект типа Т
        ArrayList<String> nameList = new ArrayList<>();
        nameList.add("Elena");
        nameList.add("John");
        nameList.add("Alex");
        nameList.add("Jim");
        nameList.add("Sara");

        Supplier<String> randomName = () -> {
            int value = (int) (Math.random() * nameList.size());
            return nameList.get(value);
        };

        System.out.println("Supplier: " + randomName.get());

        ////функциональный интерфейс FUNCTION
        ////принимает аргумент Т и приводит его к объекту типа R, который возвращается как результат
        Function<String, Integer> valueConverter = x -> Integer.valueOf(x);
        System.out.println("Function: " + valueConverter.apply("678"));

        ////функциональный интерфейс UNARYOPERATOR
        ////принимает в качестве параметра объект Т, выполняет над ним некоторые операции и возращает результат
        ////так же в виде объекта типа Т
        UnaryOperator<Integer> squareValue = x -> x * x;
        System.out.println("UnaryOperator: " + squareValue.apply(9));

        ////Stream c Predicate
        List<Integer> evenNumbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8)
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Stream-Predicate: " + evenNumbers);

        ////Stream c Consumer
        List<String> peopleGreetings = Stream.of("Elena", "John", "Alex", "Jim", "Sara")
                .peek(x -> System.out.println("Hello " + x + " !!!"))
                .collect(Collectors.toList());
        System.out.println("Stream-Consumer: " + peopleGreetings);

        ////Stream c Supplier
        ArrayList<String> nameList2 = new ArrayList<>();
        nameList2.add("Elena");
        nameList2.add("John");
        nameList2.add("Alex");
        nameList2.add("Jim");
        nameList2.add("Sara");

        Stream.generate(() -> {
            int value = (int) (Math.random() * nameList2.size());
            return nameList2.get(value);
        }).limit(10).forEach(System.out::println);

        ////Stream c Function
        List<Integer> values = Stream.of("32", "43", "74", "54", "3")
                .map(x -> Integer.valueOf(x)).collect(Collectors.toList());
        System.out.println("Stream-Function: " + values);

        ////Stream c UnaryOperator
        Stream.iterate(9, x -> x * x)
                .limit(4)
                .forEach(System.out::println);
    }
}