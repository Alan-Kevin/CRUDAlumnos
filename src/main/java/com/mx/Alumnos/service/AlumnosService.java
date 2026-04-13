package com.mx.Alumnos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mx.Alumnos.dao.IAlumnosDao;
import com.mx.Alumnos.dominio.Alumnos;
@Service
public class AlumnosService implements IAlumnosService {
	@Autowired
	private IAlumnosDao dao;

	@Override
	public Alumnos guardar(Alumnos a) {
		return dao.save(a);
	}

	@Override
	public Alumnos editar(Alumnos a) {
		return dao.save(a);
	}

	@Override
	public Alumnos buscar(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id) {
		dao.deleteById(id);
		
	}

	@Override
	public List<Alumnos> listar() {
		return dao.findAll(Sort.by(Sort.Direction.DESC,"id"));
		
	}

	@Override
	public Alumnos buscarPorNombre(String nombre) {
		return dao.findByNombreIgnoreCase(nombre);
	}

	@Override
	public List<Alumnos> buscarPorNivel(String nivel) {
		return dao.findByNivelIgnoreCase(nivel);
	}

}
