package org.ada.gestorgastronomico.entity;

import javax.persistence.*;

@Entity
@Table(name = "detalle_ticket")
public class DetalleTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private double precioUnitario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "num_ticket", nullable = false)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_producto", nullable = false)
    private Producto producto;

    public DetalleTicket() {
    }

    public DetalleTicket(int cantidad, double precioUnitario, Ticket ticket, Producto producto) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.ticket = ticket;
        this.producto = producto;
    }
}
