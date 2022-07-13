package com.pruebat.ommoreno.prueba.repository;

import com.pruebat.ommoreno.prueba.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

//conexion JPA
public interface empleadoRepository extends JpaRepository<Empleado, Integer> { 

}
