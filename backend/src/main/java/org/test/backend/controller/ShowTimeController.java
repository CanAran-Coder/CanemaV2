package org.test.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.backend.dto.MovieResponse;
import org.test.backend.service.ShowTimeService;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowTimeController {

    private final ShowTimeService showTimeService;
    @GetMapping("/getMoviesByDate/{date}")
    public ResponseEntity<List<MovieResponse>> getMoviesByDate(@PathVariable("date") String date) {
        return ResponseEntity.ok(showTimeService.getMoviesByDate(date));
    }
}
