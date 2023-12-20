package com.tpe.usuarios.DTO;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DTOAuth {
    private String email;
    private String password;
}
