package org.ada.gestorgastronomico.controller;

import org.ada.gestorgastronomico.dto.MateriaPrimaDTO;
import org.ada.gestorgastronomico.service.MateriaPrimaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/materias-primas")
public class MateriaPrimaController {

    private final MateriaPrimaService materiaPrimaService;

    public MateriaPrimaController(MateriaPrimaService materiaPrimaService) {
        this.materiaPrimaService = materiaPrimaService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody MateriaPrimaDTO materiaPrimaDTO){
        MateriaPrimaDTO createdMateriaPrimaDTO = materiaPrimaService.create(materiaPrimaDTO);
        return new ResponseEntity<>(createdMateriaPrimaDTO.getId(), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity retrieve(){
        return new ResponseEntity(materiaPrimaService.retrieveAll(),HttpStatus.OK);
    }


    @GetMapping("/{materiaPrimaId}")
    public ResponseEntity retrieveById (@PathVariable int materiaPrimaId) {
            MateriaPrimaDTO materiaPrimaDTO = materiaPrimaService.retrieveById(materiaPrimaId);
            return new ResponseEntity(materiaPrimaDTO, HttpStatus.OK);
    }
/*
    @DeleteMapping("/{materiaPrimaId}")
    //TODO: SQLIntegrityConstraintViolationException: Cannot delete or update a parent row:
    // a foreign key constraint fails (`proyectoada`.`item_pedido`, CONSTRAINT `fk_item_materia_prima`
    // FOREIGN KEY (`id_materia_prima`) REFERENCES `materia_prima` (`id`))
    
    private ResponseEntity deleteById(@PathVariable Integer materiaPrimaId){
            materiaPrimaService.delete(materiaPrimaId);
            return new ResponseEntity(HttpStatus.OK);
    }
*/

    @PatchMapping("/{materiaPrimaId}")
    private ResponseEntity modify(@PathVariable Integer materiaPrimaId, @RequestBody Map<String,Object> fieldsToModify){
        materiaPrimaService.modify(materiaPrimaId, fieldsToModify);
        return new ResponseEntity(HttpStatus.OK);
    }
}
