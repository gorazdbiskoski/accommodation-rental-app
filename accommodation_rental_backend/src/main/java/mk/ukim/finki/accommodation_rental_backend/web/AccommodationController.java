package mk.ukim.finki.accommodation_rental_backend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.accommodation_rental_backend.dto.CreateAccommodationDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayAccommodationDto;
import mk.ukim.finki.accommodation_rental_backend.model.views.AccommodationsPerHostView;
import mk.ukim.finki.accommodation_rental_backend.service.application.AccommodationApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodation")
@Tag(name = "Accommodations", description = "Accomodation management API")
public class AccommodationController {
    private final AccommodationApplicationService accommodationApplicationService;

    public AccommodationController(AccommodationApplicationService accommodationApplicationService) {
        this.accommodationApplicationService = accommodationApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all of the accommodations")
    public ResponseEntity<List<DisplayAccommodationDto>> findAll() {
        List<DisplayAccommodationDto> accommodations = accommodationApplicationService.findAll();
        return ResponseEntity.ok(accommodations);
    }

    @GetMapping("/{id}")
    @Operation(summary = "List a specific accommodation")
    public ResponseEntity<DisplayAccommodationDto> findById(@PathVariable Long id) {
        return accommodationApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/save")
    @Operation(summary = "Add a new accommodation")
    public ResponseEntity<DisplayAccommodationDto> save(@RequestBody CreateAccommodationDto accommodation) {
        return accommodationApplicationService.save(accommodation)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update an already existing accommodation")
    public ResponseEntity<DisplayAccommodationDto> update(
            @PathVariable Long id,
            @RequestBody CreateAccommodationDto accommodation
    ) {
        return accommodationApplicationService.update(accommodation, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an accommodation")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        accommodationApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rentRoom/{id}")
    @Operation(summary = "Rent an accommodation's room")
    public ResponseEntity<Boolean> rentRoom(@PathVariable Long id) {
        boolean rented = accommodationApplicationService.rentById(id);
        return ResponseEntity.ok(rented);
    }

    @PostMapping("/reserve/{id}")
    @Operation(summary = "Add a reservation to the list of potential reservations")
    public ResponseEntity<?> reserve(@PathVariable Long id) {
        accommodationApplicationService.addReservation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-reservation/{id}")
    @Operation(summary = "Removes a reservation from the list of potential reservations")
    public ResponseEntity<?> removeReservation(@PathVariable Long id) {
        accommodationApplicationService.removeReservation(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/finalize-reservations")
    @Operation(summary = "Finalize all of the reservations from the users list")
    public ResponseEntity<?> finalizeAllReservations() {
        accommodationApplicationService.finalizeAllReservations();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/preview-reservations")
    @Operation(summary = "Retrieves all of the pre-reserved accommodations from the users list")
    public ResponseEntity<List<DisplayAccommodationDto>> findAllPreReserved() {
        List<DisplayAccommodationDto> accommodationDtos = accommodationApplicationService.viewAllReservations();
        return ResponseEntity.ok(accommodationDtos);
    }

    @GetMapping("/api/accommodations/by-host")
    public ResponseEntity<List<AccommodationsPerHostView>> getAccommodationsPerHostView() {
        return ResponseEntity.ok(accommodationApplicationService.getAccommodationsPerHostView());
    }
}
