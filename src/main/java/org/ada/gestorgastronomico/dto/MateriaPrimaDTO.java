package org.ada.gestorgastronomico.dto;


public class MateriaPrimaDTO {

    private int id;
    private String nombre;
    private double sotck;
    private double precio;

    public MateriaPrimaDTO( String nombre, double stock, double precio) {
        this.nombre = nombre;
        this.sotck = stock;
        this.precio = precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSotck() {
        return sotck;
    }

    public double getPrecio() {
        return precio;
    }
}
