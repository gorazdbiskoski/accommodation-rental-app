package mk.ukim.finki.accommodation_rental_backend.service.domain.impl;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Accommodation;
import mk.ukim.finki.accommodation_rental_backend.model.exception.AccommodationOutOfSpaceException;
import mk.ukim.finki.accommodation_rental_backend.repository.AccommodationRepository;
import mk.ukim.finki.accommodation_rental_backend.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Accommodation> findAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public Optional<Accommodation> update(Accommodation accommodation, Long id) {
        return accommodationRepository.findById(id).map(oldAccommodation -> {
            if(accommodation.getName() != null)
            {
                oldAccommodation.setName(accommodation.getName());
            }
            if(accommodation.getCategory() != null)
            {
                oldAccommodation.setCategory(accommodation.getCategory());
            }
            if(accommodation.getHost() != null)
            {
                oldAccommodation.setHost(accommodation.getHost());
            }
            if(accommodation.getNumRooms() != null)
            {
                oldAccommodation.setNumRooms(accommodation.getNumRooms());
            }
            return accommodationRepository.save(oldAccommodation);
        });
    }

    @Override
    public void deleteById(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public boolean rentById(Long id) {
        return accommodationRepository.findById(id)
                .filter(Accommodation::getIsAvailable)
                .map(accommodation -> {
                    accommodation.setIsAvailable(false);
                    accommodationRepository.save(accommodation);
                    return true;
                })
                .orElseThrow(() -> new AccommodationOutOfSpaceException(id));
    }
}
