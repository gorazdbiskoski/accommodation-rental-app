package mk.ukim.finki.accommodation_rental_backend.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.accommodation_rental_backend.model.enums.Category;

@Data
@Entity
@NoArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    //FetchType.LAZY is on by default
    @ManyToOne
    private Host host;

    private Integer numRooms;

    private Boolean isAvailable;

    public Accommodation(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
    }

    public Accommodation(String name, Category category, Host host, Integer numRooms, Boolean isAvailable) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isAvailable = isAvailable;
    }
}
