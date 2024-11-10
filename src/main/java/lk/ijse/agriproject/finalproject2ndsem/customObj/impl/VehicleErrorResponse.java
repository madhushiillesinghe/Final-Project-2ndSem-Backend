package lk.ijse.agriproject.finalproject2ndsem.customObj.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.VehicleResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleErrorResponse implements VehicleResponse, Serializable {
    private int errorcode;
    private String errormessage;
}
