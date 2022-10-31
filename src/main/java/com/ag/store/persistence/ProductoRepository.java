package com.ag.store.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.ag.store.domain.Product;
import com.ag.store.domain.repository.ProductRepository;
import com.ag.store.persistence.crud.ProductoCrudRepository;
import com.ag.store.persistence.entity.Producto;
import com.ag.store.persistence.mapper.ProductMapper;

@Repository
public class ProductoRepository implements ProductRepository{
	@Autowired
	private ProductoCrudRepository productoCrudRepository;
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public List<Product> getAll(){
		List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
		return mapper.toProducts(productos);
	}

	
	@Override
	public void delete(int productId) {
		productoCrudRepository.deleteById(productId);
	}

	@Override
	public Optional<List<Product>> getByCategory(int categoryId) {
		// TODO Auto-generated method stub
		List<Producto> productos =  productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
		
		return Optional.of(mapper.toProducts(productos));
	}

	@Override
	public Optional<List<Product>> getScarseProducts(int quantity) {
		// TODO Auto-generated method stub
	Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
		return productos.map(prods ->mapper.toProducts(prods));
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		// TODO Auto-generated method stub
		return productoCrudRepository.findById(productId).map(prod -> mapper.toProduct(prod));
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		Producto producto = mapper.toProducto(product);
		return mapper.toProduct(productoCrudRepository.save(producto));
	}
	
	public Optional<List<Product>> getListByCategory(Boolean freeShipping,int discount){
	    Optional<List<Producto>> productos = productoCrudRepository.findByEnvioGratisAndPorcentajeDescuentoGreaterThanEqual(freeShipping, discount);
	    return productos.map(prods -> mapper.toProducts(prods));
	}
}
