// Archivo: src/main/java/parcial/backend/entities/Category.java
package parcial.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "CATEGORIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
    @SequenceGenerator(name = "category_seq", sequenceName = "SEQ_CATEGORY_ID", allocationSize = 1)
    @Column(name = "ID_CATEGORY")
    private Integer idCategory;

    @Column(name = "NAME", nullable = false, unique = true, length = 120)
    private String name;

    // Constructor sin ID
    public Category(String name) {
        this.name = name;
    }
}