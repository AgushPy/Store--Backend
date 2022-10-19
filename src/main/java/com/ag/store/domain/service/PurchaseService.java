package com.ag.store.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ag.store.domain.Purchase;
import com.ag.store.persistence.CompraRepository;

@Service
public class PurchaseService {

    @Autowired
    private CompraRepository compraRepository;
    
    public List<Purchase> getAll(){
        System.out.println("Hola");
        return compraRepository.getAll();
    }
    
    public Optional<List<Purchase>> getByClient(String clientId){
        return compraRepository.getByClient(clientId);
    }
    
    public Purchase save(Purchase purchase) {
        return compraRepository.save(purchase);
    }
}
