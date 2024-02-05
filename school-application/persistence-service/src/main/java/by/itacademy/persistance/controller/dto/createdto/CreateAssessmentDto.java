package by.itacademy.persistance.controller.dto.createdto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "Assessment information")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAssessmentDto {

    @Schema(type = "int32", example = "8", description = "Assessment")
    @Min(value = 0)
    @Max(value = 10)
    private Integer assessment;
}
