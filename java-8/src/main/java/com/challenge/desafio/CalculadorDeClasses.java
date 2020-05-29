package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object object) {
        return this.efetuarCalculo(object, Somar.class);
    }

    @Override
    public BigDecimal subtrair(Object object) {
        return this.efetuarCalculo(object, Subtrair.class);
    }

    @Override
    public BigDecimal totalizar(Object object) {
        return somar(object).subtract(subtrair(object));
    }

    @SuppressWarnings("unchecked")
    private BigDecimal efetuarCalculo(final Object object, Class annotation) {
        BigDecimal resultado = BigDecimal.ZERO;

        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                if (field.isAnnotationPresent(annotation) && field.getType().equals(BigDecimal.class)) {
                    resultado = resultado.add((BigDecimal) field.get(object));
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
