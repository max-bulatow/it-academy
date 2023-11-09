package by.itacademy.address;

import by.itacademy.BaseEntity;
import by.itacademy.school.School;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    @Column(name = "city", length = 30, nullable = false)
    private String city;
    @Column(name = "street", length = 50, nullable = false)
    private String street;
    @Column(name = "building_number", length = 10, nullable = false)
    private String buildingNumber;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<School> schools;

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(final String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(final List<School> schools) {
        this.schools = schools;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", schools=" + schools +
                '}';
    }
}
