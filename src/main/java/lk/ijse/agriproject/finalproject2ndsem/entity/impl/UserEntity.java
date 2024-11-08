package lk.ijse.agriproject.finalproject2ndsem.entity.impl;
import jakarta.persistence.*;
import lk.ijse.agriproject.finalproject.entity.SuperEntity;
import lk.ijse.agriproject.finalproject2ndsem.embedded.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")

public class UserEntity implements SuperEntity {
    @Id
    private String email;
    private String password;
    private Role role;
}
