package com.asandovalc1.app.controllers;

import com.asandovalc1.app.models.entity.Product;
import com.asandovalc1.app.models.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        return ResponseEntity.ok(this.productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(this.productService.findById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> save( @RequestBody Product product){
        if(!this.productService.exist(product.getId()))
        {
            return ResponseEntity.ok(this.productService.save(product));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> edit(@RequestBody Product product){
        if(this.productService.findById(product.getId()) != null && this.productService.exist(product.getId())){
            return ResponseEntity.ok(this.productService.save(product));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> delete (@PathVariable Long id){
        return ResponseEntity.ok(this.productService.deleteById(id));
    }
}
