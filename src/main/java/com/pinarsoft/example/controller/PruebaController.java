package com.pinarsoft.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PruebaController {
	
	@GetMapping("/saludar")
	public String saludar () {
		return "Hola";
	}
}
