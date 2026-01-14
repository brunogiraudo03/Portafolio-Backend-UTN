package com.ejemplo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
    private String id;
    private String nombre;
    private String apellido;
    private int aniosCarrera;

}