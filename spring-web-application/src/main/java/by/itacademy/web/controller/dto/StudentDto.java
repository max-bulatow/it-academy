package by.itacademy.web.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StudentDto {

    @JsonProperty(value = "student id")
    private final Integer id;

    @JsonProperty(value = "student first name")
    private final String firstName;

    @JsonProperty(value = "student last name")
    private final String lastName;

    public StudentDto(final Integer id, final String firstName, final String lastName) {
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
