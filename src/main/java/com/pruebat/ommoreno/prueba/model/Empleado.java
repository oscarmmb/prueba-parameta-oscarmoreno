package com.pruebat.ommoreno.prueba.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empleados")
public class Empleado {
	//Variables de Empleado.
	private int id; 
	private String nombre; 
	private String apellido; 
	private String tipo_de_documento; 
	private int numero_de_documento; 
	private java.sql.Date fecha_de_nacimiento; 
	private java.sql.Date fecha_de_vinculacion; 
	private String cargo; 
	private double salario; 

	public Empleado() {
	}

	public Empleado(int id, String nombre, String apellido, String tipo_de_documento, int numero_de_documento,
			java.sql.Date fecha_de_nacimiento, java.sql.Date fecha_de_vinculacion, String cargo, double salario) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.tipo_de_documento = tipo_de_documento;
		this.numero_de_documento = numero_de_documento;
		this.fecha_de_nacimiento = fecha_de_nacimiento;
		this.fecha_de_vinculacion = fecha_de_vinculacion;
		this.cargo = cargo;
		this.salario = salario;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	//Encapsulamiento de variables.	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTipo_de_documento() {
		return tipo_de_documento;
	}

	public void setTipo_de_documento(String tipo_de_documento) {
		this.tipo_de_documento = tipo_de_documento;
	}

	public int getNumero_de_documento() {
		return numero_de_documento;
	}

	public void setNumero_de_documento(int numero_de_documento) {
		this.numero_de_documento = numero_de_documento;
	}

	public java.sql.Date getFecha_de_nacimiento() {
		return fecha_de_nacimiento;
	}

	public void setFecha_de_nacimiento(java.sql.Date fecha_de_nacimiento) {
		this.fecha_de_nacimiento = fecha_de_nacimiento;
	}

	public java.sql.Date getFecha_de_vinculacion() {
		return fecha_de_vinculacion;
	}

	public void setFecha_de_vinculacion(java.sql.Date fecha_de_vinculacion) {
		this.fecha_de_vinculacion = fecha_de_vinculacion;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

}
