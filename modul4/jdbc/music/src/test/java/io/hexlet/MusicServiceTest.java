package io.hexlet;

import io.hexlet.component.DataInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.DriverManager;

public class MusicServiceTest {
    @BeforeEach
    public void init() throws Exception {
        try (var conn = DriverManager.getConnection("jdbc:h2:./hexlet")) {
            DataInitializer.init(conn);
        }
    }

    @Test
    public void testMusicService1() throws Exception {
        var condition = "are";

        var actual = MusicService.getSongByTitle(condition);
        assertThat(actual).hasSize(2);

        var song1 = actual.getFirst();
        assertThat(song1.getTitle()).isEqualTo("They Are With Me");

        var song2 = actual.get(1);
        assertThat(song2.getTitle()).isEqualTo("You are Not Like That");
    }

    @Test
    public void testMusicService2() throws Exception {
        var condition = "st";

        var actual = MusicService.getSongByTitle(condition);
        assertThat(actual).hasSize(2);

        var song1 = actual.getFirst();
        assertThat(song1.getTitle()).isEqualTo("Starry Rain");

        var song2 = actual.get(1);
        assertThat(song2.getTitle()).isEqualTo("The Best Day");
    }

    @Test
    public void testMusicService3() throws Exception {
        var condition = "NotExists";

        var actual = MusicService.getSongByTitle(condition);
        assertThat(actual).isEmpty();
    }
}
