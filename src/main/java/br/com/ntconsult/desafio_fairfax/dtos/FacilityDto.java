package br.com.ntconsult.desafio_fairfax.dtos;

import br.com.ntconsult.desafio_fairfax.domains.Facility;
import lombok.Getter;

@Getter
public class FacilityDto {
    public FacilityDto(Facility facility){
       this.id = facility.getId();
       this.name = facility.getName();
    }

    private final Integer id;
    private final String name;
}
