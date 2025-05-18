package mk.ukim.finki.accommodation_rental_backend.service.domain.impl;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Country;
import mk.ukim.finki.accommodation_rental_backend.repository.CountryRepository;
import mk.ukim.finki.accommodation_rental_backend.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Optional<Country> save(Country country) {
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Country country, Long id) {
        return countryRepository.findById(id).map(oldCountry -> {
            if(country.getName() != null)
            {
                oldCountry.setName(country.getName());
            }
            if(country.getContinent() != null)
            {
                oldCountry.setContinent(country.getContinent());
            }
            return countryRepository.save(oldCountry);
        });
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
