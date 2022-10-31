package com.ag.store.persistence.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.ag.store.domain.Product;
import com.ag.store.persistence.entity.Producto;

//@Component
@Mapper(componentModel = "spring",uses = {CategoryMapper.class})
//@Mapper
public interface ProductMapper {
	@Mappings({
		@Mapping(source = "idProducto",target = "productId"),
		@Mapping(source = "nombre",target = "name"),
		@Mapping(source = "idCategoria",target = "categoryId"),
		@Mapping(source = "precioVenta",target = "price"),
		@Mapping(source = "cantidadStock",target = "stock"),
		@Mapping(source = "estado",target = "active"),
		@Mapping(source = "categoria",target = "category"),
		@Mapping(source = "envioGratis",target = "freeShipping"),
		@Mapping(source = "porcentajeDescuento" ,target = "discount")
		
	})
	Product toProduct(Producto producto);
	List<Product> toProducts(List<Producto> products);
	
	@InheritInverseConfiguration
	@Mapping(target = "codigoBarras",ignore = true)
	Producto toProducto(Product product);
}
