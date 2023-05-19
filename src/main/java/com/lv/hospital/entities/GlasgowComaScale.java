package com.lv.hospital.entities;

import java.io.Serializable;

public class GlasgowComaScale implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Integer eyeOpening;
    private Integer verbalResponse;
    private Integer motorResponse;
    private Integer total;

    public GlasgowComaScale() {
    }

    public GlasgowComaScale(Long id, Integer eyeOpening, Integer verbalResponse, Integer motorResponse) {
        this.id = id;
        this.eyeOpening = eyeOpening;
        this.verbalResponse = verbalResponse;
        this.motorResponse = motorResponse;
        this.total = calculateTotal();
    }

    public Long getId() {
        return id;
    }

    public Integer getEyeOpening() {
        return eyeOpening;
    }

    public Integer getVerbalResponse() {
        return verbalResponse;
    }

    public Integer getMotorResponse() {
        return motorResponse;
    }

    public Integer getTotal() {
        return total;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GlasgowComaScale other = (GlasgowComaScale) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Integer calculateTotal() {
        return eyeOpening + verbalResponse + motorResponse;
    }
}
