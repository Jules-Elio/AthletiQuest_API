package com.athletiquest.athletiquest_api.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String email;

    private String password;

    public boolean isValid() {
        return email != null &&
               !email.isEmpty() &&
               !email.isBlank() &&
               password != null &&
               !password.isEmpty() &&
               !password.isBlank();
    }
}
