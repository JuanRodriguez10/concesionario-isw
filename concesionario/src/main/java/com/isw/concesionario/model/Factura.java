package com.isw.concesionario.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class Factura {
    private long id;

    private LocalDate fecha;

    private long precio;
    private boolean vendido;
    private String nombreCliente;
    private String telefonoCliente;
    public boolean activo;

    private Empleado empleado;

    private Set<DetalleFactura> detallesFactura;

    private Concesionario concesionario;

    public Factura(LocalDate fecha, boolean vendido, String nombreCliente, String telefonoCliente, Empleado empleado, Set<DetalleFactura> detallesFactura, Concesionario concesionario)
    {
        this.fecha = fecha;
        this.vendido = vendido;
        this.telefonoCliente = telefonoCliente;
        this.empleado = empleado;
        this.detallesFactura = detallesFactura;
        this.concesionario = concesionario;
        activo = true;
    }
}