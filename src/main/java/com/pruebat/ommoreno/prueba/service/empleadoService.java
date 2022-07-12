package com.pruebat.ommoreno.prueba.service;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.pruebat.ommoreno.prueba.model.Empleado;
import com.pruebat.ommoreno.prueba.repository.empleadoRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class empleadoService {
	public String edad; //Variable que guarda la edad del empleado
	public String TLaboral; //Variable que guarda el tiempo de vinculacion laboral del empleado
	
	@Autowired
	private empleadoRepository empleadoRepository; 

	public List<Empleado> listAllEmpleados() { //lista todos los Empleado (GET)
		return empleadoRepository.findAll();
	}

	public void saveEmpleado(Empleado user) { //crea un nuevo Empleado (POST)
		empleadoRepository.save(user);
	}

	public Empleado getEmpleado(Integer id) { //busca un empleado por su id (PUT)
		return empleadoRepository.findById(id).get();
	}

	public void deleteEmpleado(Integer id) { //elimina un empleado a traves de su id (DELETE)
		empleadoRepository.deleteById(id);
	}
	
	/***
	 * 
	 * @param user
	 * @return
	 */
	public boolean isCampoVacio(Empleado user) { //Valida si se esta ingresando algun campo vacio
		
		boolean val = false; //variable de validacion
		String Ed = user.getFecha_de_nacimiento().toString(); //Variable que toma el campo Date (Fecha_de_nacimiento) y lo transforma en String
		String Vn = user.getFecha_de_vinculacion().toString(); //Variable que toma el campo Date (Fecha_de_vinculacion) y lo transforma en String

		if ((user.getNombre().equals("")) || (user.getApellido().equals("")) || (user.getTipo_de_documento().equals("")) //validaciones de campo vacio
				|| (user.getNumero_de_documento() == 0) || (Ed.equals("0002-11-29")) || (Vn.equals("0002-11-29"))
				|| (user.getCargo().equals("")) || (user.getSalario() == 0)) { 
			val = false;
		} else {
			val = true;
		}
		return val;
	}

	/**
	 * 
	 * @return
	 */
	public JsonObject crearJSON() { //Crea un Objeto JSON con edad y tiempo laborado
		JsonObject json = new JsonObject(); //Variable JSON
		json.put("edad_actual", edad); //inserta edad
		json.put("tiempo_de_vinculación", TLaboral); //inserta Tiempo de vinculacion laboral
		return json;
	}

	public int calcularDate(Empleado user) { //Calcula fecha de nacimiento y tiempo de vinculacion laboral
		long now = System.currentTimeMillis(); //variable con fecha actual
		java.sql.Date date = new java.sql.Date(now); //variable que guarda la transformacion de fecha actual en variable java.sql.Date
		String Hoy = date.toString(); //variable que guarda la transformacion de Variable tipo Date (date) en String.
		String nacimiento = user.getFecha_de_nacimiento().toString(); //variable que guarda la transformacion de Variable tipo Date (Fecha_de_nacimiento) en String.
		String vinculacion = user.getFecha_de_vinculacion().toString(); //variable que guarda la transformacion de Variable tipo Date (Fecha_de_vinculacion) en String.

		LocalDate startDate = LocalDate.parse(Hoy);//variable que guarda la transformacion de variable tipo String (Hoy) a variable LocalDate.  
		LocalDate endDateN = LocalDate.parse(nacimiento); //variable que guarda la transformacion de variable tipo String (nacimiento) a variable LocalDate.
		LocalDate endDateV = LocalDate.parse(vinculacion); //variable que guarda la transformacion de variable tipo String (vinculacion) a variable LocalDate.
		Period Medad = Period.between(endDateN, startDate); //calcula la edad
		int Valedad = Medad.getYears();//variable que guarda la edad del empleado
		edad = calcularEdad(endDateN, startDate); ///variable que guarda la edad del empleado con dias y meses.
		TLaboral = calcularEdad(endDateV, startDate); //variable que guarda el tiempo de vinculacion laboral del empleado con dias y meses.
		//System.out.println(edad); //validador edad
		//System.out.println(TLaboral); //validador tiempo vinculacion
		return Valedad;
	}

	public String calcularEdad(LocalDate endDate, LocalDate startDate) { //calcula edad del empleado
		Period periodo = Period.between(endDate, startDate);
		String Resultado = String.format("%d-%d-%d", periodo.getYears(), periodo.getMonths(), periodo.getDays()); //da formato a la edad del empleado
		return Resultado;
	}
}
