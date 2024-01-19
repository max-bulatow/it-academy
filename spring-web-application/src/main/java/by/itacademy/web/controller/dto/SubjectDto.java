package by.itacademy.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubjectDto {

    @JsonProperty(value = "subject id")
    private final Integer id;

    @JsonProperty(value = "subject name")
    private final String name;

    public SubjectDto(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
