package org.ada.gestorgastronomico.service;

import org.ada.gestorgastronomico.dto.IngredienteDTO;
import org.ada.gestorgastronomico.entity.Ingrediente;
import org.ada.gestorgastronomico.entity.MateriaPrima;
import org.ada.gestorgastronomico.entity.Producto;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.ada.gestorgastronomico.repository.IngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;
    private final MateriaPrimaService materiaPrimaService;

    public IngredienteService(IngredienteRepository ingredienteRepository, MateriaPrimaService materiaPrimaService) {
        this.ingredienteRepository = ingredienteRepository;
        this.materiaPrimaService = materiaPrimaService;
    }

    public List<IngredienteDTO> mapToDTOS(List<Ingrediente> ingredientes) {
        return ingredientes.stream().map(ingrediente -> mapToDTO(ingrediente)).collect(Collectors.toList());
    }

    public List<Ingrediente> create(List<IngredienteDTO> ingredientesDTO, Producto producto) {
        List<Ingrediente> listaIngredientes = new ArrayList<>();
        for (IngredienteDTO ingredienteDTO: ingredientesDTO) {
            Optional<MateriaPrima> materiaPrima = materiaPrimaService.findById(ingredienteDTO.getIdMateriaPrima()); // Obtengo la materia prima
            if (materiaPrima.isEmpty()){ // Verifico si encontró esa materia prima o no
                throw new ResourceNotFoundException("Materia prima no encontrada"); //Si no la encontró, lanzo una excepción
            } else {
                materiaPrimaService.decrementarStock(ingredienteDTO.getCantidad(), materiaPrima.get()); // Modifico el stock de la materia prima
            }

            Ingrediente ingrediente = mapToEntity(ingredienteDTO, producto, materiaPrima.get());
            ingredienteRepository.save(ingrediente);
            listaIngredientes.add(ingrediente);
        }
        return listaIngredientes;
    }

    private Ingrediente mapToEntity(IngredienteDTO ingredienteDTO, Producto producto, MateriaPrima materiaPrima) {
        Ingrediente ingrediente = new Ingrediente(ingredienteDTO.getCantidad(), ingredienteDTO.getUnidadMedida(), producto, materiaPrima);
        return ingrediente;
    }

    private IngredienteDTO mapToDTO(Ingrediente ingrediente) {
        IngredienteDTO ingredienteDTO = new IngredienteDTO(ingrediente.getCantidad(),
                ingrediente.getUnidadMedida(), ingrediente.getMateriaPrima().getId(),
                ingrediente.getMateriaPrima().getNombre());

        ingredienteDTO.setId(ingrediente.getId());

        return ingredienteDTO;
    }


}
