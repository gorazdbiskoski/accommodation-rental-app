package mk.ukim.finki.accommodation_rental_backend.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.accommodation_rental_backend.dto.CreateCountryDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayCountryDto;
import mk.ukim.finki.accommodation_rental_backend.service.application.CountryApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/country")
@Tag(name = "Countries", description = "Country management API for accommodations")
public class CountryController {
    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }


    @GetMapping
    @Operation(summary = "List all of the countries")
    public ResponseEntity<List<DisplayCountryDto>> findAll() {
        List<DisplayCountryDto> countries = countryApplicationService.findAll();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/{id}")
    @Operation(summary = "List a specific country")
    public ResponseEntity<DisplayCountryDto> findById(@PathVariable Long id) {
        return countryApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/save")
    @Operation(summary = "Add a new country")
    public ResponseEntity<DisplayCountryDto> save(@RequestBody CreateCountryDto country) {
        return countryApplicationService.save(country)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update an already existing country")
    public ResponseEntity<DisplayCountryDto> update(
            @PathVariable Long id,
            @RequestBody CreateCountryDto country
    ) {
        return countryApplicationService.update(country, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a country")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        countryApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
