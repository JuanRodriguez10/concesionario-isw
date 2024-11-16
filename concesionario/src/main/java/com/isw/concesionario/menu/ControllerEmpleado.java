package com.isw.concesionario.menu;

import com.isw.concesionario.model.*;
import com.isw.concesionario.service.*;

import java.time.LocalDate;
import java.util.Set;

public class ControllerEmpleado implements Controller {
    private Empleado empleado;

    private ServicioEmpleado servicioEmpleado;
    private ServicioAdministrativo servicioAdministrativo;
    private ServicioConcesionario servicioConcesionario;
    private ServicioVehiculo servicioVehiculo;
    private ServicioDetalleFactura servicioDetalleFactura;
    private ServicioFactura servicioFactura;

    public ControllerEmpleado(Empleado empleado)
    {
        this.empleado = empleado;
    }

    public void recibirServicios(
            ServicioEmpleado servicioEmpleado,
            ServicioAdministrativo servicioAdministrativo,
            ServicioConcesionario servicioConcesionario,
            ServicioVehiculo servicioVehiculo,
            ServicioDetalleFactura servicioDetalleFactura,
            ServicioFactura servicioFactura
    ) {
        this.servicioEmpleado = servicioEmpleado;
        this.servicioAdministrativo = servicioAdministrativo;
        this.servicioConcesionario = servicioConcesionario;
        this.servicioVehiculo = servicioVehiculo;
        this.servicioDetalleFactura = servicioDetalleFactura;
        this.servicioFactura = servicioFactura;
    }

    // Empleado ------------------------------------------------


    public boolean crearEmpleado(String cedula, String nombre, String apellidoPaterno,
                                 String apellidoMaterno, String correo, String contrasenia,
                                 String telefono, Concesionario concesionario)
    {
        return servicioEmpleado.crearEmpleado(cedula, nombre, apellidoPaterno, apellidoMaterno, correo,
                contrasenia, telefono, concesionario);
    }

    public Empleado buscarEmpleado(String cedula)
    {
        return servicioEmpleado.buscarEmpleado(cedula);
    }

    public boolean eliminarEmpleado(String cedula)
    {
        return servicioEmpleado.eliminarEmpleado(cedula);
    }

    public boolean actualizarEmpleado(String cedulaAntigua, String cedula, String nombre, String apellidoPaterno,
                                      String apellidoMaterno, String correo, String contrasenia,
                                      String telefono, Concesionario concesionario)
    {
        return servicioEmpleado.actualizarEmpleado(cedula, nombre, apellidoPaterno, apellidoMaterno, correo,
                contrasenia, telefono, concesionario);
    }


    // Vehiculo ------------------------------------------------


    public Set<Vehiculo> obtenerVehiculos()
    {
        return servicioVehiculo.darVehiculos(empleado.getConcesionario().getNit());
    }


    public Set<Vehiculo> darVehiculosNoVendidos()
    {
        return servicioVehiculo.darVehiculosNoVendidos(empleado.getConcesionario().getNit());
    }

    public Set<Vehiculo> darVehiculosVendidos()
    {
        return servicioVehiculo.darVehiculosVendidos(empleado.getConcesionario().getNit());
    }

    public Vehiculo buscarVehiculo(String placa) {
        return servicioVehiculo.buscarVehiculo(placa, empleado.getConcesionario().getNit());
    }

    public boolean vender(String placa, DetalleFactura detalleFactura)
    {
        return servicioVehiculo.vender(placa, detalleFactura, empleado.getConcesionario().getNit());
    }


    // DetalleFactura ------------------------------------------------


    public boolean crearDetalleFactura(long precio, Vehiculo vehiculo) {

        return servicioDetalleFactura.crearDetalleFactura(precio, vehiculo);
    }

    public DetalleFactura buscarDetalleFactura(String placaVehiculo) {
        return servicioDetalleFactura.buscarDetalleFactura(placaVehiculo);
    }

    public boolean eliminarDetalleFactura(String placaVehiculo) {

        return servicioDetalleFactura.eliminarDetalleFactura(placaVehiculo);
    }

    public boolean actualizarDetalleFactura(long precio, String placaNueva, String placa, String nit) {

        return servicioDetalleFactura.actualizarDetalleFactura(precio, placaNueva, placa, empleado.getConcesionario().getNit());
    }


    // Factura ------------------------------------------------


    public boolean crearFactura(LocalDate fecha, boolean vendido, String nombreCliente,
                                String telefonoCliente, Empleado empleado, Set<DetalleFactura> detallesFactura) {

        return servicioFactura.crearFactura(fecha, vendido, nombreCliente, telefonoCliente, empleado, detallesFactura, this.empleado.getConcesionario());
    }

    public Set<Factura> darFacturasVenta(String nit)
    {
        return servicioFactura.darFacturasVenta(empleado.getConcesionario().getNit());
    }

    public Set<Factura> darFacturasCompra(String nit)
    {
        return servicioFactura.darFacturasCompra(nit);
    }

    public Set<Factura> darFacturasRangoVenta(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return servicioFactura.darFacturasRangoVenta(nit, fechaInicio, fechaFin);
    }

    public Set<Factura> darFacturasRangoCompra(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return servicioFactura.darFacturasRangoCompra(nit, fechaInicio, fechaFin);
    }

    public double calcularPrecioTotalFacturasVenta(String nit) {
        return servicioFactura.calcularPrecioTotalFacturasVenta(nit);
    }

    public double calcularPrecioTotalFacturasCompra(String nit) {
        return servicioFactura.calcularPrecioTotalFacturasCompra(nit);
    }

    public double calcularPrecioTotalFacturasRangoVenta(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return servicioFactura.calcularPrecioTotalFacturasRangoVenta(nit, fechaInicio, fechaFin);
    }

    public double calcularPrecioTotalFacturasRangoCompra(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return servicioFactura.calcularPrecioTotalFacturasRangoCompra(nit, fechaInicio, fechaFin);
    }

    public double calcularGananciaTotalFacturasRango(String nit, LocalDate fechaInicio, LocalDate fechaFin) {
        return servicioFactura.calcularGananciaTotalFacturasRango(nit, fechaInicio, fechaFin);
    }

    public double calcularGananciasTotalFacturas(String nit) {
        return servicioFactura.calcularGananciasTotalFacturas(nit);
    }
}
