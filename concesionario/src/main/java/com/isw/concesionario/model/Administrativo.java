package com.isw.concesionario.model;
import com.isw.concesionario.service.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
public class Administrativo extends Usuario {
    private String codigoAdministrador;

    private ServicioEmpleado servicioEmpleado;
    private ServicioAdministrativo servicioAdministrativo;
    private ServicioConcesionario servicioConcesionario;
    private ServicioVehiculo servicioVehiculo;
    private ServicioDetalleFactura servicioDetalleFactura;
    private ServicioFactura servicioFactura;

    public Administrativo(String codigoAdministrador, String cedula, String nombre,
                          String apellidoPaterno, String apellidoMaterno, String correo,
                          String contrasenia, String telefono, Concesionario concesionario) {
        super(cedula, nombre, apellidoPaterno, apellidoMaterno, correo,
                contrasenia, telefono, concesionario);
        this.codigoAdministrador = codigoAdministrador;
    }
}
