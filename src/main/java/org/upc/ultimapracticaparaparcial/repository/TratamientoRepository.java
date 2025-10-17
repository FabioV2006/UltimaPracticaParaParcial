package org.upc.ultimapracticaparaparcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.upc.ultimapracticaparaparcial.model.Tratamiento;

public interface TratamientoRepository extends JpaRepository<Tratamiento,Long> {
}
