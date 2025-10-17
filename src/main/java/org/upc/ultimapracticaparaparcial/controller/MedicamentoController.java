package org.upc.ultimapracticaparaparcial.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.upc.ultimapracticaparaparcial.dto.MedicamentoDTO;
import org.upc.ultimapracticaparaparcial.model.Medicamento;
import org.upc.ultimapracticaparaparcial.service.MedicamentoService;

@RestController
@RequestMapping("/vilca")
public class MedicamentoController {
    private final MedicamentoService medicamentoService;

    public MedicamentoController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;
    }

    @PreAuthorize("hasRole('FARMACIA')")
    @PutMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> actualizarMedicamento(@PathVariable Long id, @RequestBody MedicamentoDTO medicamentoDTO) {
        // Asegúrate que el servicio recibe el ID por separado
        medicamentoDTO.setId(id); // O modifica el servicio para recibir 2 parámetros
        MedicamentoDTO medicamentoActualizado = medicamentoService.actualizarMedicamento(medicamentoDTO);
        return ResponseEntity.ok(medicamentoActualizado);
    }

    @PostMapping
    @PreAuthorize("hasRole('FARMACIA')")
    public ResponseEntity<String> registrarMedicamento(@Valid @RequestBody MedicamentoDTO medicamentoDTO) {
        MedicamentoDTO medicamentoRegistrado = medicamentoService.registrarMedicamento(medicamentoDTO);
        String mensaje = "Se registró correctamente con ID " + medicamentoRegistrado.getId();
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}") // ← FALTABA EL {id} EN EL MAPPING
    @PreAuthorize("hasRole('FARMACIA')")
    public ResponseEntity<Void> eliminarMedicamento(@PathVariable Long id) { // ← Quitar @Valid aquí
        medicamentoService.eliminarMedicamento(id);
        return ResponseEntity.noContent().build();
    }
}