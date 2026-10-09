package org.test.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.backend.dto.MovieResponse;
import org.test.backend.dto.ShowTimeResponse;
import org.test.backend.entity.Movie;
import org.test.backend.entity.Showtime;
import org.test.backend.repository.ShowtimeRepository;
import org.test.backend.service.ShowTimeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<ShowTimeResponse> getShowTimesByDate(String date) {
        LocalDate localdate = LocalDate.parse(date);
        List<ShowTimeResponse> seance = showtimeRepository.findShowtimeDetailsByDate(localdate);
        return seance;
    }
}
