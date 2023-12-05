package com.pinarsoft.example.controller.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Este objeto define lo que se necesita para logear (Proceso de obtener un token) */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	String email;
	String password;

	public String getUsername() {
		return email;
	}
}
