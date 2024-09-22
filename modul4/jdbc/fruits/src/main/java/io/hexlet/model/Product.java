package io.hexlet.model;

// BEGIN (write your solution here)
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private Integer price;
}
// END
