package com.tpe.usuarios.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long telefono;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "id_cuenta")
    private Cuenta idCuenta;

    @ManyToOne
    @JoinColumn(name = "rol")
    private Rol rol;

    @Column
    private String password;

    public Usuario() {}

    public Usuario(String email, Long telefono, String nombre, 
    String apellido, Cuenta idCuenta, Rol rol, String password) {
        this.email = email;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idCuenta = idCuenta;
        this.rol = rol;
        this.password = password;
    }

}
