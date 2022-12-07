package mk.ukim.finki.auditoriskivezhbi.service.impl;

import mk.ukim.finki.auditoriskivezhbi.model.Category;
import mk.ukim.finki.auditoriskivezhbi.model.Manufacturer;
import mk.ukim.finki.auditoriskivezhbi.model.Product;
import mk.ukim.finki.auditoriskivezhbi.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.auditoriskivezhbi.model.exceptions.ManufacturerNotFoundException;
import mk.ukim.finki.auditoriskivezhbi.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.auditoriskivezhbi.repository.jpa.CategoryRepository;
import mk.ukim.finki.auditoriskivezhbi.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.auditoriskivezhbi.repository.jpa.ProductRepository;
import mk.ukim.finki.auditoriskivezhbi.service.ProductService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

//    private final InMemoryProductRepository productRepository;
//    private final InMemoryCategoryRepository categoryRepository;
//    private final InMemoryManufacturerRepository manufacturerRepository;

//    public ProductServiceImpl(InMemoryProductRepository productRepository, InMemoryCategoryRepository categoryRepository, InMemoryManufacturerRepository manufacturerRepository) {
//        this.productRepository = productRepository;
//        this.categoryRepository = categoryRepository;
//        this.manufacturerRepository = manufacturerRepository;
//    }

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
//    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
//        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(categoryId));
//        Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId).orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));
//        return this.productRepository.save(name,price,quantity,category,manufacturer);
//    }
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));

        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Manufacturer manufacturer = this.manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        product.setManufacturer(manufacturer);

        return Optional.of(this.productRepository.save(product));

    }


    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);

    }
}
