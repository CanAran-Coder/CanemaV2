package org.test.backend.dto;

import java.util.UUID;

public record SeatLockRequest(UUID seanceId, Integer seatNumber, UUID userId) {
}
