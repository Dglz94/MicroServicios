package com.example.ProductosService.service;

import java.util.List;

import com.example.ProductosService.model.Producto;


public interface ProductoService {
	
	Producto saveProducto(Producto producto);

	List<Producto> getAllProductos();
	
	Producto getProductoById(String id);
	
	Producto updateProducto(Producto producto, String id);
	
	Producto deleteProducto(String id);
}
