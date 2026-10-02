package org.test.backend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.test.backend.dto.MovieResponse;
import org.test.backend.entity.Movie;
import org.test.backend.repository.ShowtimeRepository;
import org.test.backend.service.ShowTimeService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowtimeRepository showtimeRepository;

    @Override
    public List<MovieResponse> getMoviesByDate(String date) {
        List<Movie> movies = showtimeRepository.getMoviesByDate(date);
        List<MovieResponse> movieResponseList = new ArrayList<>();
        for (Movie movie:movies){
            MovieResponse response = new MovieResponse(movie.getId(),movie.getName(),movie.getDescription(),movie.getDuration(),movie.getImageUrl());
            movieResponseList.add(response);
        }
        return  movieResponseList;
    }
}
