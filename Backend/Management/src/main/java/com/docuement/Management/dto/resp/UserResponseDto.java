package com.docuement.Management.dto.resp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private String username;
    private String email;
    private String name;
    private long  id;
}
