package org.ada.gestorgastronomico.repository;

import org.ada.gestorgastronomico.entity.MateriaPrima;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaPrimaRepository extends JpaRepository<MateriaPrima, Integer> {

    boolean existsByNombre(String nombre);
}
