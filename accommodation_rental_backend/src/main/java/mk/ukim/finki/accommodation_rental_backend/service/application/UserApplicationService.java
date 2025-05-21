package mk.ukim.finki.accommodation_rental_backend.service.application;

import mk.ukim.finki.accommodation_rental_backend.dto.CreateUserDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayUserDto;
import mk.ukim.finki.accommodation_rental_backend.dto.LoginResponseDto;
import mk.ukim.finki.accommodation_rental_backend.dto.LoginUserDto;
import mk.ukim.finki.accommodation_rental_backend.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);
    Optional<DisplayUserDto> findByUsername(String username);

    void logout();

    List<User> fetchAll();
}
