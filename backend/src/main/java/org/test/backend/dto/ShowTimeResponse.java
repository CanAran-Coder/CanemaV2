package org.test.backend.dto;

import org.test.backend.entity.Hall;
import org.test.backend.entity.Movie;

import java.math.BigDecimal;
import java.util.UUID;

public record ShowTimeResponse(Movie movie, Hall hall, UUID showTimeId, BigDecimal price) {

}
