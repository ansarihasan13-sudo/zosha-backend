package com.zosha.zosha_backend.repository;

import com.zosha.zosha_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
