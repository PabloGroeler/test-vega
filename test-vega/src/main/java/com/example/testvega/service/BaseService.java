package com.example.testvega.service;

import org.springframework.data.jpa.domain.Specification;

public class BaseService<T> {

    protected Specification<T> getSpecification(Specification<T> specification) {
        if (specification == null) {
            return Specification.where(null);
        }

        return specification;
    }
}
