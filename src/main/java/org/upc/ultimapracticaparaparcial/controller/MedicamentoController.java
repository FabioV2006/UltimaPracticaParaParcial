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

//    @PreAuthorize("hasRole('FARMACIA')")
//    @PutMapping("/{id}")
//    public ResponseEntity<MedicamentoDTO> actualizarMedicamento(@PathVariable Long id, @RequestBody MedicamentoDTO medicamentoDTO) {
//        // Asegúrate que el servicio recibe el ID por separado
//        medicamentoDTO.setId(id); // O modifica el servicio para recibir 2 parámetros
//        MedicamentoDTO medicamentoActualizado = medicamentoService.actualizarMedicamento(medicamentoDTO);
//        return ResponseEntity.ok(medicamentoActualizado);
//    }
// --- HU02: ACTUALIZAR MEDICAMENTO CON MANEJO DE ERROR LOCAL ---
    @PreAuthorize("hasRole('FARMACIA')")
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMedicamento(@PathVariable Long id, @RequestBody MedicamentoDTO medicamentoDTO) {
        try {
            medicamentoDTO.setId(id);
            MedicamentoDTO medicamentoActualizado = medicamentoService.actualizarMedicamento(medicamentoDTO);
            return ResponseEntity.ok(medicamentoActualizado); // Respuesta 200 OK si todo va bien

        } catch (IllegalArgumentException ex) {
            // Si el servicio lanza la excepción que definimos (porque un campo es nulo,
            // o el ID no existe), la capturamos aquí.

            // Devolvemos el mensaje de la excepción y el código HTTP 406 Not Acceptable,
            // cumpliendo EXACTAMENTE con el requisito del examen.
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('FARMACIA')")
    public ResponseEntity<String> registrarMedicamento(@Valid @RequestBody MedicamentoDTO medicamentoDTO) {
        MedicamentoDTO medicamentoRegistrado = medicamentoService.registrarMedicamento(medicamentoDTO);
        String mensaje = "Se registró correctamente con ID " + medicamentoRegistrado.getId();
        return new ResponseEntity<>(mensaje, HttpStatus.CREATED);
    }

    // --- CORRECCIÓN EN EL MÉTODO DELETE ---
    @DeleteMapping("/{id}") // <-- FALTABA EL {id} PARA CAPTURAR LA VARIABLE DE LA URL
    @PreAuthorize("hasRole('FARMACIA')")
    public ResponseEntity<Void> eliminarMedicamento(@PathVariable Long id) { // <-- QUITAR @Valid, no aplica a un tipo simple como Long
        medicamentoService.eliminarMedicamento(id);
        return ResponseEntity.noContent().build(); // noContent() es el estándar para un DELETE exitoso.
    }
}