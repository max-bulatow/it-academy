package by.itacademy.persistance.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Teacher information")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    @Schema(type = "int32", example = "1", description = "Teacher Id")
    private Integer id;

    @Schema(type = "string", example = "Ivan", description = "Teacher first name")
    @Pattern(regexp = "^[A-Z]{1}[a-z]+")
    private String firstName;

    @Schema(type = "string", example = "Ivan", description = "Teacher last name")
    @Pattern(regexp = "^[A-Z]{1}[a-z]+")
    private String lastName;
}
