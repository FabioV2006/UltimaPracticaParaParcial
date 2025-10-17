package org.upc.ultimapracticaparaparcial.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TratamientoDTO {
    private Long id;
    private List<MedicamentoInfoDTO> medicamentos;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MedicamentoInfoDTO {
        private String nombre;
        private Double costo;
    }
}
