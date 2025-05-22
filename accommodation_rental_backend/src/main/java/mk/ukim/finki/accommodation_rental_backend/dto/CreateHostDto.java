package mk.ukim.finki.accommodation_rental_backend.dto;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Country;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;

public record CreateHostDto(
        String name,
        String surname,
        Long country
) {
    public Host toEntity(Country country) {
        return new Host(
                name,
                surname,
                country
        );
    }
}
