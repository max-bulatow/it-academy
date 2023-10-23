package by.itacademy.model.transport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModelTypeTest {

    @ParameterizedTest
    @MethodSource("getModelTypesWithExpectedIdsAndNames")
    void testGetIdGetName_happyPath(final ModelType model, final Integer id, final String name) {
        assertEquals(id, model.getId());
        assertEquals(name, model.getName());
    }

    @Test
    void testValues_happyPath() {
        assertEquals(6, ModelType.values().length);
    }

    private static Stream<Arguments> getModelTypesWithExpectedIdsAndNames() {
        return Stream.of(
                Arguments.of(ModelType.TRANSPORTER_T5, 1, "TRANSPORTER T5"),
                Arguments.of(ModelType.SPRINTER_264, 2, "SPRINTER 264"),
                Arguments.of(ModelType.NINJA_ZX14, 3, "NINJA ZX14"),
                Arguments.of(ModelType.MAZDA_CX7, 4, "MAZDA CX7"),
                Arguments.of(ModelType.BMW_M5, 5, "BMW M5"),
                Arguments.of(ModelType.AUDI_Q7, 6, "AUDI Q7")
        );
    }

}