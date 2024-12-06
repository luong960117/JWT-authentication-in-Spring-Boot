package com.docuement.Management.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class JSONResponse {
//    private Map<String, List<? extends IResponseDTO>> data = new HashMap<>();
    private ResponseStatus status;
    private String message;
}
