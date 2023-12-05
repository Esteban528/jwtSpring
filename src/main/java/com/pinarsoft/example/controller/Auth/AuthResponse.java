package com.pinarsoft.example.controller.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Respuesta de autenticación
 * Guardará un token
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
	String token;
}
