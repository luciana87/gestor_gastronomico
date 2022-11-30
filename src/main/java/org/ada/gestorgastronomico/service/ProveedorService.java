package org.ada.gestorgastronomico.service;

import org.ada.gestorgastronomico.dto.ProveedorDTO;
import org.ada.gestorgastronomico.entity.Proveedor;
import org.ada.gestorgastronomico.exceptions.ExistingResourceException;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.ada.gestorgastronomico.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorService {
    private final ProveedorRepository proveedorRepository;
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public ProveedorDTO create(ProveedorDTO proveedorDTO)  {

        checkForExistingProveedor(proveedorDTO.getCuit());
        Proveedor proveedor = mapToEntity(proveedorDTO);
        proveedor = proveedorRepository.save(proveedor);

        return proveedorDTO;
    }

    public List<ProveedorDTO> retrieveAll(){
        List<Proveedor> proveedores = proveedorRepository.findAll();
        return proveedores.stream().map(proveedor -> mapToDTO(proveedor)).collect(Collectors.toList());
    }

    //TODO Excepción
    public ProveedorDTO retrieveById(String proveedorId) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(proveedorId);
        if (proveedor.isEmpty()){
            throw new ResourceNotFoundException("Proveedor no encontrado.");
        }
        return mapToDTO(proveedor.get());
    }

    public Optional<Proveedor> findById(String cuitProveedor) {
        return proveedorRepository.findById(cuitProveedor);
    }

    public void delete(String cuitProveedor) {
        proveedorRepository.deleteById(cuitProveedor);
    }

    public void replace(String cuitProveedor, ProveedorDTO proveedorDTO) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(cuitProveedor);
        if (proveedor.isEmpty()){
            throw new ResourceNotFoundException("Proveedor no encontrado.");
        }
        Proveedor proveedorToReplace = proveedor.get();
        proveedorToReplace.setNombre(proveedorDTO.getNombre());
        proveedorToReplace.setEmail(proveedorDTO.getEmail());
        proveedorToReplace.setDireccion(proveedorDTO.getDireccion());
        proveedorToReplace.setTelefono(proveedorDTO.getTelefono());
        proveedorRepository.save(proveedorToReplace);
    }

    public void modify(String cuitProveedor, Map<String, Object> fieldsToModify) {
        Optional<Proveedor> proveedor = proveedorRepository.findById(cuitProveedor);
        if (proveedor.isEmpty()){
            throw new ResourceNotFoundException("Proveedor no encontrado.");
        }
        Proveedor proveedorToModify = proveedor.get();
        fieldsToModify.forEach((key,value)-> proveedorToModify.modifyAttributeValue(key,value));
        proveedorRepository.save(proveedorToModify);
    }

    private ProveedorDTO mapToDTO(Proveedor proveedor) {
        ProveedorDTO proveedorDTO = new ProveedorDTO(proveedor.getCuit(),
                proveedor.getNombre(), proveedor.getEmail(), proveedor.getTelefono(),
                proveedor.getDireccion());

        return proveedorDTO;
    }


    private Proveedor mapToEntity(ProveedorDTO proveedorDTO) {
        Proveedor proveedor = new Proveedor(proveedorDTO.getCuit(),
                proveedorDTO.getNombre(), proveedorDTO.getEmail(),
                proveedorDTO.getTelefono(), proveedorDTO.getDireccion());

        return proveedor;
    }
    private void checkForExistingProveedor(String cuitProveedor)  {
        if (proveedorRepository.existsById(cuitProveedor)) {
            throw new ExistingResourceException(); //TODO:excepciones
        }
    }

    public void deleteAll() {
        proveedorRepository.deleteAll();
    }
}
