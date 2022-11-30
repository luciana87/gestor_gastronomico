package org.ada.gestorgastronomico.entity;

import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proveedor")
public class Proveedor {

    @Id
    private String cuit;

    @Column (nullable = false)
    private String nombre;

    private String email;
    private String telefono;
    private String direccion;

    @OneToMany(mappedBy = "proveedor", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<PedidoAlProveedor> pedidos;


    public Proveedor() {
    }

    public Proveedor(String cuit, String nombre, String email, String telefono, String direccion) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<PedidoAlProveedor> getPedidos() {
        if (pedidos == null){
            pedidos = new ArrayList<>();
        }
        return pedidos;
    }

    public void modifyAttributeValue(String nombreAtributo, Object nuevoValor) {
        switch (nombreAtributo) {
            case "nombre" :
                this.nombre = (String) nuevoValor;
                break;
            case "email" :
                this.email = (String) nuevoValor;
                break;
            case "telefono" :
                this.telefono = (String) nuevoValor;
                break;
            case "direccion" :
                this.direccion = (String) nuevoValor;
                break;
            default:
                throw new ResourceNotFoundException("No se permite modificar un campo solicitado");
        }
    }
}
