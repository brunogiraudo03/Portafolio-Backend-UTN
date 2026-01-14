// Archivo: src/main/java/parcial/backend/entities/Designer.java
package parcial.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "DESIGNERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Designer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "designer_seq")
    @SequenceGenerator(name = "designer_seq", sequenceName = "SEQ_DESIGNER_ID", allocationSize = 1)
    @Column(name = "ID_DESIGNER")
    private Integer idDesigner;

    @Column(name = "NAME", nullable = false, unique = true, length = 160)
    private String name;

    // Constructor sin ID (útil para crear nuevos diseñadores)
    public Designer(String name) {
        this.name = name;
    }
}