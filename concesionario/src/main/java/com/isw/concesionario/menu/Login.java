package com.isw.concesionario.menu;

import com.isw.concesionario.model.Administrativo;
import com.isw.concesionario.model.Empleado;
import com.isw.concesionario.service.*;

public class Login {
    private ServicioEmpleado servicioEmpleado;
    private ServicioAdministrativo servicioAdministrativo;

    public Login(ServicioEmpleado servicioEmpleado, ServicioAdministrativo servicioAdministrativo,
                 ServicioConcesionario servicioConcesionario, ServicioVehiculo servicioVehiculo,
                 ServicioDetalleFactura servicioDetalleFactura, ServicioFactura servicioFactura) {
        this.servicioEmpleado = servicioEmpleado;
        this.servicioAdministrativo = servicioAdministrativo;
    }

    public Controller login(String usuario, String contrasenia) {
        Empleado empleado = servicioEmpleado.buscarEmpleado(usuario);

        if(empleado != null && contrasenia.equalsIgnoreCase(empleado.getContrasenia()))
        {
            return new ControllerEmpleado(empleado);
        }
        else {
            Administrativo administrativo = servicioAdministrativo.buscarAdministrativo(usuario);

            if(administrativo != null && contrasenia.equalsIgnoreCase(administrativo.getContrasenia()))
            {
                return new ControllerAdministrativo(administrativo);
            }
            else {
                System.out.println("Login incorrecto");
            }
        }

        return null;
    }
}
