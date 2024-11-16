package com.isw.concesionario.model;
import lombok.*;

@Data
@NoArgsConstructor
public class Vehiculo {
    private String nombre;
    private String placa;
    private String kilometraje;
    private String modelo;
    private String combustible;
    private String color;
    private String marca;
    private String tipoVehiculo;
    private DetalleFactura detalleFactura;
    private boolean activo;

    private Concesionario concesionario;

    public Vehiculo(String nombre, String placa, String kilometraje, String modelo,
                    String combustible, String color, String marca, String tipoVehiculo, Concesionario concesionario) {
        this.nombre = nombre;
        this.placa = placa;
        this.kilometraje = kilometraje;
        this.modelo = modelo;
        this.combustible = combustible;
        this.color = color;
        this.marca = marca;
        this.tipoVehiculo = tipoVehiculo;
        activo = true;
        this.concesionario = concesionario;
    }
}
