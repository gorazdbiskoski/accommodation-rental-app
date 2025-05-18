package mk.ukim.finki.accommodation_rental_backend.dto;

import mk.ukim.finki.accommodation_rental_backend.model.domain.User;
import mk.ukim.finki.accommodation_rental_backend.model.enums.Role;

public record DisplayUserDto(
        String username,
        String name,
        Role role
) {
    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(user.getUsername(), user.getName(), user.getRole());
    }

    public User toEntity() {
        return new User(username, name, role.name());
    }
}
