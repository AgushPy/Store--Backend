package com.ag.store.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ag.store.domain.Purchase;
import com.ag.store.domain.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    
    @Autowired
    private PurchaseService purchaseService;
    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable(name = "clientId")String cliendId){
        return purchaseService.getByClient(cliendId)
                .map(purchases -> new ResponseEntity<>(purchases,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestParam Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase),HttpStatus.OK);
    }
    
    
}
