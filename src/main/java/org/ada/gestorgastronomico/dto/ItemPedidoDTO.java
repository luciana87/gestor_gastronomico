package org.ada.gestorgastronomico.dto;

import com.fasterxml.jackson.annotation.JsonAlias;


public class ItemPedidoDTO {


    private Integer id;
    private double cantidad;

    @JsonAlias("precio_unitario")
    private double precioUnitario;

    @JsonAlias("numero_pedido")
    private Integer numeroPedido;

    @JsonAlias("materia_prima_id")
    private Integer materiaPrimaId;

    @JsonAlias("nombre_materia_prima")
    private String nombreMateriaPrima;

    public ItemPedidoDTO() {
    }

    public ItemPedidoDTO(double cantidad, double precioUnitario) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public ItemPedidoDTO(Integer numeroPedido, Integer id, double cantidad, double precioUnitario, String nombreMateriaPrima, Integer materiaPrimaId) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.numeroPedido = numeroPedido;
        this.nombreMateriaPrima = nombreMateriaPrima;
        this.materiaPrimaId = materiaPrimaId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public double getCantidad() {
        return cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public Integer getNumeroPedido() {
        return numeroPedido;
    }

    public Integer getMateriaPrimaId() {
        return materiaPrimaId;
    }

    public String getNombreMateriaPrima() {
        return nombreMateriaPrima;
    }
}
