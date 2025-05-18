package mk.ukim.finki.accommodation_rental_backend.service.domain;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> save(Country country);

    Optional<Country> update(Country country, Long id);

    void deleteById(Long id);
}
