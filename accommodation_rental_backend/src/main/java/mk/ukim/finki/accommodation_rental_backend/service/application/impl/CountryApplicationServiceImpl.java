package mk.ukim.finki.accommodation_rental_backend.service.application.impl;

import mk.ukim.finki.accommodation_rental_backend.dto.CreateCountryDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayCountryDto;
import mk.ukim.finki.accommodation_rental_backend.service.application.CountryApplicationService;
import mk.ukim.finki.accommodation_rental_backend.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService.findById(id)
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto country) {
        return countryService.save(country.toEntity())
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(CreateCountryDto country, Long id) {
        return countryService.update(country.toEntity(), id)
                .map(DisplayCountryDto::from);
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
