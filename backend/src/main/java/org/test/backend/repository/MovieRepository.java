package org.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.backend.entity.Movie;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    boolean existsByName(String name);
}
