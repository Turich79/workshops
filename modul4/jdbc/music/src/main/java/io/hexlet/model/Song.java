package io.hexlet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// BEGIN (write your solution here)
@Getter
@Setter
@AllArgsConstructor
public class Song {
    private Long id;
    private String title;
    private String singer_name;
}
// END
