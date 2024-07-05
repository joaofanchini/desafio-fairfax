package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Facility;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class FacilityDto {
    public FacilityDto(Facility facility) {
        this.id = facility.getId();
        this.name = facility.getName();
    }

    private Integer id;
    private String name;
}
