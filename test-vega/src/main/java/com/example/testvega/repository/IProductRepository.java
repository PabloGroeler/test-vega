package com.example.testvega.repository;

import com.example.testvega.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    class Specifications {
        public static Specification<Product> containsDescription(String description) {
            return (root, query, cb) -> cb.like(root.get("description"), "%" + description + "%");
        }
    }

}
