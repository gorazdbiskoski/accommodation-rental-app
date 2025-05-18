package mk.ukim.finki.accommodation_rental_backend.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_HOST;

    @Override
    public String getAuthority() {
        return name();
    }
}
