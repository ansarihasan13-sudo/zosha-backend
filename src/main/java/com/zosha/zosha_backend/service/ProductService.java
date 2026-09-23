package com.zosha.zosha_backend.service;

import com.zosha.zosha_backend.dto.ProductRequestDTO;
import com.zosha.zosha_backend.dto.ProductResponseDTO;
import com.zosha.zosha_backend.entity.Category;
import com.zosha.zosha_backend.entity.Product;
import com.zosha.zosha_backend.repository.CategoryRepository;
import com.zosha.zosha_backend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // CREATE
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

        Category category = categoryRepository
                .findById(dto.getCategoryId())
                .orElse(null);

        if (category == null) {
            return null;
        }

        Product product = new Product();

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setImageUrl(dto.getImageUrl());
        product.setCategory(category);
        product.setActive(dto.isActive());

        Product savedProduct = productRepository.save(product);

        return convertToDTO(savedProduct);
    }

    // GET ALL
    public List<ProductResponseDTO> getAllProducts() {

        return productRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // GET BY ID
    public ProductResponseDTO getProductById(Long id) {

        Product product = productRepository
                .findById(id)
                .orElse(null);

        if (product == null) {
            return null;
        }

        return convertToDTO(product);
    }

    // UPDATE
    public ProductResponseDTO updateProduct(
            Long id,
            ProductRequestDTO dto) {

        Product existingProduct = productRepository
                .findById(id)
                .orElse(null);

        if (existingProduct == null) {
            return null;
        }

        Category category = categoryRepository
                .findById(dto.getCategoryId())
                .orElse(null);

        if (category == null) {
            return null;
        }

        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setImageUrl(dto.getImageUrl());
        existingProduct.setCategory(category);
        existingProduct.setActive(dto.isActive());

        Product updatedProduct =
                productRepository.save(existingProduct);

        return convertToDTO(updatedProduct);
    }

    // DELETE
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    // ENTITY → DTO
    // ENTITY → DTO
    private ProductResponseDTO convertToDTO(Product product) {

        ProductResponseDTO dto = new ProductResponseDTO();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setImageUrl(product.getImageUrl());

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getId());
            dto.setCategoryName(product.getCategory().getName());
        }

        dto.setActive(product.isActive());

        return dto;
    }
}