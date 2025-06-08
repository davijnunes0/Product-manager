package com.davijnunes.app.controllers;

import org.springframework.web.bind.annotation.RestController;
import com.davijnunes.app.dtos.ProductRecordDto;
import com.davijnunes.app.exceptions.ResourceNotFoundException;
import com.davijnunes.app.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.davijnunes.app.models.Product;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAllProduct(){
        return ResponseEntity.status(HttpStatus.OK).body(productService.listAll());
    }

   @GetMapping("/{id}")
   public ResponseEntity<Object> searchById(@PathVariable(value="id") UUID id) {
        Product product = productService.searchById(id);    
        if(product == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Produto com esse UUID não foi encontrado."));

        }

        return ResponseEntity.status(HttpStatus.OK).body(product);
   }
   
    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var product = new Product();
        BeanUtils.copyProperties(productRecordDto, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductRecordDto productRecordDto) {
        Product product = productService.searchById(id);
        if(product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResourceNotFoundException("Não foi possivel achar o produto com UUID específicado."));

        }
        BeanUtils.copyProperties(productRecordDto, product);
        return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(product));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value="id") UUID id)
   {
        try{
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }catch(ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    

        
}
