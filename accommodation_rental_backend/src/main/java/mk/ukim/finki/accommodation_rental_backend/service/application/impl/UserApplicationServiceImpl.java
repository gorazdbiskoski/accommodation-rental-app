package mk.ukim.finki.accommodation_rental_backend.service.application.impl;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.accommodation_rental_backend.dto.CreateUserDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayUserDto;
import mk.ukim.finki.accommodation_rental_backend.dto.LoginResponseDto;
import mk.ukim.finki.accommodation_rental_backend.dto.LoginUserDto;
import mk.ukim.finki.accommodation_rental_backend.model.domain.User;
import mk.ukim.finki.accommodation_rental_backend.security.JwtHelper;
import mk.ukim.finki.accommodation_rental_backend.service.application.UserApplicationService;
import mk.ukim.finki.accommodation_rental_backend.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginUserDto loginUserDto) {
        User user = userService.login(
                loginUserDto.username(),
                loginUserDto.password()
        );

        String token = jwtHelper.generateToken(user);
        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(
                DisplayUserDto.from(
                        userService.findByUsername(username)
                )
        );
    }

    @Override
    public void logout() {
        userService.logout();
    }

    @Override
    public List<User> fetchAll() {
        return userService.fetchAll();
    }
}
