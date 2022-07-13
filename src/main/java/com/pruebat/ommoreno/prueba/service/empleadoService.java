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
	
	//Variable que guarda la edad del empleado
	public String edad; 
	//Variable que guarda el tiempo de vinculacion laboral del empleado
	public String tiempoVLaboral;
	
	@Autowired
	private empleadoRepository empleadoRepository; 
	
	//lista todos los Empleado (GET)
	public List<Empleado> listAllEmpleados() { 
		return empleadoRepository.findAll();
	}
	
	//crea un nuevo Empleado (POST)
	public void saveEmpleado(Empleado user) { 
		empleadoRepository.save(user);
	}
	
	//busca un empleado por su id (PUT)
	public Empleado getEmpleado(Integer id) { 
		return empleadoRepository.findById(id).get();
	}
	
	//elimina un empleado a traves de su id (DELETE)
	public void deleteEmpleado(Integer id) { 
		empleadoRepository.deleteById(id);
	}
	
	/***
	 * Valida si se esta ingresando algun campo vacio.
	 * fechaNacimiento = Variable que toma el campo Date (Fecha_de_nacimiento) y lo transforma en String
	 * fechaVinculacion = Variable que toma el campo Date (Fecha_de_vinculacion) y lo transforma en String
	 * @param user (objeto Empleado)
	 * @return validacion booleana
	 */
	public boolean isCampoVacio(Empleado user) { 
		
		boolean val = false;
		String fechaNacimiento = user.getFecha_de_nacimiento().toString(); 
		String fechaVinculacion = user.getFecha_de_vinculacion().toString();
		//validaciones de campo vacio
		if ((user.getNombre().equals("")) || (user.getApellido().equals("")) || (user.getTipo_de_documento().equals("")) 
				|| (user.getNumero_de_documento() == 0) || (fechaNacimiento.equals("0002-11-29")) || (fechaVinculacion.equals("0002-11-29"))
				|| (user.getCargo().equals("")) || (user.getSalario() == 0)) { 
			val = false;
		} else {
			val = true;
		}
		return val;
	}

	/**
	 * Crea un Objeto JSON con edad y tiempo laborado
	 * json = Variable JSON
	 * @return JsonObject
	 */
	public JsonObject crearJSON() { 
		JsonObject json = new JsonObject(); 
		json.put("edad_actual", edad);
		json.put("tiempo_de_vinculación", tiempoVLaboral);
		return json;
	}
	
	/***
	 * Calcula fecha de nacimiento y tiempo de vinculacion laboral
	 * Hoy = variable que guarda la transformacion de Variable tipo Date (date) en String.
	 * nacimiento = variable que guarda la transformacion de Variable tipo Date (Fecha_de_nacimiento) en String.
	 * vinculacion= variable que guarda la transformacion de Variable tipo Date (Fecha_de_vinculacion) en String.
	 * valorEdad = variable que guarda la edad del empleado
	 * @param user (objeto Empleado)
	 * @return edad del empleado
	 */
	public int calcularDate(Empleado user) { 
		long now = System.currentTimeMillis(); 
		java.sql.Date date = new java.sql.Date(now); 
		String Hoy = date.toString(); 
		String nacimiento = user.getFecha_de_nacimiento().toString(); 
		String vinculacion = user.getFecha_de_vinculacion().toString(); 
		// transformacion de variables tipo String a variable LocalDate.
		LocalDate startDate = LocalDate.parse(Hoy);  
		LocalDate endDateN = LocalDate.parse(nacimiento); 
		LocalDate endDateV = LocalDate.parse(vinculacion); 
		//calcula la edad
		Period Medad = Period.between(endDateN, startDate);
		int valorEdad = Medad.getYears();
		
		edad = calcularEdad(endDateN, startDate);
		tiempoVLaboral = calcularEdad(endDateV, startDate);

		return valorEdad;
	}
	
	//calcula edad del empleado
	public String calcularEdad(LocalDate endDate, LocalDate startDate) {
		Period periodo = Period.between(endDate, startDate);
		String Resultado = String.format("%d-%d-%d", periodo.getYears(), periodo.getMonths(), periodo.getDays()); //da formato a la edad del empleado
		return Resultado;
	}
}
