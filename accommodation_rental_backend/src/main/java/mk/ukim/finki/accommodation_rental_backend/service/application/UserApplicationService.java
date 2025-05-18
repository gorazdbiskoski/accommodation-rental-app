package mk.ukim.finki.accommodation_rental_backend.service.application;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.accommodation_rental_backend.dto.CreateUserDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayUserDto;
import mk.ukim.finki.accommodation_rental_backend.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<DisplayUserDto> login(LoginUserDto loginUserDto);
    Optional<DisplayUserDto> findByUsername(String username);

    void logout();
}
