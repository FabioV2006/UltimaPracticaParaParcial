package org.upc.ultimapracticaparaparcial.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.upc.ultimapracticaparaparcial.dto.MedicamentoDTO;
import org.upc.ultimapracticaparaparcial.model.Medicamento;
import org.upc.ultimapracticaparaparcial.repository.MedicamentoRepository;

@Service
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final ModelMapper modelMapper;

    public MedicamentoService(MedicamentoRepository medicamentoRepository, ModelMapper modelMapper) {
        this.medicamentoRepository = medicamentoRepository;
        this.modelMapper = modelMapper;
    }
//    public MedicamentoDTO actualizarMedicamento(MedicamentoDTO medicamentoDTO) {
//
//        //Implementamos las validaciones
//        if(medicamentoDTO.getId() == null)
//        {
//            throw new IllegalArgumentException("Id invalido");
//        }
//        Medicamento medicamentoExistente = medicamentoRepository.findById(medicamentoDTO.getId()).orElseThrow(() ->
//                new IllegalArgumentException("Medicamento no encontrado con id" +  medicamentoDTO.getId()));
//
//        modelMapper.map(medicamentoDTO, medicamentoExistente);
//
//        Medicamento medicamentoActualizado = medicamentoRepository.save(medicamentoExistente);
//        return modelMapper.map(medicamentoActualizado, MedicamentoDTO.class);
//
//
//    }
    public MedicamentoDTO actualizarMedicamento(MedicamentoDTO medicamentoDTO) {
        // --- VALIDACIONES ---
        if (medicamentoDTO.getId() == null) {
            // El examen pide que el ID sea obligatorio.
            throw new IllegalArgumentException("El ID del medicamento es obligatorio para actualizar.");
        }
        // El examen pide 3 campos obligatorios para actualizar.
        if (medicamentoDTO.getNombre() == null || medicamentoDTO.getNombre().trim().isEmpty() ||
                medicamentoDTO.getPrincipioActivo() == null || medicamentoDTO.getPrincipioActivo().trim().isEmpty() ||
                medicamentoDTO.getPrecio() == null) {
            throw new IllegalArgumentException("Los campos nombre, principioActivo y precio son obligatorios.");
        }

        Medicamento medicamentoExistente = medicamentoRepository.findById(medicamentoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Medicamento no encontrado con id " + medicamentoDTO.getId()));

        modelMapper.map(medicamentoDTO, medicamentoExistente);
        Medicamento medicamentoActualizado = medicamentoRepository.save(medicamentoExistente);
        return modelMapper.map(medicamentoActualizado, MedicamentoDTO.class);
    }

    public MedicamentoDTO registrarMedicamento(MedicamentoDTO medicamentoDTO) {
        if(medicamentoDTO.getId() != null)
        {
            throw new IllegalArgumentException("El ID debe estar vacio para la creacion de un nuevo medicamento");
        }
        //validar campos llenos
        if(medicamentoDTO.getNombre() == null || medicamentoDTO.getNombre().trim().isEmpty())
        {
            throw new IllegalArgumentException("el Nombre del medicamento es obligatorio");
        }
        Medicamento medicamento =  modelMapper.map(medicamentoDTO, Medicamento.class);
        Medicamento medicamentoGuardado =  medicamentoRepository.save(medicamento);
        return modelMapper.map(medicamentoGuardado, MedicamentoDTO.class);


    }

    public void eliminarMedicamento(Long medicamentoId) {
        if(!medicamentoRepository.existsById(medicamentoId))
        {
            throw new IllegalArgumentException("El medicamento con id: "+ medicamentoId +" no existe");
        }

        medicamentoRepository.deleteById(medicamentoId);
    }

}
