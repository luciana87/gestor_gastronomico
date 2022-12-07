package org.ada.gestorgastronomico.service;

import org.ada.gestorgastronomico.dto.PedidoAlProveedorDTO;
import org.ada.gestorgastronomico.entity.ItemPedido;
import org.ada.gestorgastronomico.entity.PedidoAlProveedor;
import org.ada.gestorgastronomico.entity.Proveedor;
import org.ada.gestorgastronomico.exceptions.EmptyFiledException;
import org.ada.gestorgastronomico.exceptions.ExistingResourceException;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.ada.gestorgastronomico.repository.PedidoAlProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoAlProveedorService {

    private final PedidoAlProveedorRepository pedidoAlProveedorRepository;
    private final ProveedorService proveedorService;
    private final ItemPedidoService itemPedidoService;

    public PedidoAlProveedorService(PedidoAlProveedorRepository pedidoAlProveedorRepository, ProveedorService proveedorService, ItemPedidoService itemPedidoService) {
        this.pedidoAlProveedorRepository = pedidoAlProveedorRepository;
        this.proveedorService = proveedorService;
        this.itemPedidoService = itemPedidoService;
    }

    // Método create para que cree un pedido con su lista de ítems.

    //TODO: Excepción a tratar
    public void create2(String proveedorId, List<PedidoAlProveedorDTO> pedidosAlProveedorDTO) {
        Optional<Proveedor> proveedor = proveedorService.findById(proveedorId); //Obtengo el proveedor con el id que recibí por la URL
        if (proveedor.isEmpty()){   //Verifico si me devolvió un proveedor vacío
            throw new ResourceNotFoundException("El proveedor no existe.");
        }

        //Uso un foreach y no un stream porque necesito realizar más acciones por cada elemento de la lista de pedidos
        for (PedidoAlProveedorDTO pedidoAlProveedorDTO: pedidosAlProveedorDTO) {
            PedidoAlProveedor pedidoAlProveedor = mapToEntity(pedidoAlProveedorDTO, proveedor.get()); //Mapeo cada pedidoDTO a Entidad, y le envío el proveedor para agregarlo
            pedidoAlProveedorRepository.save(pedidoAlProveedor); //Guardo cada pedido de la lista de pedidos

            if (!CollectionUtils.isEmpty(pedidoAlProveedorDTO.getItems())) { //Si la lista de ítems que me mandan no está vacia, la cargo, y si està vacìa deberìa lanzar excepcion porque no hay pedido sin items
                List<ItemPedido> items = itemPedidoService.create(pedidoAlProveedorDTO.getItems(), pedidoAlProveedor); //Creo la lista de ítems de cada pedido con la información del body (la lista de ítems, y el pedido guardado)
                calcularMontoTotal (items, pedidoAlProveedor); //Calculo el monto total en base a los ìtems y lo setteo
                pedidoAlProveedorRepository.save(pedidoAlProveedor);
            }
        }
    }

    public List<PedidoAlProveedorDTO> retrieveByProveedor(String cuitProveedor) {  //Retorna todos los pedidos de un proveedor específico según el campo cuit_proveedor de la tabla Pedidos (FK)

        Optional<Proveedor> proveedor = proveedorService.findById(cuitProveedor);
        List<PedidoAlProveedor> pedidosObtenidos = pedidoAlProveedorRepository.findByProveedor(proveedor.get());

        return mapToDTOS(pedidosObtenidos);
    }

    public List<PedidoAlProveedorDTO> retrieveAll() {
        List<PedidoAlProveedor> pedidosObtenidos = pedidoAlProveedorRepository.findAll();
        return mapToDTOS(pedidosObtenidos);
    }

    public PedidoAlProveedorDTO retrieveById(Integer pedidoId) {
        Optional<PedidoAlProveedor> pedidoObtenido = pedidoAlProveedorRepository.findById(pedidoId);
        if (pedidoObtenido.isEmpty()){
            throw new ResourceNotFoundException("Pedido no encontrado.");
        }
        return mapToDTO(pedidoObtenido.get());
    }

    public void delete(Integer pedidoAlProveedorId) {
        pedidoAlProveedorRepository.deleteById(pedidoAlProveedorId);
    }

    //@Transactional
    public void deleteByProveedor(String cuitProveedor) {
        Optional<Proveedor> proveedor = proveedorService.findById(cuitProveedor);
        pedidoAlProveedorRepository.deleteAll(proveedor.get().getPedidos());
    }

    public void modify(Integer pedidoAlProveedorId, Map<String, Object> fieldsToModify) {
        Optional<PedidoAlProveedor> pedidoAlProveedor = pedidoAlProveedorRepository.findById(pedidoAlProveedorId);
        checkForExistingPedido(pedidoAlProveedorId);

        PedidoAlProveedor pedidoToModify = pedidoAlProveedor.get();
        fieldsToModify.forEach((key,value)-> pedidoToModify.modifyAttributeValue(key,value));
        pedidoAlProveedorRepository.save(pedidoToModify);
    }

    private void checkForExistingPedido (Integer pedidoId)  {
        if (!pedidoAlProveedorRepository.existsById(pedidoId)) {
            throw new ResourceNotFoundException("El pedido al proveedor ingresado no existe.");
        }
    }

    private void validateDTO(List<PedidoAlProveedorDTO> pedidosAlProveedorDTO) {
        for (PedidoAlProveedorDTO pedidoDTO: pedidosAlProveedorDTO) {
            if (pedidoDTO.getItems() == null || pedidoDTO.getItems().isEmpty()){
                throw new EmptyFiledException("ítems");
            }
        }
    }

    private PedidoAlProveedorDTO mapToDTO(PedidoAlProveedor pedidoAlProveedor) {
        PedidoAlProveedorDTO pedidoAlProveedorDTO = new PedidoAlProveedorDTO(pedidoAlProveedor.getFecha().toString(),
                pedidoAlProveedor.getMontoTotal(), pedidoAlProveedor.getEstado(), itemPedidoService.mapToDTOS(pedidoAlProveedor.getItems())); //Mapeo de entidad a lista DTO //TODO modificar el null;

        pedidoAlProveedorDTO.setNumero(pedidoAlProveedor.getNumero());

        return pedidoAlProveedorDTO;
    }

    private List<PedidoAlProveedorDTO> mapToDTOS(List<PedidoAlProveedor> pedidos) {
        return pedidos.stream().map(pedidoAlProveedor -> mapToDTO(pedidoAlProveedor)).collect(Collectors.toList());
    }

    private PedidoAlProveedor mapToEntity(PedidoAlProveedorDTO pedidoAlProveedorDTO, Proveedor proveedor) {
        PedidoAlProveedor pedidoAlProveedor = new PedidoAlProveedor(pedidoAlProveedorDTO.getEstado(), proveedor); //el montoTotal se inicializa en 0
        return pedidoAlProveedor;
    }

    private void calcularMontoTotal(List<ItemPedido> items, PedidoAlProveedor pedidoAlProveedor) {
        double montoCalculado = 0;
        for (ItemPedido item : items) {
            montoCalculado+= item.getPrecioUnitario() * item.getCantidad();
        }
        pedidoAlProveedor.setMontoTotal(montoCalculado);
    }
}
