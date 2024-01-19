package by.itacademy.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssessmentDto {

    @JsonProperty(value = "assessment id")
    private final Integer id;

    @JsonProperty(value = "assessment")
    private final Integer assessment;


    public AssessmentDto(final Integer id, final Integer assessment) {
        this.id = id;
        this.assessment = assessment;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAssessment() {
        return assessment;
    }
}
