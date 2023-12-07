package com.br.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.br.backend.dto.ProductDTO;
import com.br.backend.model.ProductModel;
import com.br.backend.repositories.ProductRepository;

@Service
public class ProductService {
    
    private final ProductRepository productRepository; 

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    //Salvar produto
    public ProductModel saveProduct(ProductDTO productDTO) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productDTO, productModel);
        return productRepository.save(productModel);
    }

    //Listar todos os produtos
    public List <ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    //Buscar produtos por id
    public Optional<ProductModel> getProductById(String id) {
        return productRepository.findById(id);
    }

    //Atualizar produtos por id 
    public ProductModel updateProduct (String id, ProductDTO productDTO){
        Optional<ProductModel> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            ProductModel productModel = optionalProduct.get();
            BeanUtils.copyProperties(productDTO, productModel);
            return productRepository.save(productModel);
        }
        return null;
    }

    //Deletar produto por id 
    public void deleteProduct(String id){
        productRepository.deleteById(id);
    }
    

}
