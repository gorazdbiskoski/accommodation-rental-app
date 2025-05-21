package mk.ukim.finki.accommodation_rental_backend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.accommodation_rental_backend.dto.CreateUserDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayUserDto;
import mk.ukim.finki.accommodation_rental_backend.dto.LoginResponseDto;
import mk.ukim.finki.accommodation_rental_backend.dto.LoginUserDto;
import mk.ukim.finki.accommodation_rental_backend.model.exception.InvalidUserCredentialsException;
import mk.ukim.finki.accommodation_rental_backend.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User API", description = "Endpoints for user authentication and registration")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        return userApplicationService.register(createUserDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "User login")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        return userApplicationService.login(loginUserDto)
                .map(ResponseEntity::ok)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

//    @Operation(summary = "User logout")
//    @GetMapping("/logout")
//    public ResponseEntity<?> logout(HttpServletRequest request) {
//        userApplicationService.logout();
//        return ResponseEntity.ok().build();
//    }

    @Operation(summary = "Fetches all users")
    @GetMapping("/users")
    public ResponseEntity<?> fetchAll(HttpServletRequest request) {
        return ResponseEntity.ok(userApplicationService.fetchAll());
    }
}
