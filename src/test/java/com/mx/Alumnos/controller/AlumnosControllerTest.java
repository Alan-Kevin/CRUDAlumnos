package com.mx.Alumnos.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.mx.Alumnos.dominio.Alumnos;
import com.mx.Alumnos.service.AlumnosService;

import tools.jackson.databind.ObjectMapper;

/*PRUEBAS UNITARIAS EN LA CAPA CONTROLADORA
 *
 * En esta capa se prueba validaciones de entrada, llamadas al service,
 * procesamiento de peticiones http y estatus en la respuesta.*/
@WebMvcTest(AlumnosController.class) //Levantamos solo la parte controladora, no toda la app.
class AlumnosControllerTest {

	//Simula las peticiones HTTP
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private AlumnosService service;

	//Objeto serializador Java a JSON
	private static final ObjectMapper mapper = new ObjectMapper();
	
	
	@Test
	void testListar() throws Exception {
	    //Instancias
	    Alumnos a1 = new Alumnos(1, "Juan", 15, "Secundaria");
	    Alumnos a2 = new Alumnos(2, "Pedro", 7, "Primaria");
	    
	    //Simulamos el comportamiento del service
	    when(service.listar()).thenReturn(Arrays.asList(a1, a2));
	    
	    //Simulamos una peticion GET
	    mockMvc.perform(MockMvcRequestBuilders.get("/Alumnos/listar"))
	        .andExpect(status().isOk()) //Esperamos un estatus 200
	        .andExpectAll(jsonPath("$.length()").value(2));
	}
	
	@Test
	void testGuardar() throws Exception {
	    Alumnos a1 = new Alumnos(1, "Juan", 15, "Secundaria");
	    
	    //Simulamos
	    when(service.guardar(Mockito.any(Alumnos.class))).thenReturn(a1);
	    
	    mockMvc.perform(MockMvcRequestBuilders.post("/Alumnos/guardar")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(mapper.writeValueAsString(a1)))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.nombre").value("Juan"));
	}
	
	@Test
	void testBuscar() throws Exception {
	    Alumnos a1 = new Alumnos(1, "Juan", 15, "Secundaria");
	    
	    when(service.buscar(1)).thenReturn(a1);
	    
	    mockMvc.perform(MockMvcRequestBuilders.get("/Alumnos/buscar/1"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.nombre").value("Juan"));
	}

	@Test
	void testBuscarNoExiste() throws Exception {
	    when(service.buscar(99)).thenReturn(null);
	    
	    mockMvc.perform(MockMvcRequestBuilders.get("/Alumnos/buscar/99"))
	        .andExpect(status().isOk());
	}
	
	@Test
	void testEliminar() throws Exception {
	    mockMvc.perform(MockMvcRequestBuilders.delete("/Alumnos/eliminar/1"))
	        .andExpect(status().isOk());
	    
	    Mockito.verify(service, Mockito.times(1)).eliminar(1);
	}
	
	@Test
	void testBuscarPorNombre() throws Exception {
	    Alumnos a1 = new Alumnos(1, "Juan", 15, "Secundaria");
	    
	    when(service.buscarPorNombre("Juan")).thenReturn(a1);
	    
	    mockMvc.perform(MockMvcRequestBuilders.get("/Alumnos/buscar-nombre/Juan"))
	        .andExpectAll(status().isOk())
	        .andExpect(jsonPath("$.nombre").value("Juan"));
	}
	
	@Test
	void testBuscarPorNivel() throws Exception {
	    Alumnos a1 = new Alumnos(1, "Juan", 15, "Secundaria");
	    Alumnos a2 = new Alumnos(2, "Luis", 15, "Secundaria");
	    
	    when(service.buscarPorNivel("Secundaria")).thenReturn(Arrays.asList(a1, a2));
	    
	    mockMvc.perform(MockMvcRequestBuilders.get("/Alumnos/buscar-nivel")
	            .param("nivel", "Secundaria"))
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$.length()").value(2));
	}

}