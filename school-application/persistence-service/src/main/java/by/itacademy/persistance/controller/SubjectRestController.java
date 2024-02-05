package by.itacademy.persistance.controller;

import by.itacademy.persistance.controller.dto.createdto.CreateSubjectDto;
import by.itacademy.persistance.controller.dto.ErrorDto;
import by.itacademy.persistance.controller.dto.SubjectDto;
import by.itacademy.persistance.service.SubjectService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "subject", description = "Subject management APIs")
@Validated
@RestController
@RequestMapping("/subjects")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SubjectRestController {

    private final SubjectService subjectService;

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = SubjectDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "500", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            })
    })
    @Parameters(@Parameter(name = "id", required = true, description = "Subject Id", example = "1"))
    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SubjectDto getByID(@Min(value = 1, message = "Id must be more than 0") final Integer id) {
        return subjectService.findById(id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = SubjectDto.class)),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "500", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            })
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubjectDto> getALl() {
        return subjectService.getAll();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(
                            schema = @Schema(implementation = SubjectDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "500", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            })
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer save(@RequestBody CreateSubjectDto dto) {
        return subjectService.save(dto);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = SubjectDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "500", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            })
    })
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters(@Parameter(name = "id", required = true, description = "Subject Id", example = "1"))
    public void delete(final Integer id) {
        subjectService.delete(id);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            schema = @Schema(implementation = SubjectDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "400", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "404", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            }),
            @ApiResponse(responseCode = "500", content = {
                    @Content(
                            schema = @Schema(implementation = ErrorDto.class),
                            mediaType = "application/json"
                    )
            })
    })
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer update(@RequestBody SubjectDto dto) {
        return subjectService.update(dto);
    }
}
