package com.davijnunes.app.services;

import com.davijnunes.app.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.davijnunes.app.exceptions.ResourceNotFoundException;
import com.davijnunes.app.models.Product;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> listAll(){
        return productRepository.findAll();
    }

    public Product searchById(UUID productId){
        return productRepository.findById(productId).
        orElseThrow(() -> new ResourceNotFoundException("Não foi possivel achar o produto com esse UUID específicado."));
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(UUID productId){

        if(!productRepository.existsById(productId)){
            throw new ResourceNotFoundException("Esse produto com esse UUID específicado não existe");
        }

        productRepository.deleteById(productId);
    }
    
}
