package org.ada.gestorgastronomico.service;

import org.ada.gestorgastronomico.dto.ItemPedidoDTO;
import org.ada.gestorgastronomico.entity.ItemPedido;
import org.ada.gestorgastronomico.entity.MateriaPrima;
import org.ada.gestorgastronomico.entity.PedidoAlProveedor;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.ada.gestorgastronomico.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final MateriaPrimaService materiaPrimaService;


    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository, MateriaPrimaService materiaPrimaService) {
        this.itemPedidoRepository = itemPedidoRepository;
        this.materiaPrimaService = materiaPrimaService;
    }

    //TODO: Excepción a tratar
    public List<ItemPedido> create(List<ItemPedidoDTO> itemsDTO, PedidoAlProveedor pedidoAlProveedor){

        //Mapeo la lista de ítemsDTO a una lista de ítems entity
        List<ItemPedido> items = new ArrayList<>(); //Creo la lista de entidades
        for (ItemPedidoDTO itemPedidoDTO: itemsDTO) {
            Optional<MateriaPrima> materiaPrima = materiaPrimaService.findById(itemPedidoDTO.getMateriaPrimaId()); // Obtengo la materia prima
            if (materiaPrima.isEmpty()){ // Verifico si encontró esa materia prima o no
                throw new ResourceNotFoundException("Materia prima no encontrada"); //Si no la encontró, lanzo una excepción
            } else {
                materiaPrimaService.incrementarStock(itemPedidoDTO.getCantidad(), materiaPrima.get()); // Modifico el stock de la materia prima
                materiaPrimaService.cargarPrecio(itemPedidoDTO.getPrecioUnitario(), materiaPrima.get()); //Setteo el precio de la materia prima segùn valor envìado en ìtem pedido
            }

            ItemPedido itemPedido = mapToEntity(itemPedidoDTO, pedidoAlProveedor, materiaPrima.get()); //Mapeo cada ìtem
            itemPedidoRepository.save(itemPedido);// Lo guardo en la DB y le genero un ID
            items.add(itemPedido); // Agrego el ítem a la lista de ítems del pedido
        }
        return items;
    }
    public List<ItemPedidoDTO> mapToDTOS(List<ItemPedido> items) {
        return items.stream().map(itemPedido -> mapToDTO(itemPedido)).collect(Collectors.toList());
    }


    private ItemPedidoDTO mapToDTO(ItemPedido itemPedido) {
        ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO(itemPedido.getPedidoAlProveedor().getNumero(), itemPedido.getId(), itemPedido.getCantidad(),
                itemPedido.getPrecioUnitario(), itemPedido.getMateriaPrima().getNombre(), itemPedido.getMateriaPrima().getId());

        return itemPedidoDTO;
    }

    private ItemPedido mapToEntity(ItemPedidoDTO itemPedidoDTO, PedidoAlProveedor pedidoAlProveedor, MateriaPrima materiaPrima) {

        ItemPedido itemPedido = new ItemPedido(itemPedidoDTO.getCantidad(), itemPedidoDTO.getPrecioUnitario(), pedidoAlProveedor, materiaPrima);

        return itemPedido;
    }



}
