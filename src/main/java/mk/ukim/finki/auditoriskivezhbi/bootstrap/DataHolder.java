package mk.ukim.finki.auditoriskivezhbi.bootstrap;

import mk.ukim.finki.auditoriskivezhbi.model.Category;
import mk.ukim.finki.auditoriskivezhbi.model.Manufacturer;
import mk.ukim.finki.auditoriskivezhbi.model.Product;
import mk.ukim.finki.auditoriskivezhbi.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories=new ArrayList<>();
    public static List<User> users=new ArrayList<>();
    public static List<Manufacturer>manufacturers=new ArrayList<>();

    public static List<Product>products=new ArrayList<>();

    @PostConstruct
    public void init() {
        this.categories.add(new Category("Software", "Software Category"));
        this.categories.add(new Category("Books", "Books Category"));

        this.users.add(new User("nadica.dimitrovska", "nadica123", "Nadica","Dimitrovska"));
        this.users.add(new User("itsnady", "nady321", "Nady","Dimitrovska"));

        Manufacturer manufacturer=new Manufacturer("Nike","NY NY");
        manufacturers.add(manufacturer);

        Category category=new Category("Sport","Sport Category");
        categories.add(category);

        products.add(new Product("Ball 1",235.8,7,category,manufacturer ));
        products.add(new Product("Ball 2",235.8,7,category,manufacturer ));
        products.add(new Product("Ball 3",235.8,7,category,manufacturer ));
    }
}
