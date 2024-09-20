package io.hexlet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {
    private Long id;
    private String made;
    private String model;
    private String number;
    private String color;
    private User owner;

    public Car(String made, String model, String number, String color) {
        this.made = made;
        this.model = model;
        this.number = number;
        this.color = color;
    }
}
