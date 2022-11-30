package org.ada.gestorgastronomico.entity;

import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;

import javax.persistence.*;

@Entity
@Table(name = "materia_prima")
public class MateriaPrima {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double stock;

    @Column(nullable = false)
    private Double precio;

    public MateriaPrima() {
    }

    public MateriaPrima( String nombre, double stock, Double precio) {
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


    public double getStock() {
        return stock;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void modifyAttributeValue(String nombreAtributo, Object nuevoValor) {
        switch (nombreAtributo) {
            case "nombre" :
                this.nombre = (String) nuevoValor;
                break;
            case "precio" :
                this.precio = (Double) nuevoValor;
                break;
            default:
                throw new ResourceNotFoundException("No se permite modificar un campo solicitado");
        }
    }

    public void incrementarStock(double cantidad) {
        stock+= cantidad;
    }

    public void decrementarStock(double cantidad) {
        stock-= cantidad;
    }

    public void cargarPrecio(double precioUnitario) {
        setPrecio(precioUnitario);
    }


}
