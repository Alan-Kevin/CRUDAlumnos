package com.mx.Alumnos.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.Alumnos.dao.IAlumnosDao;
import com.mx.Alumnos.dominio.Alumnos;

/*PRUEBAS UNITARIAS EN LA CAPA DAO
 *
 * En esta capa las pruebas unitarias no son 100% puras porque el
 * repositorio dependen completamente de una bd y se asemejaria mas a pruebas
 * de integracion.*/

@SpringBootTest
class IAlumnosDaoTest {
	
	@Autowired
	private IAlumnosDao dao;
	
	//Este metodo se ejecuta SIEMPRE ANTES de cada prueba unitaria, preparando el entorno para la prueba.
	@BeforeEach
	void setUp() throws Exception {
	    dao.deleteAll();
	    
	    Alumnos a1 = new Alumnos(1, "Juan", 15, "Secundaria");
	    Alumnos a2 = new Alumnos(2, "Pedro", 10, "Primaria");
	    Alumnos a3 = new Alumnos(3, "Luis", 13, "Secundaria");
	    
	    dao.save(a1);
	    dao.save(a2);
	    dao.save(a3);
	}
	
	

	@Test
	void testListar() {
	    //Recuperamos todos los registros de la bd
	    List<Alumnos> alumnos = dao.findAll();
	    
	    //Validamos que existan exactamente 3 registros en la lista
	    assertEquals(3, alumnos.size());
	}
	
	@Test
	void testGuardar() {
	    //Creamos el nuevo alumno
	    Alumnos alumno = new Alumnos(4, "Maria", 8, "Primaria");
	    
	    //Guardamos
	    dao.save(alumno);
	    
	    //Buscamos para verificar que si se guardo
	    Alumnos encontrado = dao.findById(4).orElse(null);
	    
	    //Validamos
	    assertNotNull(encontrado); //Verifica que el objeto NO sea nulo.
	    assertEquals("Maria", encontrado.getNombre());
	}
	
	@Test
	void testBuscar() {
	    //Buscamos
	    Alumnos alumno = dao.findById(1).orElse(null);
	    
	    //Validamos
	    assertNotNull(alumno);
	    assertEquals("Juan", alumno.getNombre());
	}

	@Test
	void testEditar() {
	    //Buscamos
	    Alumnos original = dao.findById(2).orElse(null);
	    
	    //Modificamos
	    original.setNombre("Update");
	    original.setEdad(50);
	    
	    //Guardamos cambios
	    dao.save(original);
	    
	    //Buscamos
	    Alumnos actualizado = dao.findById(2).orElse(null);
	    
	    //Validaciones
	    assertEquals("Update", actualizado.getNombre());
	    assertNotEquals(10, actualizado.getEdad());
	}
	
	@Test
	void testEliminar() {
	    //Buscamos
	    Alumnos eliminado = dao.findById(3).orElse(null);
	    
	    //Eliminamos
	    dao.delete(eliminado);
	    
	    //Buscamos nuevamente
	    Alumnos encontrado = dao.findById(3).orElse(null);
	    
	    //Validamos
	    assertNull(encontrado);
	    //Validamos que el objeto con id 3 no esté presente en la bd
	    assertFalse(dao.findById(3).isPresent());
	}
	
	@Test
	void testBuscarPorNombre() {
	    Alumnos alumno = dao.findByNombreIgnoreCase("Pedro");
	    
	    //Validamos
	    assertNotNull(alumno);
	    assertEquals("Pedro", alumno.getNombre());
	}

	@Test
	void testBuscarPorNivel() {
	    List<Alumnos> lista = dao.findByNivelIgnoreCase("Secundaria");
	    
	    //Validamos
	    assertEquals(2, lista.size());
	}
	
	
	
}