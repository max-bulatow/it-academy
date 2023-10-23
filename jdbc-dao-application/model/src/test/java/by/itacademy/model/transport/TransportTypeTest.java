package by.itacademy.model.transport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransportTypeTest {

    @ParameterizedTest
    @MethodSource("getTransportTypesWithExpectedIdsAndNames")
    void testGetIdGetName_happyPath(final TransportType type, final Integer id, final String name) {
        assertEquals(id, type.getId());
        assertEquals(name, type.getName());
    }

    @Test
    void testValues_happyPath() {
        assertEquals(3, TransportType.values().length);
    }

    private static Stream<Arguments> getTransportTypesWithExpectedIdsAndNames() {
        return Stream.of(
                Arguments.of(TransportType.AUTOMOBILE, 1, "Automobile"),
                Arguments.of(TransportType.MOTORCYCLE, 2, "Motorcycle"),
                Arguments.of(TransportType.MINIBUS, 3, "Minibus")
        );
    }

}