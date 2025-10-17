package org.upc.ultimapracticaparaparcial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MontoTratamientoDTO {
    private Long tratamientoId;
    private Double montoTotal;
}