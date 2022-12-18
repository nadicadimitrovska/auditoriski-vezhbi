package mk.ukim.finki.auditoriskivezhbi.service;

import mk.ukim.finki.auditoriskivezhbi.model.User;

public interface AuthService {

    User login(String username, String password);
//    User register(String username, String password, String repeatPassword, String name, String surname);

}
