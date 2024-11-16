package com.isw.concesionario.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class Concesionario {
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;
    private boolean activo;

    private Set<Vehiculo> vehiculos;
    private Set<Usuario> usuarios;
    private Set<Factura> facturas;

    public Concesionario(String nit, String nombre, String direccion, String telefono) {
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.usuarios = new HashSet<>();
        this.vehiculos = new HashSet<>();
        this.facturas = new HashSet<>();
        activo = true;
    }

    public boolean agregarVehiculo(Vehiculo vehiculo)
    {
        return vehiculos.add(vehiculo);
    }

    public boolean agregarUsuario(Usuario usuario)
    {
        return usuarios.add(usuario);
    }

    public boolean agregarFactura(Factura factura)
    {
        return facturas.add(factura);
    }
}