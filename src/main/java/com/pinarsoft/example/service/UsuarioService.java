package com.pinarsoft.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinarsoft.example.entity.UsuarioEntity;
import com.pinarsoft.example.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void createUsuario(UsuarioEntity usuario) {
		usuarioRepository.save(usuario);
	}
}
