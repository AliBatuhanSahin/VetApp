package dev.patika.VeterinerYonetimSistemi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerRequest {
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
}
