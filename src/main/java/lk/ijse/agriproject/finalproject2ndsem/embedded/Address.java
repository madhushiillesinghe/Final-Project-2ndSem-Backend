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
    private String line_1;
    private String line_2;
    private String line_3;
    private String line_4;
    private String line_5;

}
