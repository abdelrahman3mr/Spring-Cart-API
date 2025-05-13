package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.CreateProductRequest;
import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getProducts(
            @RequestParam(required = false, defaultValue = " ", name = "categoryId") Byte categoryId
    ) {

        if (categoryId == null) {
            var products = productRepository.findAllWithCategory().stream().map(productMapper::toProduct).toList();
            return ResponseEntity.ok(products);
        }
        var productsFiltered = productRepository.findByCategory_Id(categoryId).stream().map(productMapper::toProduct).toList();
        if (productsFiltered.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productsFiltered);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productMapper.toProduct(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(
            @RequestBody ProductDto request,
            UriComponentsBuilder uriBuilder) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
        if (category == null) return  ResponseEntity.notFound().build();

        Product product = productMapper.toEntity(request);
        product.setCategory(category);

        productRepository.save(product);
        ProductDto productDto = productMapper.toProduct(product);

        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(productDto.getId()).toUri();

        return ResponseEntity.created(uri).body(productDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable(name = "id") Long id,
                                                    @RequestBody ProductDto request) {

        Product product = productRepository.findById(id).orElse(null);
        if (product == null) return  ResponseEntity.notFound().build();


        productMapper.updateProduct(product, request);

        if (!product.getCategory().getId().equals(request.getCategoryId())) {
            Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);
            if (category == null) return ResponseEntity.notFound().build();
            product.setCategory(category);
        }

        productRepository.save(product);
        ProductDto productDto = productMapper.toProduct(product);
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable(name = "id") Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) return  ResponseEntity.notFound().build();
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
