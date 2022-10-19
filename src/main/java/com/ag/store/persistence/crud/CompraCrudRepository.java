package com.ag.store.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ag.store.persistence.entity.Compra;


public interface CompraCrudRepository extends CrudRepository<Compra, Integer>{
    Optional<List<Compra>> findByIdCliente(String idCliente);
}
