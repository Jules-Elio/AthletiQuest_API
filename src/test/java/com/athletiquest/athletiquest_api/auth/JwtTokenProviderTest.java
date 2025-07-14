package com.athletiquest.athletiquest_api.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {


    @Test
    void testTokenGeneration() {
        JwtProvider jwtProvider = new JwtProvider();
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getName()).thenReturn("athletiquest@mail.com");

        String token = jwtProvider.generateToken(authentication);
        assertNotNull(token);
        assertEquals("athletiquest@mail.test", jwtProvider.getUserEmail(token));
    }

}
