package by.itacademy.persistance.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Student group information")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGroupDto {

    @Schema(type = "int32", example = "1", description = "Student group Id")
    private Integer id;

    @Schema(type = "string", example = "10A", description = "Student group name")
    @Pattern(regexp = "^\\d{1,2}[a-zA-Z]{1}$")
    private String name;

}
