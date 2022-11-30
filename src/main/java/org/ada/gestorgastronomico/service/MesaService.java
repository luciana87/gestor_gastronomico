package org.ada.gestorgastronomico.service;

import org.ada.gestorgastronomico.entity.Mesa;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.ada.gestorgastronomico.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }


    public void checkForExistingMesa(int mesaId) {
        if (!mesaRepository.existsById(mesaId)) {
            throw new ResourceNotFoundException("La mesa no existe."); //TODO:excepciones
        }
    }

    public Optional<Mesa> findById(int mesaId) {
        return mesaRepository.findById(mesaId);
    }
}
