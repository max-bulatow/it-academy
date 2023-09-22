package by.itacademy.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TransportTypeTest {

    @Test
    void testValues_happyPath() {
        final var expectedTransportTypeNames = Arrays.asList("MOTORCYCLE", "AUTOMOBILE", "MINIBUS");

        final var actualTransportTypeNames = Stream.of(TransportType.values())
                .map(TransportType::name)
                .toList();

        assertEquals(expectedTransportTypeNames, actualTransportTypeNames);

    }
}