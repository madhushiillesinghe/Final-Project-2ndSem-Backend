package lk.ijse.agriproject.finalproject2ndsem.embedded;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Embeddable
public class Name {
    private String first_name;
    private String last_name;
}
