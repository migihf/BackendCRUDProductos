package com.mintic.productos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mintic.productos.modelo.Producto;
import com.mintic.productos.servicios.ProductoServicio;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RequestMapping("/api/productos")
public class ProductoControlador {
	@Autowired
	ProductoServicio productoServicio;
	
	@GetMapping("/productos")
	public ResponseEntity<List<Producto>> getAllProductos(){
		return ResponseEntity.ok().body(productoServicio.getAllProducto());
	}

	@GetMapping("/productos/{id}")
	public ResponseEntity<Producto> getProductoById(@PathVariable String id) {
		return ResponseEntity.ok().body(productoServicio.getProductoById(id));
	}

	@GetMapping("/buscar/{codigo}")
	public ResponseEntity<Producto> getProductoByCodigo(@PathVariable int codigo) {
		Producto productoData = productoServicio.buscarByCodigo(codigo);
		if (productoData != null) {
			return ResponseEntity.ok().body(productoData);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/productos")
	public ResponseEntity < Producto > crearProducto(@RequestBody Producto producto){
		return ResponseEntity.ok().body(this.productoServicio.crearProducto(producto));
	}

	@PutMapping("/productos/{id}")
	public ResponseEntity<Producto> updateProducto(@PathVariable String id, @RequestBody Producto producto){
		producto.set_id(id);
		return ResponseEntity.ok().body(this.productoServicio.updateProducto(producto));
	}

	@DeleteMapping("/productos/{id}")
	public HttpStatus deleteProducto(@PathVariable String id) {
		this.productoServicio.deleteProducto(id);
		return HttpStatus.OK;
	}
}