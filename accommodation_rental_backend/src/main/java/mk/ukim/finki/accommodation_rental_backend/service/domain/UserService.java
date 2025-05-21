package mk.ukim.finki.accommodation_rental_backend.service.domain;

import mk.ukim.finki.accommodation_rental_backend.model.domain.User;
import mk.ukim.finki.accommodation_rental_backend.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, Role role);

    User login(String username, String password);

    User findByUsername(String username);

    void logout();

    List<User> fetchAll();
}
