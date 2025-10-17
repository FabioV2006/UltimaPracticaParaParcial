package org.upc.ultimapracticaparaparcial.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.upc.ultimapracticaparaparcial.dto.MontoTratamientoDTO;
import org.upc.ultimapracticaparaparcial.dto.TratamientoDTO;
import org.upc.ultimapracticaparaparcial.model.Medicamento;
import org.upc.ultimapracticaparaparcial.repository.MedicamentoRepository;
import org.upc.ultimapracticaparaparcial.repository.TratamientoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TratamientoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private TratamientoRepository tratamientoRepository; // Lo necesitaremos para validar que el tratamiento existe

    @Autowired
    private ModelMapper modelMapper;

    // --- Lógica para HU03 ---
    public List<TratamientoDTO.MedicamentoInfoDTO> listarMedicamentosPorTratamiento(Long tratamientoId) {
        // Validación: Asegurarnos de que el tratamiento exista antes de buscar sus medicamentos.
        if (!tratamientoRepository.existsById(tratamientoId)) {
            throw new RuntimeException("No se encontró el tratamiento con ID: " + tratamientoId); // Esto será capturado por el GlobalExceptionHandler
        }

        List<Medicamento> medicamentos = medicamentoRepository.findByTratamientoId(tratamientoId);

        // Convertimos la lista de Entidades a una lista de DTOs específicos para la respuesta.
        return medicamentos.stream()
                .map(medicamento -> new TratamientoDTO.MedicamentoInfoDTO(medicamento.getNombre(), medicamento.getPrecio()))
                .collect(Collectors.toList());
    }

    // --- Lógica para HU04 ---
    public List<MontoTratamientoDTO> calcularMontoPorTratamiento(LocalDate fechaInicio, LocalDate fechaFin) {
        return medicamentoRepository.calcularMontoPorTratamientoEnPeriodo(fechaInicio, fechaFin);
    }
}