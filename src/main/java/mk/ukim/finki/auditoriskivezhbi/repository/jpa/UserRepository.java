package mk.ukim.finki.auditoriskivezhbi.repository.jpa;

import mk.ukim.finki.auditoriskivezhbi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsernameAndPassword(String user,String password);
    Optional<User> findByUsername(String user);
}
