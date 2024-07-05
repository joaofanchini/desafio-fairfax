package br.com.ntconsult.desafio_fairfax.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Class<?> clazz, String field, Object value) {
        super("Resource not found for %s: %s %s".formatted(clazz.getSimpleName(), field, value.toString()));
        this.clazz = clazz;
        this.field = field;
        this.value = value;
    }

    private Class<?> clazz;
    private String field;
    private Object value;
}
