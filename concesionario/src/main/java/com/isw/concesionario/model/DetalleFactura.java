package com.isw.concesionario.model;

import lombok.*;

@Data
@NoArgsConstructor
public class DetalleFactura {
    private long precioUnitario;
    private Vehiculo vehiculo;
    public boolean activo;

    private Factura factura;

    public DetalleFactura(long precioUnitario, Vehiculo vehiculo)
    {
        this.precioUnitario = precioUnitario;
        this.vehiculo = vehiculo;
        activo = true;
    }
}