package mk.ukim.finki.accommodation_rental_backend.dto;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Country;

public record CreateCountryDto(
        String name,
        String continent
) {
    public Country toEntity() {
        return new Country(
                name,
                continent
        );
    }
}
