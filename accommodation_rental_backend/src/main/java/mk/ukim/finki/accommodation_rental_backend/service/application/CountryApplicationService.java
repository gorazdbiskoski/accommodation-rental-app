package mk.ukim.finki.accommodation_rental_backend.service.application;

import mk.ukim.finki.accommodation_rental_backend.dto.CreateCountryDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<DisplayCountryDto> findAll();

    Optional<DisplayCountryDto> findById(Long id);

    Optional<DisplayCountryDto> save(CreateCountryDto country);

    Optional<DisplayCountryDto> update(CreateCountryDto country, Long id);

    void deleteById(Long id);
}
