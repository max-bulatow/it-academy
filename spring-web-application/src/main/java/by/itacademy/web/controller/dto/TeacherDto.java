package by.itacademy.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TeacherDto {

    @JsonProperty(value = "teacher id")
    private final Integer id;

    @JsonProperty(value = "teacher first name")
    private final String firstName;

    @JsonProperty(value = "teacher last name")
    private final String lastName;

    public TeacherDto(final Integer id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
