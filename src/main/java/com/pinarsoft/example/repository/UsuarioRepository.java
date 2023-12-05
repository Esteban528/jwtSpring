package com.pinarsoft.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pinarsoft.example.entity.UsuarioEntity;

/* Repositorio normal de los Usuarios
 * 
 */

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
	Optional<UsuarioEntity> findByEmail(String email);
}
