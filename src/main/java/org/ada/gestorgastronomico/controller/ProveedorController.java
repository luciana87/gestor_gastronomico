package org.ada.gestorgastronomico.controller;

import org.ada.gestorgastronomico.dto.ProveedorDTO;
import org.ada.gestorgastronomico.service.ProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping (path = "/proveedores")
public class ProveedorController {

    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody ProveedorDTO proveedorDTO){
            ProveedorDTO createdProveedorDTO = proveedorService.create(proveedorDTO);
            return new ResponseEntity(proveedorDTO.getCuit(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve(){
        return new ResponseEntity(proveedorService.retrieveAll(),HttpStatus.OK);
    }


    @GetMapping("/{proveedorId}")
    public ResponseEntity retrieveById (@PathVariable String proveedorId) {
            ProveedorDTO proveedorDTO = proveedorService.retrieveById(proveedorId);
            return new ResponseEntity(proveedorDTO, HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity deleteAll (){
        proveedorService.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{cuitProveedor}")
    private ResponseEntity deleteById(@PathVariable String cuitProveedor){
        proveedorService.delete(cuitProveedor);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{cuitProveedor}")
    private ResponseEntity replace(@PathVariable String cuitProveedor, @RequestBody ProveedorDTO proveedorDTO){
        proveedorService.replace(cuitProveedor, proveedorDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping("/{cuitProveedor}")
    private ResponseEntity modify(@PathVariable String cuitProveedor, @RequestBody Map<String, Object> fieldsToModify) {
        proveedorService.modify(cuitProveedor, fieldsToModify);
        return new ResponseEntity(HttpStatus.OK);
    }
}