package com.example.testvega.service;

import com.example.testvega.entity.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.testvega.repository.IProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService extends BaseService {

    @Autowired
    IProductRepository repository;

    public ResponseEntity insertOrUpdate(Product product) {

        try {
            repository.save(product);
            return ResponseEntity.ok("Product has been inserted");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error on save product.");
        }
    }

    public ResponseEntity findById(Integer id) {

        Optional<Product> product = repository.findById(id);
        if (!product.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.");
        }

        return ResponseEntity.ok(product.get());
    }

    public ResponseEntity findByName(String name) {
        Specification specification = null;

        if(!StringUtils.isEmpty(name)) {
            specification = getSpecification(specification).and(IProductRepository.Specifications.containsDescription(name));
        }

        List products = repository.findAll(specification, Sort.by("description"));
        if (products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product(s) not found.");
        }

        return ResponseEntity.ok(products);
    }

    public ResponseEntity delete(Integer id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok("Product has been deleted");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error on delete product.");
        }
    }

}
