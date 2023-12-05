package com.pinarsoft.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pinarsoft.example.entity.UsuarioEntity;
import com.pinarsoft.example.service.UsuarioService;

/* 
	Controlador protegido requiere token de autenticacion. 
	(Revisar config)
*/ 

@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PutMapping("/create")
	public void createUsuario(@RequestBody UsuarioEntity usuario) {
		usuarioService.createUsuario(usuario);
	}
	
	@GetMapping("test")
	public String test() {
		return "Hola";
	}
	
}
