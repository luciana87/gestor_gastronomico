package org.ada.gestorgastronomico.dto;

import java.util.List;

public class ProductoDTO {

    private Integer codigo;
    private String nombre;
    private double precio;
    private List<IngredienteDTO> ingredientes;

    public ProductoDTO() {
    }

    public ProductoDTO(String nombre, double precio, List<IngredienteDTO> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.ingredientes = ingredientes;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public List<IngredienteDTO> getIngredientes() {
        return ingredientes;
    }
}
