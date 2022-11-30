package org.ada.gestorgastronomico.repository;

import org.ada.gestorgastronomico.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository <Ticket, Integer> {
}
