package com.isw.concesionario.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class Empleado extends Usuario {
    private List<Factura> facturas = new ArrayList<>();

    public Empleado(String cedula, String nombre, String apellidoPaterno,
                    String apellidoMaterno, String correo, String contrasenia,
                    String telefono, Concesionario concesionario) {
        super(cedula, nombre, apellidoPaterno, apellidoMaterno, correo,
                contrasenia, telefono, concesionario);
        this.facturas = new ArrayList<>();
    }
}
