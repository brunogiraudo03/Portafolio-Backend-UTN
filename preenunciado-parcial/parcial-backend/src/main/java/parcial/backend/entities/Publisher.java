// Archivo: src/main/java/parcial/backend/entities/Publisher.java
package parcial.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "PUBLISHERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_seq")
    @SequenceGenerator(name = "publisher_seq", sequenceName = "SEQ_PUBLISHER_ID", allocationSize = 1)
    @Column(name = "ID_PUBLISHER")
    private Integer idPublisher;

    @Column(name = "NAME", nullable = false, unique = true, length = 160)
    private String name;

    // Constructor sin ID
    public Publisher(String name) {
        this.name = name;
    }
}