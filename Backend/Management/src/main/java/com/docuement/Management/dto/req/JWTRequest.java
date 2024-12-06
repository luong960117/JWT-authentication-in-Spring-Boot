package com.docuement.Management.dto.req;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JWTRequest {
    private String username;
    private String password;
}
