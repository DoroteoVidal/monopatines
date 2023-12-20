package com.tpe.usuarios.DTO;

import com.tpe.usuarios.model.Usuario;
import lombok.Data;

import java.io.Serializable;

@Data
public class DTOUsuario implements Serializable {
    private Long id;
    private String email;
    private Long telefono;
    private String nombre;
    private String apellido;
    private Long idCuenta;
    private String rol;
    private String password;

    public DTOUsuario() {}

    public DTOUsuario(Usuario u) {
        this.id = u.getId();
        this.email = u.getEmail();
        this.telefono = u.getTelefono();
        this.nombre = u.getNombre();
        this.apellido = u.getApellido();
        this.idCuenta = u.getIdCuenta().getId();
        this.rol = u.getRol().getTipo();
        this.password = u.getPassword();
    }

}
