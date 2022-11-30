package org.ada.gestorgastronomico.entity;

import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int cantidadComensales;

    @Column(nullable = false)
    private String estado;

    private String mesero;

    @OneToMany(mappedBy = "mesa")
    private List<Ticket> tickets;

    public Mesa(int cantidadComensales, String estado, String mesero, List<Ticket> tickets) {
        this.cantidadComensales = cantidadComensales;
        this.estado = estado;
        this.mesero = mesero;
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public int getCantidadComensales() {
        return cantidadComensales;
    }

    public String getEstado() {
        return estado;
    }

    public String getMesero() {
        return mesero;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
