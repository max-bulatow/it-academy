package by.itacademy.persistance.controller.dto.createdto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Subject information")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubjectDto {

    @Schema(type = "string", example = "Java Core", description = "Subject name")
    private String name;
}
