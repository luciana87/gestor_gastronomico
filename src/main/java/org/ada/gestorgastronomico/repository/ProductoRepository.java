package org.ada.gestorgastronomico.repository;

import org.ada.gestorgastronomico.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
}
