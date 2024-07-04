package br.com.ntconsult.desafio_fairfax.domains;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class Hotel {
    @Id
    @ToString.Include
    private Integer id;

    @NotNull
    @ToString.Include
    private String name;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<Room> rooms;

    private String location;

    @ManyToMany
    @JoinTable(name = "hotel_facilities",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "facility_id"))
    private List<Facility> facilities;

    @OneToMany(mappedBy = "id.hotel", fetch = FetchType.LAZY)
    private List<Review> reviews;
}
