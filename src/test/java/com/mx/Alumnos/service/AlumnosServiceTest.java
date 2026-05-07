package com.mx.Alumnos.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;


import com.mx.Alumnos.dao.IAlumnosDao;
import com.mx.Alumnos.dominio.Alumnos;

class AlumnosServiceTest {

	/*PRUEBAS UNITARIAS EN LA CAPA DE SERVICIO
	 *
	 * Las pruebas en la capa service validan la logica de negocio, validan datos,
	 * llamadas al repositorio (simulado) y simula dependencias con Mockito.*/
	
	//Creamos un Mock del repositorio IAlumnosDao simulado. (Objeto falso)
	@Mock
	private IAlumnosDao dao;

	//Inyectamos el dao simulado dentro del servicio
	@InjectMocks
	private AlumnosService service;
	
	@BeforeEach
	void setUp() throws Exception {
		//Inicializamos los mocks antes de cada prueba unitaria
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testListar() {
	    List<Alumnos> lista = Arrays.asList(
	            new Alumnos(1, "Juan", 15, "Secundaria"),
	            new Alumnos(2, "Pedro", 10, "Primaria"));

	    //Simulamos el comportamiento del dao
	    when(dao.findAll(any(Sort.class))).thenReturn(lista);

	    List<Alumnos> resultado = service.listar();

	    //Validaciones
	    assertEquals(2, resultado.size());

	    //Verificamos que el mock dao llamo al menos una vez al metodo findAll
	    verify(dao).findAll(any(Sort.class));
	}
	
	@Test
	void testGuardar() {
	    Alumnos alumno = new Alumnos(1, "Juan", 15, "Secundaria");
	    
	    //Simulamos el comportamiento del dao
	    when(dao.save(alumno)).thenReturn(alumno);
	    
	    //Guardamos
	    Alumnos guardado = service.guardar(alumno);
	    
	    //Validaciones
	    assertNotNull(guardado);
	    assertEquals("Juan", guardado.getNombre());
	    
	    verify(dao).save(alumno);
	}
	
	@Test
	void testBuscar() {
	    Alumnos alumno = new Alumnos();
	    
	    when(dao.findById(1)).thenReturn(Optional.of(alumno));
	    
	    Alumnos encontrado = service.buscar(1);
	    
	    assertNotNull(encontrado);
	    
	    verify(dao).findById(1);
	}

	
	@Test
	void testEliminar() {
	    service.eliminar(1);
	    verify(dao).deleteById(1);
	}
	
	@Test
	void testBuscarPorNombre() {
	    Alumnos a1 = new Alumnos(1, "Juan", 15, "Secundaria");
	    Alumnos a2 = new Alumnos(2, "Pedro", 7, "Primaria");
	    
	    when(dao.findByNombreIgnoreCase("Juan")).thenReturn(a1);
	    
	    Alumnos encontrado = service.buscarPorNombre("Juan");
	    
	    assertNotNull(encontrado);
	    assertEquals("Secundaria", encontrado.getNivel());
	    
	    verify(dao).findByNombreIgnoreCase("Juan");
	}
	
	@Test
	void testBuscarPorNivel() {
	    Alumnos a1 = new Alumnos(1, "Juan", 15, "Secundaria");
	    Alumnos a2 = new Alumnos(2, "Pedro", 14, "Secundaria");
	    Alumnos a3 = new Alumnos(3, "Luis", 7, "Primaria");
	    
	    when(dao.findByNivelIgnoreCase("Secundaria")).thenReturn(Arrays.asList(a1, a2));
	    
	    List<Alumnos> resultado = service.buscarPorNivel("Secundaria");
	    
	    assertEquals(2, resultado.size());
	    assertEquals("Pedro", resultado.get(1).getNombre());
	    
	    verify(dao).findByNivelIgnoreCase("Secundaria");
	}

}
