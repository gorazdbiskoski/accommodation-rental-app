package mk.ukim.finki.accommodation_rental_backend.dto;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Country;

import java.util.List;

public record DisplayCountryDto(
        Long id,
        String name,
        String continent
) {
    public static DisplayCountryDto from(Country country) {
        if (country != null)
        {
            return new DisplayCountryDto(country.getId(), country.getName(), country.getContinent());
        }
        return null;
    }

    public static List<DisplayCountryDto> from(List<Country> countries) {
        return countries.stream()
                .map(DisplayCountryDto::from)
                .toList();
    }
}
