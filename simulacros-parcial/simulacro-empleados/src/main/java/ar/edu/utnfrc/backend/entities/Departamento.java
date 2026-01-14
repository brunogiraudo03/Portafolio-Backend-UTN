package ar.edu.utnfrc.backend.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Empleado> empleados = new HashSet<>();

    public Departamento(String nombre) {
        this.nombre = nombre;
    }
}
