package mk.ukim.finki.auditoriskivezhbi.repository.jpa;

import mk.ukim.finki.auditoriskivezhbi.model.ShoppingCart;
import mk.ukim.finki.auditoriskivezhbi.model.User;
import mk.ukim.finki.auditoriskivezhbi.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository  extends JpaRepository<ShoppingCart,Long>{

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);


}
