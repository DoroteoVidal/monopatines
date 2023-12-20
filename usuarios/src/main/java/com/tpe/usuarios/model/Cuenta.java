package com.tpe.usuarios.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity( name = "cuentas")
@Data
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaDeAlta;

    @Column(nullable = false)
    private Float saldo;

    @Column(nullable = false)
    private String estado;

    @OneToMany(mappedBy = "idCuenta", cascade = CascadeType.MERGE)
    private List<Usuario> usuarios;

    public Cuenta() {}

    public Cuenta(LocalDate fechaDeAlta, Float saldo, String estado) {
        this.fechaDeAlta = fechaDeAlta;
        this.saldo = saldo;
        this.estado = estado;
    }

}


