package org.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.test.backend.dto.ShowTimeResponse;
import org.test.backend.entity.Movie;
import org.test.backend.entity.Showtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowtimeRepository extends JpaRepository<Showtime, UUID> {

    boolean existsByMovie_IdAndHall_IdAndDate(UUID movieId, UUID hallId, LocalDateTime date);
    @Query(value = """
        select distinct m.*
        from movies m
        join showtimes s on s.movie_id = m.id
        where cast(s."date" as date) = cast(:date as date)
        """, nativeQuery = true)
    List<Movie> getMoviesByDate(@Param("date") String date);


    @Query("SELECT NEW org.test.backend.dto.ShowTimeResponse(m, h, s.id, s.price) " +
            "FROM Showtime s " +
            "JOIN s.movie m " +
            "JOIN s.hall h " +
            "WHERE CAST(s.date AS LocalDate) = :targetDate")
    List<ShowTimeResponse> findShowtimeDetailsByDate(@Param("targetDate") LocalDate targetDate);
}
