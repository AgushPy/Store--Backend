package com.ag.store.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ag.store.domain.Product;
import com.ag.store.domain.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public List<Product> getAll(){
		return productRepository.getAll();
	}
	
	public Optional<Product> getProduct(int productId){
		return productRepository.getProduct(productId);
	}
	
	public Optional<List<Product>> getByCategory(int categoryId){
		return productRepository.getByCategory(categoryId);
	}
	
	public Product save(Product product) {
		return productRepository.save(product);
	}
	
	public boolean delete(int productId) {
		return getProduct(productId).map(product -> {
			productRepository.delete(productId);
			return true;
		}).orElse(false);

//		if(getProduct(productId).isPresent()) {
//			productRepository.delete(productId);
//			return true;
//		}else{
//			return false;
//		}
	}
	
	public Optional<List<Product>> getAllProductsFilters(Boolean freeShipping,Integer discount,double precio){
//	    List<Product> products = productRepository.getAll();
	    Optional<List<Product>> products = productRepository.getListByCategory(freeShipping, discount, precio);
	    return products;
	}
	
	
}
