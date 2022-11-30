package org.ada.gestorgastronomico.entity;

import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido_al_proveedor")
public class PedidoAlProveedor {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer numero;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(name = "monto_total", nullable = false)
    private Double montoTotal;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuit_proveedor", nullable = false)
    private Proveedor proveedor;

    @OneToMany(mappedBy = "pedidoAlProveedor", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<ItemPedido> items;

    public PedidoAlProveedor() {
    }

    public PedidoAlProveedor(String estado, Proveedor proveedor) {
        this.fecha = LocalDateTime.now();
        this.estado = estado;
        this.proveedor = proveedor;
        montoTotal = 0.0;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public List<ItemPedido> getItems() {
        if (items == null){
            items = new ArrayList<>();
        }
        return items;
    }

    public void modifyAttributeValue(String nombreAtributo, Object nuevoValor) {
        switch (nombreAtributo){
            case "fecha" :
                this.fecha = LocalDateTime.parse((String) nuevoValor, DATE_TIME_FORMATTER);
                break;
            case "estado" :
                this.estado = (String) nuevoValor;
                break;
            default:
                throw new ResourceNotFoundException("No se permite modificar un campo solicitado");
        }
    }
}
