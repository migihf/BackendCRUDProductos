package com.mintic.productos.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mintic.productos.dao.IProductoRepositorio;
import com.mintic.productos.excepcion.ResourceNotFoundException;
import com.mintic.productos.modelo.Producto;

@Service
@Transactional
public class ProductoServicioImpl implements ProductoServicio {

	@Autowired
	private IProductoRepositorio productoRepo;

	@Override
	public Producto crearProducto(Producto producto) {
		return productoRepo.save(producto);
	}

	@Override
	public Producto updateProducto(Producto producto) {
		// Se busca el cliente con el Id del objeto cliente recibido
		Optional<Producto> productoDb = this.productoRepo.findById(producto.get_id());
		
		if(productoDb.isPresent()) {
			// Se crea un objeto tipo Producto con los datos recuperados
			Producto productoUpdate = productoDb.get();
			// Se actualiza el valor de cada atributo con el get correspondiente de producto
			productoUpdate.set_id(producto.get_id());
			productoUpdate.setCodigoproducto(producto.getCodigoproducto());
			productoUpdate.setIvacompra(producto.getIvacompra());
			productoUpdate.setNitproveedor(producto.getNitproveedor());
			productoUpdate.setNombre_producto(producto.getNombre_producto());
			productoUpdate.setPrecio_compra(producto.getPrecio_compra());
			productoUpdate.setPrecio_venta(producto.getPrecio_venta());
			// Se actualizan los valores del objeto producto
			productoRepo.save(productoUpdate);
			return productoUpdate;
		} else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+producto.get_id());
		}
	}

	@Override
	public List<Producto> getAllProducto() {
		return productoRepo.findAll();
	}

	@Override
	public Producto getProductoById(String productoId) {
		Optional<Producto> productoDb = this.productoRepo.findById(productoId);
		if(productoDb.isPresent()) {
			return productoDb.get();
		} else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+productoId);
		}
	}

	@Override
	public Producto buscarByCodigo(int codigo) {
		Producto productoDb = this.productoRepo.findByCodigoproducto(codigo);
		if(productoDb != null) {
			return productoDb;
		} else {
			throw new ResourceNotFoundException("Registro no Encontrado con el código:"+codigo);
		}
	}

	@Override
	public void deleteProducto(String productoId) {
		Optional<Producto> productoDb = this.productoRepo.findById(productoId);
		if(productoDb.isPresent()) {
			this.productoRepo.delete(productoDb.get());
		} else {
			throw new ResourceNotFoundException("Registro no Encontrado con el Id:"+productoId);
		}
	}
}