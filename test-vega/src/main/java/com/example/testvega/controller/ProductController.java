package com.example.testvega.controller;

import com.example.testvega.entity.Product;
import com.example.testvega.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping()
    public ResponseEntity findProdutos(@RequestParam(name = "filter", required = false, defaultValue = "") String filter) {
        return service.findByName(filter);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody() Product product) {
        return service.insertOrUpdate(product);
    }

    @PutMapping
    public ResponseEntity put(@RequestBody() Product product) {
        return service.insertOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        return service.delete(id);
    }
}
