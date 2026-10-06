package org.test.backend.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.test.backend.entity.Hall;
import org.test.backend.entity.Movie;
import org.test.backend.entity.Showtime;
import org.test.backend.repository.HallRepository;
import org.test.backend.repository.MovieRepository;
import org.test.backend.repository.ShowtimeRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Seeders implements CommandLineRunner {

    private static final LocalTime[] SHOWTIME_HOURS = {
            LocalTime.of(14, 0),
            LocalTime.of(17, 0),
            LocalTime.of(20, 0)
    };

    private static final BigDecimal[] SHOWTIME_PRICES = {
            new BigDecimal("150.00"),
            new BigDecimal("180.00"),
            new BigDecimal("220.00")
    };

    private final HallRepository hallRepository;
    private final MovieRepository movieRepository;
    private final ShowtimeRepository showtimeRepository;

    @Override
    public void run(String... args) {
        try {
            seedHalls();
            seedMovies();
            seedShowtimes();
        } catch (Exception e) {
            log.error("Seed data could not be loaded", e);
        }
    }

    private void seedHalls() {
        saveHallIfMissing("hall1", 30);
        saveHallIfMissing("hall2", 60);
        saveHallIfMissing("hall3", 100);
    }

    private void saveHallIfMissing(String name, int seat) {
        if (hallRepository.existsByName(name)) {
            return;
        }
        hallRepository.save(Hall.builder()
                .name(name)
                .seat(seat)
                .build());
        log.info("Seeded hall {}", name);
    }

    private void seedMovies() {
        saveMovieIfMissing(
                "The Joker",
                "Arthur Fleck, a party clown and a failed stand-up comedian, leads an impoverished life with his ailing mother. However, when society shuns him and brands him as a freak, he decides to embrace the life of chaos in Gotham City.",
                122,
                "https://bskiacxbrwgninouhqxu.supabase.co/storage/v1/object/public/movies/thejoker.webp"
        );
        saveMovieIfMissing(
                "The Godfather",
                "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                175,
                "https://bskiacxbrwgninouhqxu.supabase.co/storage/v1/object/public/movies/godfather.webp"
        );
        saveMovieIfMissing(
                "The Pianist",
                "During WWII, acclaimed Polish musician Wladyslaw faces various struggles as he loses contact with his family. As the situation worsens, he hides in the ruins of Warsaw in order to survive.",
                150,
                "https://bskiacxbrwgninouhqxu.supabase.co/storage/v1/object/public/movies/pianist.webp"
        );
        saveMovieIfMissing(
                "The Shawshank Redemption",
                "After a banker is sentenced to life in Shawshank Prison, he forms an unlikely friendship with a seasoned inmate and clings to hope amid cruelty and corruption.",
                150,
                "https://bskiacxbrwgninouhqxu.supabase.co/storage/v1/object/public/movies/shawnshank.webp"
        );
    }

    private void saveMovieIfMissing(String name, String description, int duration, String imageUrl) {
        if (movieRepository.existsByName(name)) {
            return;
        }
        movieRepository.save(Movie.builder()
                .name(name)
                .description(description)
                .duration(duration)
                .imageUrl(imageUrl)
                .build());
        log.info("Seeded movie {}", name);
    }

    private void seedShowtimes() {
        List<Movie> movies = movieRepository.findAll();
        List<Hall> halls = hallRepository.findAll();
        if (movies.isEmpty() || halls.isEmpty()) {
            log.warn("Showtimes were not seeded because movies or halls are missing");
            return;
        }

        LocalDate today = LocalDate.now();
        for (Movie movie : movies) {
            for (int i = 0; i < halls.size(); i++) {
                LocalDateTime date = LocalDateTime.of(today, SHOWTIME_HOURS[i % SHOWTIME_HOURS.length]);
                saveShowtimeIfMissing(
                        movie,
                        halls.get(i),
                        date,
                        SHOWTIME_PRICES[i % SHOWTIME_PRICES.length]
                );
            }
        }
    }

    private void saveShowtimeIfMissing(Movie movie, Hall hall, LocalDateTime date, BigDecimal price) {
        if (showtimeRepository.existsByMovie_IdAndHall_IdAndDate(movie.getId(), hall.getId(), date)) {
            return;
        }
        showtimeRepository.save(Showtime.builder()
                .movie(movie)
                .hall(hall)
                .date(date)
                .price(price)
                .build());
        log.info("Seeded showtime for {} in {} at {}", movie.getName(), hall.getName(), date);
    }
}
