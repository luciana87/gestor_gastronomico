package org.ada.gestorgastronomico.service;

import org.ada.gestorgastronomico.dto.TicketDTO;
import org.ada.gestorgastronomico.entity.Mesa;
import org.ada.gestorgastronomico.entity.Ticket;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.ada.gestorgastronomico.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final MesaService mesaService;

    public TicketService(TicketRepository ticketRepository, MesaService mesaService) {
        this.ticketRepository = ticketRepository;
        this.mesaService = mesaService;
    }
/*
    public TicketDTO create(int mesaId, TicketDTO ticketDTO) {
        Optional<Mesa> mesa = mesaService.findById(mesaId);
        if (mesa.isEmpty() && mesa.get().getEstado() == "Disponible"){   //Verifico si me devolvió una mesa vacía
            throw new ResourceNotFoundException("La mesa no existe.");
        }


        TicketDTO ticket = mapToEntity(ticketDTO);

        return ticket;
    }

    /*private TicketDTO mapToEntity(TicketDTO ticketDTO) { // Terminar
        Ticket ticket = new Ticket(ticketDTO.);
        return null;
    }

    private void checkForExistingTicket(Integer materiaPrimaId)  {
        if (!ticketRepository.existsById(materiaPrimaId)) {
            throw new ResourceNotFoundException("La materia prima no existe."); //TODO:excepciones
        }
    }*/
}
