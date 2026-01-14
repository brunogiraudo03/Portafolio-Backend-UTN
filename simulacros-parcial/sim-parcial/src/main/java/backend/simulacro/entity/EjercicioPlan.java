package backend.simulacro.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ejercicio_plan")
public class EjercicioPlan implements ModelEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_maquina")
    private String nombreMaquina;
    @Column
    private String descripcion;
    @Column
    private Integer series;
    @Column
    private Integer repeticiones;

    public Double totalCaloriasQuemadas() {
        return series
         * repeticiones
         * (1.75 + (Math.random() * (2.85 - 1.75)));
    }
}
