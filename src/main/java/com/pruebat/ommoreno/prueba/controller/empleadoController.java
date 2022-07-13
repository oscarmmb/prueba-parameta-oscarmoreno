package com.pruebat.ommoreno.prueba.controller;

import com.pruebat.ommoreno.prueba.model.Empleado;
import com.pruebat.ommoreno.prueba.service.empleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.NoSuchElementException;


import com.github.cliftonlabs.json_simple.JsonObject;

@RestController
@RequestMapping("/empleados")

public class empleadoController {

	@Autowired
	empleadoService empleadoService;

	//lista todos los empleados
	@GetMapping("")
	public List<Empleado> list() { 
		return empleadoService.listAllEmpleados();

	}

	//muestra el empleado con el id solicitado
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> get(@PathVariable Integer id) { 
		try {
			Empleado empleado = empleadoService.getEmpleado(id);
			return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Empleado>(HttpStatus.NOT_FOUND); 
		}
	}
	
	//Si el usuario es mayor de edad y los datos ingresados no estan vacios, crea un nuevo empleado.
	@PostMapping("/")
	public ResponseEntity<JsonObject> add(@RequestBody Empleado user) {
		try {
			//valida si el usuario es mayor de edad y los campos no estan vacios
			JsonObject json = empleadoService.crearJSON();
			if ((empleadoService.isCampoVacio(user)) && (empleadoService.calcularDate(user) >= 18)) { 
				empleadoService.saveEmpleado(user); //crea un empleado nuevo
				return new ResponseEntity<JsonObject>(json, HttpStatus.OK); 
			} else { 
				
				if(empleadoService.isCampoVacio(user)==false) {					
					json.put("Error","Alguno de los campos ingresados se encuentra vacio.");
				}
				if(empleadoService.calcularDate(user) < 18) { 
					json.put("Error","La persona ingresada es menor de edad.");
				}

				return new ResponseEntity<JsonObject>(json,HttpStatus.BAD_REQUEST); //retorna el error correspondiente en un objeto JSON
			}
		} catch (NullPointerException e) {
			JsonObject json = new JsonObject();
			json.put("Error","Formato de dato invalido");
			return new ResponseEntity<JsonObject>(json, HttpStatus.BAD_REQUEST); //error de dato no valido
		} catch (Exception e) {
			JsonObject json = new JsonObject();
			json.put("Error guardando datos del empleado","revise los campos");
			return new ResponseEntity<JsonObject>(json, HttpStatus.BAD_REQUEST); //error de dato no valido
		}
		
	}

	//busca si la id del empleado existe.
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Empleado user, @PathVariable Integer id) {
		try {
			empleadoService.getEmpleado(id);
			user.setId(id);
			empleadoService.saveEmpleado(user);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Elimina empleado a traves de su id.
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) { 
		empleadoService.deleteEmpleado(id);
	}
}
