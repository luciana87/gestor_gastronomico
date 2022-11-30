package org.ada.gestorgastronomico.service;

import org.ada.gestorgastronomico.dto.MateriaPrimaDTO;
import org.ada.gestorgastronomico.entity.MateriaPrima;
import org.ada.gestorgastronomico.exceptions.ExistingResourceException;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.ada.gestorgastronomico.repository.MateriaPrimaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaPrimaService {
    private final MateriaPrimaRepository materiaPrimaRepository;

    public MateriaPrimaService(MateriaPrimaRepository materiaPrimaRepository) {
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    public MateriaPrimaDTO create(MateriaPrimaDTO materiaPrimaDTO){

        checkForExistingMateriaPrima(materiaPrimaDTO.getNombre());
        MateriaPrima materiaPrima = mapToEntity(materiaPrimaDTO);
        materiaPrima = materiaPrimaRepository.save(materiaPrima);

        materiaPrimaDTO.setId(materiaPrima.getId()); //TODO: chequear

        return materiaPrimaDTO;
    }

    public List<MateriaPrimaDTO> retrieveAll(){
        List<MateriaPrima> materiaPrimaList = materiaPrimaRepository.findAll();
        return materiaPrimaList.stream().map(person -> mapToDTO(person)).collect(Collectors.toList());

    }

    //TODO: Excepción
    public MateriaPrimaDTO retrieveById (int id) {
        Optional<MateriaPrima> materiaPrima = findById(id);
        if (materiaPrima.isEmpty()){
            throw new ResourceNotFoundException("Materia prima no encontrada");
        }
        return mapToDTO(materiaPrima.get());
    }
    public Optional<MateriaPrima> findById(Integer materiaPrimaId) {
        return materiaPrimaRepository.findById(materiaPrimaId);
    }

    public void incrementarStock(double cantidad, MateriaPrima materiaPrima) {
        materiaPrima.incrementarStock(cantidad);
        materiaPrimaRepository.save(materiaPrima);
    }

    public void decrementarStock(double cantidad, MateriaPrima materiaPrima) {
        materiaPrima.decrementarStock(cantidad);
        materiaPrimaRepository.save(materiaPrima);
    }

    public void cargarPrecio(double precioUnitario, MateriaPrima materiaPrima) {
        materiaPrima.cargarPrecio(precioUnitario);
        materiaPrimaRepository.save(materiaPrima);
    }
/*
    public void delete(Integer materiaPrimaId) {
        materiaPrimaRepository.deleteById(materiaPrimaId);
    }
*/

    public void modify(Integer materiaPrimaId, Map<String,Object> fieldsToModify) {
        Optional<MateriaPrima> materiaPrima = findById(materiaPrimaId);
        checkForExistingMateriaPrima(materiaPrimaId);

        MateriaPrima materiaPrimaToModify = materiaPrima.get();
        fieldsToModify.forEach((key,value)-> materiaPrimaToModify.modifyAttributeValue(key,value));
        materiaPrimaRepository.save(materiaPrimaToModify);
    }

    private MateriaPrimaDTO mapToDTO(MateriaPrima materiaPrima) {
        MateriaPrimaDTO materiaPrimaDTO = new MateriaPrimaDTO(materiaPrima.getNombre(),
                materiaPrima.getStock(), materiaPrima.getPrecio());
        materiaPrimaDTO.setId(materiaPrima.getId());

        return materiaPrimaDTO;
    }

    private MateriaPrima mapToEntity(MateriaPrimaDTO materiaPrimaDTO) {

        MateriaPrima materiaPrima = new MateriaPrima(materiaPrimaDTO.getNombre(),
                materiaPrimaDTO.getSotck(), materiaPrimaDTO.getPrecio());

        return  materiaPrima;
    }

    private void checkForExistingMateriaPrima(String nombre)  {
        if (materiaPrimaRepository.existsByNombre(nombre)) {
            throw new ExistingResourceException(); //TODO:excepciones
        }
    }
    private void checkForExistingMateriaPrima(Integer materiaPrimaId)  {
        if (!materiaPrimaRepository.existsById(materiaPrimaId)) {
            throw new ResourceNotFoundException("La materia prima no existe."); //TODO:excepciones
        }
    }



}
