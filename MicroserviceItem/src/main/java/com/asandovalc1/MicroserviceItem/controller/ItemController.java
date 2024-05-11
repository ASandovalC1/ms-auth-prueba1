package com.asandovalc1.MicroserviceItem.controller;

import com.asandovalc1.MicroserviceItem.models.Item;
import com.asandovalc1.MicroserviceItem.models.Product;
import com.asandovalc1.MicroserviceItem.models.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getItems(){
        return ResponseEntity.ok(this.itemService.findAll());
    }

    @GetMapping("/{id}/quantity/{quantity}")
    public ResponseEntity<Item> getItemDetail(@PathVariable Long id, @PathVariable Integer quantity){
        Item newItem = this.itemService.findById(id,quantity);
        System.out.println(newItem.getTotal());
        return ResponseEntity.ok(newItem);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> save(@RequestBody Product product){
        return ResponseEntity.ok(itemService.save(product));
    }

    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> edit(@RequestBody Product product){
        return ResponseEntity.ok(itemService.update(product));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(itemService.delete(id));
    }


}
