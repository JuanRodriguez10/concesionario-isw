package com.isw.concesionario.service;

import com.isw.concesionario.model.DetalleFactura;
import com.isw.concesionario.model.Vehiculo;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServicioDetalleFactura {
    private Set<DetalleFactura> detallesFactura;
    private ServicioVehiculo servicioVehiculo;

    public ServicioDetalleFactura(ServicioVehiculo servicioVehiculo)
    {
        detallesFactura = new HashSet<DetalleFactura>();
        this.servicioVehiculo = servicioVehiculo;
    }

    public boolean crearDetalleFactura(long precio, Vehiculo vehiculo) {

        DetalleFactura detalleFactura = new DetalleFactura(precio, vehiculo);

        return detallesFactura.add(detalleFactura);
    }

    public DetalleFactura buscarDetalleFactura(String placaVehiculo) {
        return detallesFactura.stream()
                .filter(e -> e.getVehiculo().getPlaca().equalsIgnoreCase(placaVehiculo) && e.isActivo())
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarDetalleFactura(String placaVehiculo) {

        DetalleFactura detalleFactura = buscarDetalleFactura(placaVehiculo);

        if (detalleFactura == null) {
            return false;
        }

        detalleFactura.setActivo(false);
        return true;
    }

    public boolean actualizarDetalleFactura(long precio, String placaNueva, String placa, String nit) {

        DetalleFactura detalleFactura = buscarDetalleFactura(placa);

        if(detalleFactura == null) return false;

        Vehiculo vehiculo = servicioVehiculo.buscarVehiculo(placa, nit);
        Vehiculo vehiculoNuevo = servicioVehiculo.buscarVehiculo(placaNueva, nit);

        if(vehiculoNuevo == null) return false;

        vehiculo.setDetalleFactura(null);
        vehiculoNuevo.setDetalleFactura(detalleFactura);

        detalleFactura.setPrecioUnitario(precio);
        detalleFactura.setVehiculo(vehiculoNuevo);

        return true;
    }
}
