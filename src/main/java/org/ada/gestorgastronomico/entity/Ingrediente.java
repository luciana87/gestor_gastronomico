package org.ada.gestorgastronomico.entity;

import javax.persistence.*;

@Entity
@Table(name = "ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double cantidad;

    @Column(nullable = false)
    private String unidadMedida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_producto", nullable = false)
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_materia_prima", nullable = false)
    private MateriaPrima materiaPrima;

    public Ingrediente() {
    }

    public Ingrediente(double cantidad, String unidadMedida, Producto producto, MateriaPrima materiaPrima) {
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.producto = producto;
        this.materiaPrima = materiaPrima;
    }

    public int getId() {
        return id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public Producto getProducto() {
        return producto;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }
}
