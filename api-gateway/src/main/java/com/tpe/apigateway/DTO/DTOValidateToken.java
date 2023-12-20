package com.tpe.apigateway.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DTOValidateToken {

    private boolean isAuthenticated;
    private String username;
    private String authority;

}
