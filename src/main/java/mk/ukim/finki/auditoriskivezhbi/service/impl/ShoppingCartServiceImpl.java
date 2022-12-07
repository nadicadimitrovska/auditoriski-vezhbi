package mk.ukim.finki.auditoriskivezhbi.service.impl;

import mk.ukim.finki.auditoriskivezhbi.model.Product;
import mk.ukim.finki.auditoriskivezhbi.model.ShoppingCart;
import mk.ukim.finki.auditoriskivezhbi.model.User;
import mk.ukim.finki.auditoriskivezhbi.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.auditoriskivezhbi.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.auditoriskivezhbi.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.auditoriskivezhbi.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.auditoriskivezhbi.model.exceptions.UserNotFoundException;
import mk.ukim.finki.auditoriskivezhbi.repository.jpa.ShoppingCartRepository;
import mk.ukim.finki.auditoriskivezhbi.repository.jpa.UserRepository;
import mk.ukim.finki.auditoriskivezhbi.service.ProductService;
import mk.ukim.finki.auditoriskivezhbi.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

//    private final InMemoryShoppingCartRepository shoppingCartRepository;
//    private final InMemoryUserRepository userRepository;
//    private final ProductService productService;

//    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository, InMemoryUserRepository userRepository, ProductService productService) {
//        this.shoppingCartRepository = shoppingCartRepository;
//        this.userRepository = userRepository;
//        this.productService = productService;
//    }
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository, ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductInShoppingCart(Long cartId) {
        if (!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
//    public ShoppingCart getActiveShoppingCart(String username) {
//        return this.shoppingCartRepository.findByUsernameAndStatus(username, ShoppingCartStatus.CREATED).orElseGet(()->{
//            User user=this.userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException(username));
//            ShoppingCart shoppingCart=new ShoppingCart(user);
//            return this.shoppingCartRepository.save(shoppingCart);
//        });
//    }
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository.findByUserAndStatus(user, ShoppingCartStatus.CREATED).orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }


    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart=this.getActiveShoppingCart(username);
//        Product product=this.productService.findById(productId).orElseThrow(()->new ProductNotFoundException(productId));
       Product product=this.productService.findById(productId).orElseThrow(()->new ProductNotFoundException(productId));
        if (shoppingCart.getProducts().stream().filter(i->i.getId().equals(productId)).collect(Collectors.toList()).size()>0)
            throw new ProductAlreadyInShoppingCartException(productId,username);
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
        }
}
