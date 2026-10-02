package org.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.backend.entity.Hall;

import java.util.UUID;

public interface HallRepository extends JpaRepository<Hall, UUID> {

    boolean existsByName(String name);
}
