package mk.ukim.finki.accommodation_rental_backend.service.domain.impl;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Accommodation;
import mk.ukim.finki.accommodation_rental_backend.model.domain.User;
import mk.ukim.finki.accommodation_rental_backend.model.exception.AccommodationOutOfSpaceException;
import mk.ukim.finki.accommodation_rental_backend.repository.AccommodationRepository;
import mk.ukim.finki.accommodation_rental_backend.repository.UserRepository;
import mk.ukim.finki.accommodation_rental_backend.service.domain.AccommodationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, UserRepository userRepository) {
        this.accommodationRepository = accommodationRepository;
        this.userRepository = userRepository;
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

    @Override
    public void addReservation(Long id) {
        User user = getCurrentUser();

        accommodationRepository.findById(id)
                .map(accommodation -> {
                    if(accommodation.getIsAvailable() && !user.getReservations().contains(accommodation))
                    {
                        user.getReservations().add(accommodation);
                        userRepository.save(user);
                    }
                    else
                    {
                        //TODO
                        throw new AccommodationOutOfSpaceException(id);
                    }
                    return accommodation;
                    //TODO
                }).orElseThrow(() -> new AccommodationOutOfSpaceException(id));
    }

    @Override
    public void removeReservation(Long id) {
        User user = getCurrentUser();

        Accommodation accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> new AccommodationOutOfSpaceException(id));

        user.getReservations().remove(accommodation);
        userRepository.save(user);
    }

    @Override
    public void finalizeAllReservations() {
        User user = getCurrentUser();

        user.getReservations().stream().forEach(reservation -> {
            reservation.setIsAvailable(false);
            accommodationRepository.save(reservation);
        });

        user.getReservations().clear();
        userRepository.save(user);
    }

    @Override
    public List<Accommodation> viewAllReservations() {
        User user = getCurrentUser();
        return user.getReservations();
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated())
        {
            //TODO
            throw new IllegalArgumentException();
        }

        return  (User) authentication.getPrincipal();
    }
}
