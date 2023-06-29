package com.example.ProductosService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ProductosService.model.Producto;



public interface ProductoRepository extends MongoRepository<Producto, String> {

}

