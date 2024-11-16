package com.isw.concesionario.service;

import com.isw.concesionario.model.Concesionario;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicioConcesionario {
    private Set<Concesionario> concesionarios;

    public ServicioConcesionario()
    {
        concesionarios = new HashSet<>();
    }

    public Set<Concesionario> darConcesionarios()
    {
        return concesionarios.stream().filter(Concesionario::isActivo)
                .collect(Collectors.toSet());
    }

    public boolean crearConcesionario(String nit, String nombre, String direccion, String telefono)
    {
        Concesionario concesionario = new Concesionario(nit,nombre,direccion,telefono);
        return concesionarios.add(concesionario);
    }

    public Concesionario buscarConcesionario(String nit)
    {
        return concesionarios.stream()
                .filter(e -> e.getNit().equalsIgnoreCase(nit))
                .findFirst()
                .orElse(null);
    }

    public boolean actualizarConcesionario(String nit, String nombre, String direccion, String telefono)
    {
        Concesionario concesionario = buscarConcesionario(nit);

        if (concesionario == null) {
            return false;
        }

        concesionario.setNombre(nombre);
        concesionario.setDireccion(direccion);
        concesionario.setTelefono(telefono);

        return true;
    }

    public boolean eliminarConcesionario(String nit) {
        Concesionario concesionario = buscarConcesionario(nit);

        if (concesionario == null) {
            return false;
        }

        concesionario.setActivo(false);
        return true;
    }
}
