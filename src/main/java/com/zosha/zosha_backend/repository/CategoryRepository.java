package com.zosha.zosha_backend.repository;

import com.zosha.zosha_backend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}