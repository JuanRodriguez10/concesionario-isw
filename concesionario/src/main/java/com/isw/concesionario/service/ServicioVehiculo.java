package com.isw.concesionario.service;

import com.isw.concesionario.model.Concesionario;
import com.isw.concesionario.model.DetalleFactura;
import com.isw.concesionario.model.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicioVehiculo {

    private Set<Vehiculo> vehiculos;

    public ServicioVehiculo()
    {
        vehiculos = new HashSet<>();
    }

    // Dar vehiculos

    public Set<Vehiculo> darVehiculos(String nitSede)
    {
        return vehiculos.stream()
                .filter(v -> v.getConcesionario().getNit().equalsIgnoreCase(nitSede)
                        && v.isActivo())
                .collect(Collectors.toSet());
    }

    public Set<Vehiculo> darVehiculosNoVendidos(String nitSede)
    {
        return vehiculos.stream()
                .filter(v -> v.getConcesionario().getNit().equalsIgnoreCase(nitSede)
                        && v.getDetalleFactura() == null)
                .collect(Collectors.toSet());
    }

    public Set<Vehiculo> darVehiculosVendidos(String nitSede)
    {
        return vehiculos.stream()
                .filter(v -> v.getConcesionario().getNit().equalsIgnoreCase(nitSede)
                        && v.getDetalleFactura() != null)
                .collect(Collectors.toSet());
    }

    public boolean crearVehiculo(String nombre, String placa, String kilometraje, String modelo,
                                 String combustible, String color, String marca, String tipoVehiculo,
                                 Concesionario concesionario) {

        Vehiculo vehiculo = new Vehiculo(nombre, placa, kilometraje, modelo,
                combustible, color, marca, tipoVehiculo, concesionario);
        concesionario.agregarVehiculo(vehiculo);

        return vehiculos.add(vehiculo);
    }

    public Vehiculo buscarVehiculo(String placa, String nit) {
        return vehiculos.stream()
                .filter(e -> e.getConcesionario().getNit().equalsIgnoreCase(nit) && e.getPlaca().equalsIgnoreCase(placa) && e.isActivo())
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarVehiculo(String placa, String nit) {

        Vehiculo vehiculo = buscarVehiculo(placa, nit);

        if (vehiculo == null) {
            return false;
        }

        vehiculo.setActivo(false);
        return true;
    }

    public boolean actualizarVehiculo(String nombre, String placa, String kilometraje, String modelo,
                                      String combustible, String color, String marca, String tipoVehiculo, String nit) {

        Vehiculo vehiculo = buscarVehiculo(placa, nit);

        if(vehiculo == null) return false;

        vehiculo.setNombre(nombre);
        vehiculo.setPlaca(placa);
        vehiculo.setKilometraje(kilometraje);
        vehiculo.setModelo(modelo);
        vehiculo.setCombustible(combustible);
        vehiculo.setColor(color);
        vehiculo.setMarca(marca);
        vehiculo.setTipoVehiculo(tipoVehiculo);

        return true;
    }

    public boolean vender(String placa, DetalleFactura detalleFactura, String nit)
    {
        Vehiculo vehiculo = buscarVehiculo(placa, nit);

        if(vehiculo == null) return false;

        vehiculo.setDetalleFactura(detalleFactura);

        return true;
    }
}
