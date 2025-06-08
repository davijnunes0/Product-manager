package com.davijnunes.app.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.davijnunes.app.models.Product;

@Repository
public  interface ProductRepository extends JpaRepository<Product,UUID> {
    
}
