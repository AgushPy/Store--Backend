package com.ag.store.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ag.store.domain.Product;
import com.ag.store.domain.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:3000")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/all")
	@ApiOperation("Con este metodo te traes todos los productos que hay registrados")
	@ApiResponse(code = 200,message = "OK")
	public ResponseEntity<List<Product>> getAll(){
		return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
	}
	
	@GetMapping("/{productId}")
	@ApiOperation("Buscar un producto por id")
	@ApiResponses({
	    @ApiResponse(code = 200,message = "OK"),
	    @ApiResponse(code = 404,message = "Producto No encontrado")
	})
	public ResponseEntity<Product> getProduct(@ApiParam(value="La id de el producto",required = true,example = "7")@PathVariable("id") int  productId){
		return productService.getProduct(productId)
				.map(product ->new ResponseEntity<>(product,HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
		return productService.getByCategory(categoryId)
				.map(products -> new ResponseEntity<>(products,HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity delete(@PathVariable("id") int productId) {
		if(productService.delete(productId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
