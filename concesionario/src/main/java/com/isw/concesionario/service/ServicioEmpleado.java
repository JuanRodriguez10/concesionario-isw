package com.isw.concesionario.service;
import com.isw.concesionario.model.Concesionario;
import com.isw.concesionario.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicioEmpleado {
    private Set<Empleado> empleados;

    public ServicioEmpleado() {
        empleados = new HashSet<>();
    }

    public boolean crearEmpleado(String cedula, String nombre, String apellidoPaterno,
                                  String apellidoMaterno, String correo, String contrasenia,
                                  String telefono, Concesionario concesionario) {
        Empleado empleado = new Empleado(
                cedula,
                nombre,
                apellidoPaterno,
                apellidoMaterno,
                correo,
                contrasenia,
                telefono,
                concesionario
        );

        concesionario.agregarUsuario(empleado);

        return empleados.add(empleado);
    }

    public Empleado buscarEmpleado(String cedula) {
        return empleados.stream()
                .filter(e -> e.getCedula().equalsIgnoreCase(cedula))
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarEmpleado(String cedula) {
        Empleado empleado = buscarEmpleado(cedula);

        if (empleado == null) {
            return false;
        }

        empleado.setActivo(false);
        return true;
    }

    public boolean actualizarEmpleado(String cedula, String nombre,
                                       String apellidoPaterno, String apellidoMaterno, String correo,
                                       String contrasenia, String telefono,
                                       Concesionario concesionario)
    {
        Empleado empleado = buscarEmpleado(cedula);

        if(empleado == null)
        {
            return false;
        }

        empleado.setNombre(nombre);
        empleado.setApellidoPaterno(apellidoPaterno);
        empleado.setApellidoMaterno(apellidoMaterno);
        empleado.setCorreo(correo);
        empleado.setContrasenia(contrasenia);
        empleado.setTelefono(telefono);
        empleado.setConcesionario(concesionario);

        return true;
    }

    public Set<Empleado> buscarEmpleadosPorConcesionario(String nitConcesionario) {
        return empleados.stream()
                .filter(e -> e.getConcesionario().getNit().equalsIgnoreCase(nitConcesionario))
                .collect(Collectors.toSet());
    }
}
