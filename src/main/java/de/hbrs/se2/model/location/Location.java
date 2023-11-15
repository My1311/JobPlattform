package de.hbrs.se2.model.location;

import de.hbrs.se2.model.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Builder
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Location", schema = "public")
public class Location extends BaseEntity {


    @Builder.Default
    @Column(name = "street")
    private String street = "";

    @Builder.Default
    @Column(name="house_number")
    private String house_number = "";

    @Builder.Default
    @Column(name="zip_code")
    private String zip_code = "";

    @Builder.Default
    @Column(name="city")
    private String city = "";

    @Builder.Default
    @Column(name="country")
    private String country = "";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Location location = (Location) o;

        if (!Objects.equals(street, location.street)) return false;
        if (!Objects.equals(house_number, location.house_number))
            return false;
        if (!Objects.equals(zip_code, location.zip_code)) return false;
        if (!Objects.equals(city, location.city)) return false;
        return Objects.equals(country, location.country);
    }


}