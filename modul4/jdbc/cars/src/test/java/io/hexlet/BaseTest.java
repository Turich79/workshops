package io.hexlet;

import io.hexlet.component.DataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BaseTest {
    @BeforeEach
    public void init() throws Exception {
        try (var conn = DriverManager.getConnection("jdbc:h2:./hexlet")) {
            DataInitializer.init(conn);
        }
    }

    @Test
    public void testBase() throws Exception {
        assertThat(Base.getInfo("123ab"))
                .contains("Ford").
                contains("E350")
                .contains("Raine")
                .contains("Wrennall")
                .contains("08 Arkansas Plaza");

        assertThat(Base.getInfo("nm759"))
                .contains("Ford").
                contains("Taurus")
                .contains("Sunshine")
                .contains("Fairbourn")
                .contains("48395 Graedel Parkway");

    }

    @Test
    public void testCarNotFound() throws Exception {
        assertThatThrownBy(() -> Base.getInfo("NotExisting"))
                .isInstanceOf(NoSuchElementException.class);
    }
}
