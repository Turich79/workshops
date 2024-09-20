package io.hexlet.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String first_name;
    private String second_name;
    private String adress;

    public User(String first_name, String second_name, String adress) {
        this.first_name = first_name;
        this.second_name = second_name;
        this.adress = adress;
    }
}
