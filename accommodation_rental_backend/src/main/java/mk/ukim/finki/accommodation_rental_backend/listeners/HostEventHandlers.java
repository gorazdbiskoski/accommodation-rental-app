package mk.ukim.finki.accommodation_rental_backend.listeners;

import mk.ukim.finki.accommodation_rental_backend.events.HostCreatedEvent;
import mk.ukim.finki.accommodation_rental_backend.events.HostDeletedEvent;
import mk.ukim.finki.accommodation_rental_backend.events.HostUpdatedEvent;
import mk.ukim.finki.accommodation_rental_backend.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {

    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostCreated(HostCreatedEvent event) {
        hostService.refreshMaterializedView();
    }

    @EventListener
    public void onHostDeleted(HostDeletedEvent event) {
        hostService.refreshMaterializedView();
    }

    @EventListener
    public void onHostUpdated(HostUpdatedEvent event) {
        hostService.refreshMaterializedView();
    }
}
