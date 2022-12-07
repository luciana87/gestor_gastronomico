package org.ada.gestorgastronomico.service;

import org.ada.gestorgastronomico.dto.ProductoDTO;
import org.ada.gestorgastronomico.entity.Ingrediente;
import org.ada.gestorgastronomico.entity.Producto;
import org.ada.gestorgastronomico.exceptions.ResourceNotFoundException;
import org.ada.gestorgastronomico.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final IngredienteService ingredienteService;

    public ProductoService(ProductoRepository productoRepository, IngredienteService ingredienteService) {
        this.productoRepository = productoRepository;
        this.ingredienteService = ingredienteService;
    }

    public void create(List<ProductoDTO> productosDTO){
        for (ProductoDTO productoDTO: productosDTO) {
            Producto producto = mapToEntity(productoDTO);
            productoRepository.save(producto);

            if (!CollectionUtils.isEmpty(productoDTO.getIngredientes())){
                List<Ingrediente> ingredientes = ingredienteService.create(productoDTO.getIngredientes(), producto);
                calcularPrecio(ingredientes, producto);
                productoRepository.save(producto);
            }
        }
    }


   public List<ProductoDTO> retrieveAll() {
        List<Producto> productos = productoRepository.findAll();
        return mapToDTOS(productos);
    }

    public ProductoDTO retrieveById(Integer productoId) {
       Optional<Producto> producto = findById(productoId);
       if (producto.isEmpty()){
           throw new ResourceNotFoundException("Producto no encontrado");
       }
       return mapToDTO(producto.get());
    }

    public void modify(int productoId, Map<String, Object> fieldsToModify) {
        Optional<Producto> producto = findById(productoId);
        checkForExistingProducto(productoId);

        Producto productoToModify = producto.get();
        fieldsToModify.forEach((key,value)-> productoToModify.modifyAttributeValue(key,value));
        productoRepository.save(productoToModify);
    }

    public Optional<Producto> findById(Integer id){
        return productoRepository.findById(id);
    }

    public void delete(Integer productoId) {
        productoRepository.deleteById(productoId);
    }

    private Producto mapToEntity(ProductoDTO productoDTO) {
        Producto producto = new Producto(productoDTO.getNombre());

        return producto;
    }

    private List<ProductoDTO> mapToDTOS(List<Producto> productos) {
        return productos.stream().map(producto -> mapToDTO(producto)).collect(Collectors.toList());
    }

    private ProductoDTO mapToDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO( producto.getNombre(),
                producto.getPrecio(), ingredienteService.mapToDTOS(producto.getIngredientes()));

        productoDTO.setCodigo(producto.getCodigo());

        return productoDTO;
    }

    private void checkForExistingProducto (Integer productoId)  {
        if (!productoRepository.existsById(productoId)) {
            throw new ResourceNotFoundException();
        }
    }

    private void calcularPrecio(List<Ingrediente> ingredientes, Producto producto) {
        double precioCalculado = 0;
        for (Ingrediente ingrediente:ingredientes) {
            precioCalculado+= ingrediente.getMateriaPrima().getPrecio() * ingrediente.getCantidad();
        }
        precioCalculado *= 1.20;
        producto.setPrecio(precioCalculado);
    }
}
