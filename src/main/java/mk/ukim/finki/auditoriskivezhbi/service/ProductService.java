package mk.ukim.finki.auditoriskivezhbi.service;

import mk.ukim.finki.auditoriskivezhbi.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product>findAll();
    Optional<Product>findById(Long id);
    Optional<Product>findByName(String name);
    Optional<Product>save(String name,Double price, Integer quantity,Long categoryId, Long manufacturerId);
    Optional<Product>edit(Long id,String name, Double price, Integer quantity, Long categoryId, Long manufacturerId);
    void deleteById(Long id);
}
