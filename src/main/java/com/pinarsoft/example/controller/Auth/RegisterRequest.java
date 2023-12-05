package com.pinarsoft.example.controller.Auth;

import com.pinarsoft.example.service.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Este objeto define lo que se necesita para logear (Proceso de obtener un token) */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	String nombre;
    String apellido;
    String email;
    String password;
    String telefono;
    Role role;
}
