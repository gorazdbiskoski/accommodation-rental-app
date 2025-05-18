package mk.ukim.finki.accommodation_rental_backend.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class AccommodationOutOfSpaceException extends RuntimeException {

    public AccommodationOutOfSpaceException(Long id) {
        super(String.format("Accommodation with id %s is out of space", id));
    }
}
