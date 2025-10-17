package org.upc.ultimapracticaparaparcial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.upc.ultimapracticaparaparcial.dto.MontoTratamientoDTO;
import org.upc.ultimapracticaparaparcial.model.Medicamento;
import org.upc.ultimapracticaparaparcial.model.Tratamiento;

import java.time.LocalDate;
import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

    // HU03: Listar medicamentos por el ID del tratamiento.
    // El nombre del método es suficiente para que Spring Data JPA genere la consulta.
    List<Medicamento> findByTratamientoId(Long tratamientoId);

    // HU04: Calcular el monto total por tratamiento en un rango de fechas.
    @Query("SELECT new org.upc.ultimapracticaparaparcial.dto.MontoTratamientoDTO(t.id, SUM(m.precio)) " +
            "FROM Medicamento m JOIN m.tratamiento t " +
            "WHERE t.fechaInicio >= :fechaInicio AND t.fechaFin <= :fechaFin " +
            "GROUP BY t.id")
    List<MontoTratamientoDTO> calcularMontoPorTratamientoEnPeriodo(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );
}
