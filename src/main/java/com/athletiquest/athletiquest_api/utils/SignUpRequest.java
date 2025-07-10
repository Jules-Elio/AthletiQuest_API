package com.athletiquest.athletiquest_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String username;

    private String email;

    private Set<String> roles;

    private String password;

    public boolean isValid() {
        return username != null &&
               !username.isEmpty() &&
               !username.isBlank() &&
               email != null &&
               !email.isEmpty() &&
               !email.isBlank() &&
               password != null &&
               !password.isEmpty() &&
               !password.isBlank();
    }

}
