package com.mx.Alumnos.service;

import java.util.List;
import com.mx.Alumnos.dominio.Alumnos;

public interface IAlumnosService {
	
	Alumnos guardar(Alumnos a);
	Alumnos editar(Alumnos a);
	Alumnos buscar(int id);
	void eliminar(int id);
	List<Alumnos> listar ();
	
	Alumnos buscarPorNombre(String nombre);
	
	List<Alumnos> buscarPorNivel(String nivel);

}
