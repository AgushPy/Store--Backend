package com.ag.store.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.ag.store.persistence.entity.Producto;
//@Component
public interface ProductoCrudRepository extends CrudRepository<Producto, Integer>{
	
	List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);
	Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock,boolean estado);
	Optional<List<Producto>> findByEnvioGratisAndPorcentajeDescuentoGreaterThanEqual(boolean envioGratis,int porcentajeDescuento);

}
