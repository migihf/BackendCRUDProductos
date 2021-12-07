package com.mintic.productos.servicios;

import java.util.List;

import com.mintic.productos.modelo.Producto;

public interface ProductoServicio {
	// Crear
	Producto crearProducto(Producto producto);
	//Actualizar
	Producto updateProducto(Producto producto);
	//Listado de Productos
	List<Producto> getAllProducto();
	//Buscar Producto por _id
	Producto getProductoById(String productoId);
	//Buscar Producto por código
	Producto buscarByCodigo(int codigo);
	//Eliminar un Producto
	void deleteProducto(String productoId);
}