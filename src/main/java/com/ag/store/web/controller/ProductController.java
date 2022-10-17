package com.ag.store.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ag.store.domain.Product;
import com.ag.store.domain.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String saludar() {
		return "Hola";
	}
	
	@GetMapping("/all")
	public List<Product> getAll(){
		return productService.getAll();
	}
	
	@GetMapping("/{productId}")
	public Optional<Product> getProduct(@PathVariable("id") int  productId){
		return productService.getProduct(productId);
	}
	@GetMapping("/category/{categoryId}")
	public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
		return productService.getByCategory(categoryId);
	}
	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product product){
		return productService.save(null);
	}
	
	@DeleteMapping("/delete/{id}")
	public boolean 	delete(@PathVariable("id") int productId) {
		return productService.delete(productId);
	}
}
