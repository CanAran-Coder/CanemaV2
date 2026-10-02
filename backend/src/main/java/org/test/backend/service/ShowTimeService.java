package org.test.backend.service;

import org.test.backend.dto.MovieResponse;

import java.util.List;

public interface ShowTimeService {
    List<MovieResponse> getMoviesByDate(String date);

}
