package br.com.ntconsult.desafio_fairfax.domains;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
public class Review {
    @EmbeddedId
    private ReviewId id;
    private Integer note;

    @EqualsAndHashCode
    public static class ReviewId implements Serializable {
        @ManyToOne
        private Reservation reservation;
        @ManyToOne
        private Hotel hotel;
    }
}
