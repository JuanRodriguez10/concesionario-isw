package com.isw.concesionario.model;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Usuario {
    private long Id;
    private String cedula;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contrasenia;
    private String telefono;
    private boolean activo;

    private Concesionario concesionario;


    public Usuario(String cedula, String nombre, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, Concesionario concesionario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.concesionario = concesionario;
        activo = true;
    }
}
