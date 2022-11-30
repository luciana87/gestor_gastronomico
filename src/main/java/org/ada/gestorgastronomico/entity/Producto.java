package org.ada.gestorgastronomico.entity;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double precio;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Ingrediente> ingredientes;

    public Producto() {
    }

    public Producto(String nombre) { //TODO: modificar el código, no va porque se autoincrementa
        this.nombre = nombre;
        this.precio = 0.0;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }

    public List<Ingrediente> getIngredientes() {
        if (ingredientes == null){
            ingredientes = new ArrayList<>();
        }
        return ingredientes;
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
                throw new ResourceNotFoundException("No se permite modificar el campo solicitado");
        }
    }
}
