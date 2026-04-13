package com.mx.Alumnos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Alumnos.dominio.Alumnos;
import com.mx.Alumnos.service.IAlumnosService;

@RestController
@RequestMapping("Alumnos")
public class AlumnosController {
	
	
	//<INyeccion por constructor
	private final IAlumnosService service;

	public AlumnosController(IAlumnosService service) {
		super();
		this.service = service;
	}
	
	@GetMapping("listar")
	public List<Alumnos> listar(){
		return service.listar();
	}
	
	@PostMapping("guardar")
	public Alumnos guardar(@RequestBody Alumnos alumno) {
		return service.guardar(alumno);
	}
	
	
	@GetMapping("buscar/{id}")
	public Alumnos buscar(@PathVariable int id) {
		return service.buscar(id);
	}
	
	@PutMapping("editar")
	public Alumnos editar(@RequestBody Alumnos alumno) {
		return service.editar(alumno);
	}
	
	@DeleteMapping("eliminar/{id}")
	public void eliminar(@PathVariable int id) {
		service.eliminar(id);
	}
	
	@GetMapping("buscar-nombre/{nombre}")
	public Alumnos buscarPorNombre(@PathVariable String nombre) {
		return service.buscarPorNombre(nombre);
	}
	
	@GetMapping("buscar-nivel")
	public List<Alumnos> buscarPorNivel(@RequestParam String nivel){
		return service.buscarPorNivel(nivel);
	}
	
}
	
	

