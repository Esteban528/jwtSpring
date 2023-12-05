package com.pinarsoft.example.controller.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinarsoft.example.service.AuthService;

import lombok.RequiredArgsConstructor;

/* Controlador encargado del login y el registro
 * Este contrador esta configurado como público
 */

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@PostMapping(value = "login")
	public ResponseEntity<AuthResponse> login (@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping(value = "register")
	public ResponseEntity<AuthResponse> register (@RequestBody RegisterRequest request) {
		return ResponseEntity.ok(authService.register(request));
	}
}
