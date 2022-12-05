package org.ada.gestorgastronomico.controller;

import org.ada.gestorgastronomico.dto.ProductoDTO;
import org.ada.gestorgastronomico.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/productos")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }


    @PostMapping
    public ResponseEntity create(@RequestBody List<ProductoDTO> productosDTO){
        productoService.create(productosDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity retrieve() {
        return new ResponseEntity(productoService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{productoId}")
    public ResponseEntity retrieveById(@PathVariable Integer productoId) {
        ProductoDTO productoDTO = productoService.retrieveById(productoId);
        return new ResponseEntity(productoDTO, HttpStatus.OK);
    }

    @PatchMapping("/{productoId}")
    public ResponseEntity modify(@PathVariable int productoId, @RequestBody Map<String, Object> fieldsToModify){
        productoService.modify(productoId, fieldsToModify);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{productoId}")
    public ResponseEntity deleteById(@PathVariable Integer productoId) {
        productoService.delete(productoId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
