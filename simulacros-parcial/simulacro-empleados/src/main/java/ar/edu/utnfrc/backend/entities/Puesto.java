package ar.edu.utnfrc.backend.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "puesto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Puesto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany(mappedBy = "puesto", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Empleado> empleados = new HashSet<>();

    public Puesto(String nombre) {
        this.nombre = nombre;
    }
}
