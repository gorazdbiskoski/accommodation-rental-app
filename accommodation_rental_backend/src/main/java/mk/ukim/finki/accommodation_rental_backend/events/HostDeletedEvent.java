package mk.ukim.finki.accommodation_rental_backend.events;

import mk.ukim.finki.accommodation_rental_backend.model.domain.Host;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

public class HostDeletedEvent extends ApplicationEvent {

    private LocalDateTime when;


    public HostDeletedEvent(Host source) {
        super(source);
        this.when = LocalDateTime.now();
    }

    public HostDeletedEvent(Host source, LocalDateTime when) {
        super(source);
        this.when = when;
    }
}
