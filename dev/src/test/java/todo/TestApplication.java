package todo; // Motsvarar din package i main

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest // Laddar hela Spring Boot-applikationskontexten
class ApplicationTest {

    @Test
    @DisplayName("Spring-kontexten ska laddas korrekt")
    void contextLoads() {
        // Om testet körs utan att kasta ett exception, betyder det att kontexten laddades korrekt
        // Inget att asserta här specifikt, testet lyckas om det inte kraschar.
    }
}
