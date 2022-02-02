package com.example.signupsignin.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class  SignUpRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;

}
