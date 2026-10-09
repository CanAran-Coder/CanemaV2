package org.test.backend.service;

import org.test.backend.dto.MovieResponse;
import org.test.backend.dto.ShowTimeResponse;

import java.util.List;

public interface ShowTimeService {
    List<ShowTimeResponse> getShowTimesByDate(String date);

}
