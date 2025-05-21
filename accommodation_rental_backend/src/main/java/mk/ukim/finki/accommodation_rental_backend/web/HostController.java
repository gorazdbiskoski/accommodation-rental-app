package mk.ukim.finki.accommodation_rental_backend.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.accommodation_rental_backend.dto.CreateHostDto;
import mk.ukim.finki.accommodation_rental_backend.dto.DisplayHostDto;
import mk.ukim.finki.accommodation_rental_backend.model.projections.HostProjection;
import mk.ukim.finki.accommodation_rental_backend.model.views.HostsPerCountryView;
import mk.ukim.finki.accommodation_rental_backend.service.application.HostApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/host")
@Tag(name = "Host", description = "Host management API for accommodations")
public class HostController {
    private final HostApplicationService hostApplicationService;

    public HostController(HostApplicationService hostApplicationService) {
        this.hostApplicationService = hostApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<DisplayHostDto>> findAll() {
        List<DisplayHostDto> hosts = hostApplicationService.findAll();
        return ResponseEntity.ok(hosts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDto> findById(@PathVariable Long id) {
        return hostApplicationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/save")
    public ResponseEntity<DisplayHostDto> save(@RequestBody CreateHostDto host) {
        return hostApplicationService.save(host)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<DisplayHostDto> update(
            @PathVariable Long id,
            @RequestBody CreateHostDto host
    ) {
        return hostApplicationService.update(host, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        hostApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-country")
    public ResponseEntity<List<HostsPerCountryView>> getHostsPerCountryView() {
        return ResponseEntity.ok(hostApplicationService.getHostsPerCountryView());
    }

    @GetMapping("/names")
    public ResponseEntity<List<HostProjection>> getHostsNames() {
        return ResponseEntity.ok(hostApplicationService.findAllHostsNames());
    }
}
