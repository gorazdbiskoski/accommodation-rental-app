package mk.ukim.finki.accommodation_rental_backend.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Accommodation;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Country;
import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;
import mk.ukim.finki.accommodation_rental_backend.model.enums.Category;
import mk.ukim.finki.accommodation_rental_backend.repository.AccommodationRepository;
import mk.ukim.finki.accommodation_rental_backend.repository.CountryRepository;
import mk.ukim.finki.accommodation_rental_backend.repository.HostRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    private final CountryRepository countryRepository;
    private final HostRepository hostRepository;
    private final AccommodationRepository accommodationRepository;

    public DataInitializer(CountryRepository countryRepository, HostRepository hostRepository, AccommodationRepository accommodationRepository) {
        this.countryRepository = countryRepository;
        this.hostRepository = hostRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @PostConstruct
    public void init() {
        List<Country> countries = new ArrayList<>();
        if(countryRepository.count() == 0) {
            countries.add(new Country("France", "Europe"));
            countries.add(new Country("Germany", "Europe"));
            countries.add(new Country("Canada", "North America"));
            countries.add(new Country("China", "Asia"));
            countries.add(new Country("Brazil", "South America"));
        }
        countryRepository.saveAll(countries);

        List<Host> hosts = new ArrayList<>();
        if(hostRepository.count() == 0) {
            hosts.add(new Host("Gorazd", "Biskoski", countries.get(0)));
            hosts.add(new Host("Daniel", "Ilievski", countries.get(4)));
            hosts.add(new Host("Kristijan", "Stojanovski", countries.get(2)));
            hosts.add(new Host("Admin", "Admin", countries.get(4)));
        }
        hostRepository.saveAll(hosts);

        List<Accommodation> accommodations = new ArrayList<>();
        if(accommodationRepository.count() == 0)
        {
            accommodations.add(new Accommodation("Grand Plaza", Category.HOTEL, hosts.get(0), 5, true));
            accommodations.add(new Accommodation("Mia's Apartments", Category.APARTMENT, hosts.get(1), 3, false));
            accommodations.add(new Accommodation("Andrej's", Category.APARTMENT, hosts.get(1), 6, true));
            accommodations.add(new Accommodation("Test Flat", Category.FLAT, hosts.get(2), 6, true));
            accommodations.add(new Accommodation("Just a room", Category.ROOM, hosts.get(3), 1, false));
        }
        accommodationRepository.saveAll(accommodations);
    }
}
