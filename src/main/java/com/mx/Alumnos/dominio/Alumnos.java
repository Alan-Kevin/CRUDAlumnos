package com.mx.Alumnos.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Alumnos_DB")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Alumnos {
	
	@Id
	private Integer id;
	private String nombre;
	private Integer edad;
	private String nivel;

}
