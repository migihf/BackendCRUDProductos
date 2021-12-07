package com.mintic.productos.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mintic.productos.modelo.Producto;

@Repository
public interface IProductoRepositorio extends MongoRepository<Producto, String> {
	public Producto findByCodigoproducto(int codigoproducto);
}