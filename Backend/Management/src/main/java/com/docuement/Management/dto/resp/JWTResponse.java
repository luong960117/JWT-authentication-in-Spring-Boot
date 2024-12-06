package com.docuement.Management.dto.resp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JWTResponse {
    private String token;
}
