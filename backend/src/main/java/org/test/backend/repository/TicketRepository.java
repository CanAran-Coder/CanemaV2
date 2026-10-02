package org.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.test.backend.entity.Ticket;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
