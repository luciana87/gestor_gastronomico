package org.ada.gestorgastronomico.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public class IngredienteDTO {

    private Integer id;
    private double cantidad;

    @JsonAlias("unidad_medida")
    private String unidadMedida;

    @JsonAlias("codigo_producto")
    private Integer codigoProducto;

    private String nombreProducto;

    @JsonAlias("id_materia_prima")
    private Integer idMateriaPrima;

    private String nombreMateriaPrima;

    public IngredienteDTO() {
    }

    public IngredienteDTO( double cantidad, String unidadMedida, Integer codigoProducto,
                          String nombreProducto, Integer idMateriaPrima, String nombreMateriaPrima) {
        this.cantidad = cantidad;
        this.unidadMedida = unidadMedida;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.idMateriaPrima = idMateriaPrima;
        this.nombreMateriaPrima = nombreMateriaPrima;
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

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public Integer getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public Integer getIdMateriaPrima() {
        return idMateriaPrima;
    }

    public String getNombreMateriaPrima() {
        return nombreMateriaPrima;
    }
}
