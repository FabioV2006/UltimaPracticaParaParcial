package org.upc.ultimapracticaparaparcial.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.upc.ultimapracticaparaparcial.model.Tratamiento;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicamentoDTO {
    private Long id;

    private String nombre;

    private LocalDate fechaVencimiento;

    private String principioActivo;

    private Double precio;

}
