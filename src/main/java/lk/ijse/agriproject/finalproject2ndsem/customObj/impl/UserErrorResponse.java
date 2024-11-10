package lk.ijse.agriproject.finalproject2ndsem.customObj.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserErrorResponse implements UserResponse, Serializable {
    private int errorcode;
    private String errormessage;
}
