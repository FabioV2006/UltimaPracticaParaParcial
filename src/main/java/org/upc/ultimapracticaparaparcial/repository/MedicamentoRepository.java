package org.upc.ultimapracticaparaparcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.upc.ultimapracticaparaparcial.model.Medicamento;
import org.upc.ultimapracticaparaparcial.model.Tratamiento;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    //Aquí irá las historias de usuario 3 y 4

    //Historia de Usuario 3
    List<Medicamento> findByTratamientoId(Long tratamientoId);

    //Historia de Usuario 4

}
