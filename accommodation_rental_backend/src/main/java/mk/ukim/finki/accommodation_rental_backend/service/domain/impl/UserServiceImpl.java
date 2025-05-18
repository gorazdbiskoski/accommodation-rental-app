package mk.ukim.finki.accommodation_rental_backend.service.domain.impl;

import mk.ukim.finki.accommodation_rental_backend.model.domain.User;
import mk.ukim.finki.accommodation_rental_backend.model.enums.Role;
import mk.ukim.finki.accommodation_rental_backend.repository.UserRepository;
import mk.ukim.finki.accommodation_rental_backend.service.domain.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
        {
            //TODO: Make Exception
            throw new IllegalArgumentException("Username and password cannot be empty");
        }
        if(!password.equals(repeatPassword))
        {
            //TODO: Make Exception
            throw new IllegalArgumentException("Passwords don't match");
        }
        if(userRepository.findByUsername(username).isPresent())
        {
            //TODO: Make Exception
            throw new IllegalArgumentException("Username already exists");
        }
        User user = new User(username, passwordEncoder.encode(password), name, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
        {
            //TODO: Make Exception
            throw new IllegalArgumentException("Username and password cannot be empty");
        }
        //TODO: Make Exception
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public void logout() {
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            SecurityContextHolder.clearContext();
        } else {
            throw new IllegalArgumentException();
            //TODO: Make Exception
            //throw new AuthenticationException();
        }
    }
}
