package com.isw.concesionario.service;

import com.isw.concesionario.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicioFactura {
    private Set<Factura> facturas;
    private int contador;

    public ServicioFactura()
    {
        facturas = new HashSet<>();
    }

    public boolean crearFactura(LocalDate fecha, boolean vendido, String nombreCliente,
                                String telefonoCliente, Empleado empleado, Set<DetalleFactura> detallesFactura, Concesionario concesionario) {

        Factura factura = new Factura(fecha, vendido, nombreCliente, telefonoCliente, empleado, detallesFactura, concesionario);

        concesionario.agregarFactura(factura);

        return facturas.add(factura);
    }

    public Set<Factura> darFacturasVenta(String nit)
    {
        return facturas.stream()
                .filter(f -> f.getConcesionario().getNit().equalsIgnoreCase(nit)
                && f.isVendido())
                .collect(Collectors.toSet());
    }

    public Set<Factura> darFacturasCompra(String nit)
    {
        return facturas.stream()
                .filter(f -> f.getConcesionario().getNit().equalsIgnoreCase(nit)
                        && !f.isVendido())
                .collect(Collectors.toSet());
    }

    public Set<Factura> darFacturasRangoVenta(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return facturas.stream()
                .filter(f -> nit == null || f.getConcesionario().getNit().equalsIgnoreCase(nit)
                        && f.isVendido())
                .filter(f -> (fechaInicio == null || !f.getFecha().isBefore(fechaInicio)) &&
                        (fechaFin == null || !f.getFecha().isAfter(fechaFin)))
                .collect(Collectors.toSet());
    }

    public Set<Factura> darFacturasRangoCompra(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return facturas.stream()
                .filter(f -> nit == null || f.getConcesionario().getNit().equalsIgnoreCase(nit)
                        && !f.isVendido())
                .filter(f -> (fechaInicio == null || !f.getFecha().isBefore(fechaInicio)) &&
                        (fechaFin == null || !f.getFecha().isAfter(fechaFin)))
                .collect(Collectors.toSet());
    }

    public double calcularPrecioTotalFacturasVenta(String nit) {
        return facturas.stream()
                .filter(f -> f.getConcesionario().getNit().equalsIgnoreCase(nit)
                        && !f.isVendido())
                .mapToDouble(Factura::getPrecio)
                .sum();
    }

    public double calcularPrecioTotalFacturasCompra(String nit) {
        return facturas.stream()
                .filter(f -> f.getConcesionario().getNit().equalsIgnoreCase(nit)
                        && !f.isVendido())
                .mapToDouble(Factura::getPrecio)
                .sum();
    }

    public double calcularPrecioTotalFacturasRangoVenta(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return facturas.stream()
                .filter(f -> nit == null || f.getConcesionario().getNit().equalsIgnoreCase(nit)
                        && f.isVendido())
                .filter(f -> (fechaInicio == null || !f.getFecha().isBefore(fechaInicio)) &&
                        (fechaFin == null || !f.getFecha().isAfter(fechaFin)))
                .mapToDouble(Factura::getPrecio)
                .sum();
    }

    public double calcularPrecioTotalFacturasRangoCompra(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return facturas.stream()
                .filter(f -> nit == null || f.getConcesionario().getNit().equalsIgnoreCase(nit)
                        && f.isVendido())
                .filter(f -> (fechaInicio == null || !f.getFecha().isBefore(fechaInicio)) &&
                        (fechaFin == null || !f.getFecha().isAfter(fechaFin)))
                .mapToDouble(Factura::getPrecio)
                .sum();
    }

    public double calcularGananciaTotalFacturasRango(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return calcularPrecioTotalFacturasRangoVenta(nit, fechaInicio, fechaFin) - calcularPrecioTotalFacturasRangoCompra(nit, fechaInicio, fechaFin);
    }

    public double calcularGananciasTotalFacturas(String nit) {
        return calcularPrecioTotalFacturasVenta(nit) - calcularPrecioTotalFacturasCompra(nit);
    }
}
