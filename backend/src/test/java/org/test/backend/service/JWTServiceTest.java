package org.test.backend.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JWTServiceTest {

    private static final String SECRET = "0123456789abcdef0123456789abcdef";

    private final JWTService jwtService = new JWTService(SECRET, 60_000);

    @Test
    void writesAndReadsEmail() {
        String token = jwtService.generateToken("user@test.com");

        assertEquals("user@test.com", jwtService.extractEmail(token));
        assertNotNull(jwtService.extractExpiration(token));
        assertFalse(jwtService.isTokenExpired(token));
        assertTrue(jwtService.isTokenValid(token));
    }

    @Test
    void detectsExpiredToken() {
        JWTService expiredService = new JWTService(SECRET, -1_000);
        String token = expiredService.generateToken("user@test.com");

        assertEquals("user@test.com", expiredService.extractEmail(token));
        assertTrue(expiredService.isTokenExpired(token));
        assertFalse(expiredService.isTokenValid(token));
    }

    @Test
    void rejectsInvalidToken() {
        assertNull(jwtService.extractEmail("not-a-token"));
        assertNull(jwtService.extractExpiration("not-a-token"));
        assertTrue(jwtService.isTokenExpired("not-a-token"));
        assertFalse(jwtService.isTokenValid("not-a-token"));
    }
}
