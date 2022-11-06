package mk.ukim.finki.auditoriskivezhbi.service;

import mk.ukim.finki.auditoriskivezhbi.model.Product;
import mk.ukim.finki.auditoriskivezhbi.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    List<Product>listAllProductInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
