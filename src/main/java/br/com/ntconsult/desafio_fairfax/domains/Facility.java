package br.com.ntconsult.desafio_fairfax.domains;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Facility {
    @Id
    private Integer id;

    @NotNull
    private String name;
}
