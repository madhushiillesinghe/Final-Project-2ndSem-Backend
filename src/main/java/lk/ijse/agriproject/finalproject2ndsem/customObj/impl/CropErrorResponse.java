package lk.ijse.agriproject.finalproject2ndsem.customObj.impl;

import lk.ijse.agriproject.finalproject2ndsem.customObj.CropResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CropErrorResponse implements CropResponse, Serializable {
    private int errorcode;
    private String errormessage;
}
