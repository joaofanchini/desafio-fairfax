package br.com.ntconsult.desafio_fairfax.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomType {
    SINGLE(1),
    DOUBLE_SINGLE(2),
    DOUBLE(2),
    SUITE(4);

    private final Integer numberOfGuests;
}
