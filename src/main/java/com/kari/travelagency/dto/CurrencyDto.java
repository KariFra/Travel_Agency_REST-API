package com.kari.travelagency.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyDto {

    private String code;
    private String rate;

    @Override
    public String toString() {
        return "CurrencyDto{" +
                "code='" + code + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
