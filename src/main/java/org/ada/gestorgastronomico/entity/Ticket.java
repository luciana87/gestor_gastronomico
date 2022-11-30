package org.ada.gestorgastronomico.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int num;

    @Column(name = "monto_total", nullable = false)
    private double montoTotal;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER)
    private List<DetalleTicket> detallesTicket;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_mesa", nullable = false)
    private Mesa mesa;

    public Ticket() {
    }

    public Ticket(List<DetalleTicket> detallesTicket, Mesa mesa) {
        this.montoTotal = 0;
        this.fecha = LocalDateTime.now();
        this.detallesTicket = detallesTicket;
        this.mesa = mesa;
    }

    public int getNum() {
        return num;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public List<DetalleTicket> getDetallesTicket() {
        return detallesTicket;
    }
}
