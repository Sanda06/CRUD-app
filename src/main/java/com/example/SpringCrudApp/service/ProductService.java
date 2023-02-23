package com.example.SpringCrudApp.service;

import com.example.SpringCrudApp.dto.CreateUpdateProductDTO;
import com.example.SpringCrudApp.entity.Product;
import com.example.SpringCrudApp.repository.ProductRepository;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CustomerService customerService;

    public Product createProduct(CreateUpdateProductDTO createUpdateProductDTO) {
        var customer = customerService.getCustomer(createUpdateProductDTO.getCustomerId());
        Product product = new Product(createUpdateProductDTO.getName(), createUpdateProductDTO.getCode(),
                createUpdateProductDTO.getPrice(), customer);
        return productRepository.save(product);
    }

    public Page<Product> getAllProducts(Pageable pageableParams) {
        return productRepository.findAll(pageableParams);
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());
    }

    public Product updateProduct(Long id, CreateUpdateProductDTO createUpdateProductDTO) {
        var customer = customerService.getCustomer(createUpdateProductDTO.getCustomerId());
        Product existingProduct = getProduct(id);
        existingProduct.setName(createUpdateProductDTO.getName());
        existingProduct.setCode(createUpdateProductDTO.getCode());
        existingProduct.setPrice(createUpdateProductDTO.getPrice());
        existingProduct.setCustomer(customer);
        return productRepository.save(existingProduct);
    }

    public void deleteById(Long id) {
        var product = getProduct(id);
        productRepository.delete(product);
    }
}
