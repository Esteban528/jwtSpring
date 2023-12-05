package com.pinarsoft.example.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pinarsoft.example.controller.Auth.AuthResponse;
import com.pinarsoft.example.controller.Auth.LoginRequest;
import com.pinarsoft.example.controller.Auth.RegisterRequest;
import com.pinarsoft.example.entity.UsuarioEntity;
import com.pinarsoft.example.repository.UsuarioRepository;
import com.pinarsoft.example.security.JwtService;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final UsuarioRepository userRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

	// Método para gestionar el proceso de inicio de sesión
	public AuthResponse login(LoginRequest request) {
		try {
			// Autenticar el nombre de usuario y la contraseña
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

			// Obtener detalles del usuario autenticado
			UserDetails user = userRepository.findByEmail(request.getEmail()).orElseThrow();

			// Generar un token JWT y devolver la respuesta de autenticación
			String token = jwtService.getToken(user);
			return AuthResponse.builder()
				.token(token)
				.build();
		} catch (BadCredentialsException e) {
			// Manejar credenciales no válidas
			logger.warn(e.getMessage());
		} catch (Exception e) {
			// Manejar otras excepciones
			logger.warn(e.getMessage());
		}
		
		// Devolver nulo si el inicio de sesión no fue exitoso
		return null;
	}
	
	// Método para gestionar el proceso de registro
	public AuthResponse register(RegisterRequest request) {
		// Crear una entidad de usuario con los detalles proporcionados
		UsuarioEntity user = UsuarioEntity.builder()
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.nombre(request.getNombre())
				.apellido(request.getApellido())
				.telefono(request.getTelefono())
				.role(Role.USER)
				.build();
		
		// Guardar el usuario en la base de datos
		userRepository.save(user);
		
		// Generar un token JWT para el nuevo usuario y devolver la respuesta de autenticación
		return AuthResponse.builder()
				.token(jwtService.getToken(user))
				.build();
	}
}
