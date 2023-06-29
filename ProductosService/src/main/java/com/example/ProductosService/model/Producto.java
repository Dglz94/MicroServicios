package com.example.ProductosService.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Document(collection = "productos")
public class Producto {
	@Id
	private String id;
	@NotNull
	@Size(max=30)
	private String nombre;
	@NotNull
	@Enumerated(EnumType.STRING)
	private UnidadMedida unidadMedida;
	@NotNull
	@Size(min=3, max=30)
	private String clave;
	@DecimalMin(value = "0.0", inclusive = false)
	private float precio;

	public Producto(@NotNull @Size(max = 30) String nombre, @NotNull UnidadMedida unidadMedida, @NotNull String clave,
			@NotNull float precio) {
		super();
		this.nombre = nombre;
		this.unidadMedida = unidadMedida;
		this.clave = clave;
		this.precio = precio;
	}
	


	public String getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}


	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}


	public String getClave() {
		return clave;
	}


	public void setClave(String clave) {
		this.clave = clave;
	}


	public float getPrecio() {
		return precio;
	}


	public void setPrecio(float precio) {
		this.precio = precio;
	}


	public enum UnidadMedida {
	    @NotNull(message = "La unidad de medida no puede ser nula")
	    PIEZA,
	    
	    @NotNull(message = "La unidad de medida no puede ser nula")
	    KILOGRAMO,
	    
	    @NotNull(message = "La unidad de medida no puede ser nula")
	    PULGADA,
	    
	    @NotNull(message = "La unidad de medida no puede ser nula")
	    LITRO
	}
}
