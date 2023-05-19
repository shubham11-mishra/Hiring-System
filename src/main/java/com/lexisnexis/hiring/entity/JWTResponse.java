package com.lexisnexis.hiring.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTResponse {
    private String token;
    private String note;
}
