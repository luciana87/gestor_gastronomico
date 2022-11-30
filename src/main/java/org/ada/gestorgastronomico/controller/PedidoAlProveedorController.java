package org.ada.gestorgastronomico.controller;

import org.ada.gestorgastronomico.dto.PedidoAlProveedorDTO;
import org.ada.gestorgastronomico.service.PedidoAlProveedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/proveedores/{proveedorId}/pedidos")
public class PedidoAlProveedorController {

    private final PedidoAlProveedorService pedidoAlProveedorService;

    public PedidoAlProveedorController(PedidoAlProveedorService pedidoAlProveedorService) {
        this.pedidoAlProveedorService = pedidoAlProveedorService;
    }


    @PostMapping
    public ResponseEntity create (@PathVariable String proveedorId, @RequestBody List<PedidoAlProveedorDTO> pedidosAlProveedorDTO){

            pedidoAlProveedorService.create2(proveedorId, pedidosAlProveedorDTO);
            return new ResponseEntity(HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity retrieve () {
        return new ResponseEntity(pedidoAlProveedorService.retrieveAll(), HttpStatus.OK);
    }

    @GetMapping("/{cuitProveedor}")
    public ResponseEntity retrieveByProveedor (@PathVariable String cuitProveedor) {
        return new ResponseEntity(pedidoAlProveedorService.retrieveByProveedor(cuitProveedor), HttpStatus.OK);
    }

    @DeleteMapping
    private ResponseEntity deleteByProveedor(@PathVariable String proveedorId){
        pedidoAlProveedorService.deleteByProveedor(proveedorId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{pedidoAlProveedorId}")
    private ResponseEntity deleteById(@PathVariable Integer pedidoAlProveedorId){

        pedidoAlProveedorService.delete(pedidoAlProveedorId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PatchMapping ("/{pedidoAlProveedorId}")
    private ResponseEntity modify(@PathVariable Integer pedidoAlProveedorId, @RequestBody Map<String, Object> fieldsToModify){
                pedidoAlProveedorService.modify(pedidoAlProveedorId, fieldsToModify );
                return new ResponseEntity(HttpStatus.OK);
    }





}
