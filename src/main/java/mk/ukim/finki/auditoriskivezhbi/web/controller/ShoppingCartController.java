package mk.ukim.finki.auditoriskivezhbi.web.controller;

import mk.ukim.finki.auditoriskivezhbi.model.ShoppingCart;
import mk.ukim.finki.auditoriskivezhbi.model.User;
import mk.ukim.finki.auditoriskivezhbi.service.ShoppingCartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
//        User user= (User) req.getSession().getAttribute("user");
        String username = req.getRemoteUser();
//        ShoppingCart shoppingCart=this.shoppingCartService.getActiveShoppingCart(user.getUsername());
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("products", this.shoppingCartService.listAllProductInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
//        return "shopping-cart";
        return "master-template";
    }

//    @PostMapping("/add-product/{id}")
//    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req){
//        try {
////            User user= (User) req.getSession().getAttribute("user");
//            String username=req.getRemoteUser();
////            ShoppingCart shoppingCart=this.shoppingCartService.addProductToShoppingCart(user.getUsername(),id);
//            ShoppingCart shoppingCart=this.shoppingCartService.addProductToShoppingCart(username,id);
//            return "redirect:/shopping-cart";
//        }catch (RuntimeException exception){
//            return "redirect:/shopping-cart?error=" + exception.getMessage();
//        }
//
//    }


    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.addProductToShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";

        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
}

