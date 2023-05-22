package com.lv.hospital.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class GlasgowComaScaleTest {
    
    @Test
    public void shouldReturn10(){
        var g1 = new GlasgowComaScale(null, 2, 3, 5, 0, null);
        assert(g1.getTotal() == 10);
    }

    @Test
    public void shouldReturn11(){
        var g1 = new GlasgowComaScale(null, 3, 4, 6, 2, null);
        assert(g1.getTotal() == 11);
    }

    @Test
    public void shouldReturn15(){
        var g1 = new GlasgowComaScale(null, 4, 5, 6, 0, null);
        assert(g1.getTotal() == 15);
    }

    @Test
    public void shouldReturnDeepComa(){
        var g1 = new GlasgowComaScale(null, 1, 1, 1, 2, null);
        assertEquals("Deep coma", g1.getResult());
    }

    @Test
    public void shouldReturnSevereComa(){
        var g1 = new GlasgowComaScale(null, 2, 2, 1, 1, null);
        assertEquals("Severe coma", g1.getResult());
    }

    @Test
    public void shouldReturnModerateComa(){
        var g1 = new GlasgowComaScale(null, 3, 1, 3, 1, null);
        assertEquals("Moderate coma", g1.getResult());
    }

    @Test
    public void shouldReturnSuperficialComa(){
        var g1 = new GlasgowComaScale(null, 4, 4, 3, 0, null);
        assertEquals("Superficial coma", g1.getResult());
    }

    @Test
    public void shouldReturnNormality(){
        var g1 = new GlasgowComaScale(null, 4, 5, 6, 0, null);
        assertEquals("Normality", g1.getResult());
    }


}
