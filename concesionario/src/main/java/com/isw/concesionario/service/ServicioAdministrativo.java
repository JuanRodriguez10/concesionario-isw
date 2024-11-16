package com.isw.concesionario.service;
import com.isw.concesionario.model.Administrativo;
import com.isw.concesionario.model.Concesionario;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ServicioAdministrativo {
    private Set<Administrativo> administrativos;

    public ServicioAdministrativo()
    {
        administrativos = new HashSet<>();
    }

    public boolean crearAdministrativo(String codigoAdministrador,
                                       String cedula, String nombre, String apellidoPaterno,
                                       String apellidoMaterno, String correo, String contrasenia,
                                       String telefono, Concesionario concesionario) {

        Administrativo administrativo = new Administrativo(codigoAdministrador, cedula, nombre,
                apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, concesionario);

        concesionario.agregarUsuario(administrativo);

        return administrativos.add(administrativo);
    }

    public Administrativo buscarAdministrativo(String codigoAdministrativo) {
        return administrativos.stream()
                .filter(e -> e.getCodigoAdministrador().equalsIgnoreCase(codigoAdministrativo) && e.isActivo())
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarAdministrativo(String codigoAdministrativo) {

        Administrativo administrativo = buscarAdministrativo(codigoAdministrativo);

        if (administrativo == null) {
            return false;
        }

        administrativo.setActivo(false);
        return true;
    }

    public boolean actualizarAdministrativo(String codigoAdministrador,
                                            String nombre, String apellidoPaterno,
                                            String apellidoMaterno, String correo,
                                            String contrasenia, String telefono) {

        Administrativo administrativo = buscarAdministrativo(codigoAdministrador);

        if(administrativo == null) return false;

        administrativo.setNombre(nombre);
        administrativo.setApellidoPaterno(apellidoPaterno);
        administrativo.setApellidoMaterno(apellidoMaterno);
        administrativo.setCorreo(correo);
        administrativo.setContrasenia(contrasenia);
        administrativo.setTelefono(telefono);

        return true;
    }
}