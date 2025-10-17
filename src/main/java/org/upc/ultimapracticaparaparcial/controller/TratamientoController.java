package org.upc.ultimapracticaparaparcial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.upc.ultimapracticaparaparcial.dto.MontoTratamientoDTO;
import org.upc.ultimapracticaparaparcial.dto.TratamientoDTO;
import org.upc.ultimapracticaparaparcial.service.TratamientoService;

import java.time.LocalDate;
import java.util.List;

@RestController
// La ruta base es una combinación de las rutas de HU03 y HU04,
// por lo que es mejor definir la ruta completa en cada método.
@RequestMapping("/jpls")
public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    // --- Endpoint para HU03 ---
    // Ruta: jpls/listados/{id}
    @GetMapping("/listados/{tratamientoId}")
    // No lleva @PreAuthorize porque es de acceso público según la HU.
    public ResponseEntity<List<TratamientoDTO.MedicamentoInfoDTO>> listarMedicamentosPorTratamiento(@PathVariable Long tratamientoId) {
        List<TratamientoDTO.MedicamentoInfoDTO> medicamentos = tratamientoService.listarMedicamentosPorTratamiento(tratamientoId);
        return ResponseEntity.ok(medicamentos);
    }

    // --- Endpoint para HU04 ---
    // Ruta: jpls/montos
    @GetMapping("/montos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MontoTratamientoDTO>> calcularMontoPorTratamiento(
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
        // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) le indica a Spring cómo convertir
        // el texto de la URL (ej. "2025-10-17") a un objeto LocalDate.
        List<MontoTratamientoDTO> montos = tratamientoService.calcularMontoPorTratamiento(fechaInicio, fechaFin);
        return ResponseEntity.ok(montos);
    }
}