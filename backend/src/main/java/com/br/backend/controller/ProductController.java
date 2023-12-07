package com.br.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.backend.dto.ProductDTO;
import com.br.backend.model.ProductModel;
import com.br.backend.service.ProductService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("product")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Endpoint para SALVAR produto 
    @PostMapping
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductDTO productDTO ) {
        if (productDTO.getName() == null || productDTO.getPrice() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); //futuramente adicionar tratamento de exceções nos bad_request
        }

        ProductModel productModel = productService.saveProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productModel);
    }


    @GetMapping 
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id) {
        Optional<ProductModel> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A Consulta do produto pelo ID falhou ! verifique se o ID está correto e tente novamente !");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody @Valid ProductDTO productDTO) {
        ProductModel updateProduct = productService.updateProduct(id, productDTO);
        if(updateProduct != null) {
            return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não Encontrado !");
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluido do sistema com sucesso !");
    }
}
