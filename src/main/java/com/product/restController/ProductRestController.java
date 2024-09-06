package com.product.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.DAO.ProductDAO;
import com.product.model.Product;

@CrossOrigin("*")
@RestController
@RequestMapping("/product")
public class ProductRestController {
    @Autowired
    ProductDAO prodDao;

    @GetMapping("")
    public ResponseEntity<List<Product>> getAll(Model model) {
        return ResponseEntity.ok(prodDao.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id) {
        if (!prodDao.existsById(id)) {
            return ResponseEntity.status(404).body("ID not found");

        }
        return ResponseEntity.ok(prodDao.findById(id).get());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProd(@RequestBody Product product) {
        if (product.getId() != null && prodDao.existsById(product.getId())) {
            return ResponseEntity.badRequest().body("Product with this ID already exists");
        }

        // product.setId(null);
        prodDao.save(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProd(@PathVariable("id") Long id, @RequestBody Product product) {
        Optional<Product> existingProductOpt = prodDao.findById(id);
        if (existingProductOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product existingProduct = existingProductOpt.get();

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCreateDate(product.getCreateDate());
        existingProduct.setDescription(product.getDescription());

        Product updatedProduct = prodDao.save(existingProduct);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProd(@PathVariable("id") Long id) {
        if (!prodDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        prodDao.deleteById(id);
        return ResponseEntity.ok().build();

    }
}
