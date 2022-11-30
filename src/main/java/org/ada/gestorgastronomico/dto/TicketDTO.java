package org.ada.gestorgastronomico.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class TicketDTO {

    private Integer num;

    @JsonAlias("monto_total")
    private double montoTotal;

    private String fecha;

    private List<DetalleTicketDTO> detalles;

    public TicketDTO(double montoTotal, String fecha, List<DetalleTicketDTO> detalles) {
        this.montoTotal = montoTotal;
        this.fecha = fecha;
        this.detalles = detalles;
    }

    public Integer getNum() {
        return num;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public String getFecha() {
        return fecha;
    }
}
