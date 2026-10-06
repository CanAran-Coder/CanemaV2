package org.test.backend.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.backend.dto.MovieResponse;
import org.test.backend.entity.Movie;
import org.test.backend.repository.ShowtimeRepository;
import org.test.backend.service.impl.ShowTimeServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShowTimeImplTest {

    @Mock
    private ShowtimeRepository showtimeRepository;

    @InjectMocks
    private ShowTimeServiceImpl showTimeService;

    @Test
    void shouldReturnMoviesByDate(){
        String date = "2026-10-5";

        List<Movie> movieList = new ArrayList<>();
        Movie movie = new Movie();
        movie.setName("test1");
        Movie movie2 = new Movie();
        movie2.setName("test2");
        movieList.add(movie);
        movieList.add(movie2);

        when(showtimeRepository.getMoviesByDate(date)).thenReturn(movieList);


        var result = showTimeService.getMoviesByDate(date);

        assertFalse(result.isEmpty());
        assertNotNull(result);
        assertEquals(2, result.size());

        verify(showtimeRepository,times(1)).getMoviesByDate(date);


    }

}
