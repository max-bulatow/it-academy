package by.itacademy.persistance.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Schema(description = "Error information")
@Getter
@AllArgsConstructor
public class ErrorDto {

    @Schema(type = "string", example = "123e4567-e89b-42d3-a456-556642440000")
    private UUID errorId;

    @Schema(type = "string", example = "error message")
    private String message;
}
