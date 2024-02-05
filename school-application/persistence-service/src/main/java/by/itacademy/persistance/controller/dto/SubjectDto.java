package by.itacademy.persistance.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Subject information")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDto {

    @Schema(type = "int32", example = "1", description = "Subject Id")
    private Integer id;

    @Schema(type = "string", example = "Java Core", description = "Subject name")
    private String name;
}
