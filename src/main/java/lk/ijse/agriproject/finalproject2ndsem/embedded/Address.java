package lk.ijse.agriproject.finalproject2ndsem.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data

@Embeddable

public class Address {
    private String roadNo;
    private String street;
    private String city;
    private String district;
    private String province;

}
