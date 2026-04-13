package com.mx.Alumnos.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Alumnos.dominio.Alumnos;

@Repository
public interface IAlumnosDao extends JpaRepository<Alumnos,Integer> {
	
	/*Los nombres de los metodos personalizados tienen que respetar ciertas convenciones definidas por JPA
	 * 
	 * Query Method Naming( derivacion de consulta por nombre) divide el metodo en partes:
	 * accion, condicion y el atributo para generar una consulta
	 * SQL que se aplicara a la base de datos  en tiempo de ejecucion
	 * */
	
	//SELECT * FROM Alumnos_DB WHERE NOMBRE = nombre;
	Alumnos findByNombreIgnoreCase(String nombre);
	
	List<Alumnos> findByNivelIgnoreCase(String nivel);
	
	
	

}
