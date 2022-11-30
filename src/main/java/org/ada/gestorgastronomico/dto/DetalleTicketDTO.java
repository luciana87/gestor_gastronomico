package org.ada.gestorgastronomico.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class DetalleTicketDTO {

    private  Integer id;
    private Integer cantidad;

    @JsonAlias("precio_unitario")
    private Double precioUnitario;

    @JsonAlias("ticket_id")
    private Integer ticketId;

    @JsonAlias("codigo_producto")
    private Integer codigoProducto;

    public DetalleTicketDTO(Integer cantidad, Double precioUnitario, Integer ticketId, Integer codigoProducto) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.ticketId = ticketId;
        this.codigoProducto = codigoProducto;
    }
}
