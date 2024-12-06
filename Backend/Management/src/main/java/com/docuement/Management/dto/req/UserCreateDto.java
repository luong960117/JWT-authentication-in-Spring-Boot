package com.docuement.Management.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {
    private String username;
    private String password;
    private String re_password;
    private String name;
    private String email;
}
