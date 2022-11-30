package org.ada.gestorgastronomico.repository;

import org.ada.gestorgastronomico.entity.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
}
