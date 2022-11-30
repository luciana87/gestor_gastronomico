package org.ada.gestorgastronomico.repository;

import org.ada.gestorgastronomico.entity.PedidoAlProveedor;
import org.ada.gestorgastronomico.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProveedorRepository extends JpaRepository<Proveedor,String> {

}
