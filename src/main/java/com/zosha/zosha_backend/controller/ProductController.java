package com.zosha.zosha_backend.controller;

import com.zosha.zosha_backend.dto.ProductRequestDTO;
import com.zosha.zosha_backend.dto.ProductResponseDTO;
import com.zosha.zosha_backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(
        origins = {
                "http://localhost:5173",
                "http://localhost:5176"
        }
)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // CREATE
    @PostMapping
    public ProductResponseDTO createProduct(
            @RequestBody ProductRequestDTO dto) {

        return productService.createProduct(dto);
    }

    // GET ALL
    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {

        return productService.getAllProducts();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(
            @PathVariable Long id) {

        return productService.getProductById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequestDTO dto) {

        return productService.updateProduct(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {

        productService.deleteProduct(id);

        return "Product deleted successfully";
    }
}