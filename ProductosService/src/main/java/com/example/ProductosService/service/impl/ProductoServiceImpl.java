package com.example.ProductosService.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ProductosService.exception.ResourceNotFoundException;
import com.example.ProductosService.model.Producto;
import com.example.ProductosService.repository.ProductoRepository;
import com.example.ProductosService.service.ProductoService;



@Service
public class ProductoServiceImpl implements ProductoService {

		private ProductoRepository productoRepo;
		
		public ProductoServiceImpl(ProductoRepository productoRepo) {
			super();
			this.productoRepo = productoRepo;
		}
		
		@Override
		public Producto saveProducto(Producto producto) {
			return productoRepo.save(producto);
		}
		
		@Override
		public List<Producto> getAllProductos(){
			return productoRepo.findAll();
		}
		
		@Override
		public Producto getProductoById(String id) {
			
			return productoRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Producto", "Id", id));
		}
		
		@Override
		public Producto updateProducto(Producto producto, String id ) {
			
			Producto productoExistente = productoRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Producto", "Id", id));
			
			productoExistente.setNombre(producto.getNombre());
			productoExistente.setUnidadMedida(producto.getUnidadMedida());
			productoExistente.setPrecio(producto.getPrecio());
			productoExistente.setClave(producto.getClave());
			
			productoRepo.save(productoExistente);
			
			return productoExistente;
		}
		
		@Override 
		public Producto deleteProducto(String id) {
			Producto productoExistente = productoRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Producto", "Id", id));
			
			productoRepo.deleteById(id);
			
			return productoExistente;
		}
}
