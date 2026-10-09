package org.test.backend.dto;

import java.util.UUID;

public record MovieResponse(
        UUID id,

        String name,
        String description,
        Integer duration,
        String imageUrl
) {
}
