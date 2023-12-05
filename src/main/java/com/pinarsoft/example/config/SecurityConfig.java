package com.pinarsoft.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.pinarsoft.example.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

/* Clase de configuracion de seguridad.
 * Generalmente en esta clase lo que hacemos es inyectar los beans referentes al Filtro y al auth Provider
 * para despues retornar el filtro que va a ser usado para todo el sistema de autenticación
 */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final AuthenticationProvider authProvider;
	
	@Bean //Filtro de seguridad que se va a aplicar en Spring Security
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.csrf(csrf -> 
					csrf
					.disable() // Desactivamos el sistema de tokens Cross-SiteRequestForgery
				)
				// Autorización de los Request HTTP
				.authorizeHttpRequests(authRequest ->
					authRequest
						/* Definimos que todos los controladores mapeados en /auth serán publicos y cualquier request podrá acceder
						a ellos. */
						.requestMatchers("/auth/**").permitAll()
						/* Definimos que el resto de peticiones van a necesitar autenticación */
						.anyRequest().authenticated()
						)
				// Configuramos la Autenticacion basada en jwt
				.sessionManagement(sessionManager -> 
					sessionManager // Deshabilitamos el sistema de sesiones
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
						)
					.authenticationProvider(authProvider) // Fijamos el provider de autenticacion
						// Agregamos el filtro que desarrollamos y vamos a usar.
					   .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build()
				;
	}
}
