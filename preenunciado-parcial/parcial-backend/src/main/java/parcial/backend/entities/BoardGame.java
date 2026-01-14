// Archivo: src/main/java/parcial/backend/entities/BoardGame.java
package parcial.backend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "BOARD_GAMES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "boardgame_seq")
    @SequenceGenerator(name = "boardgame_seq", sequenceName = "SEQ_BOARD_GAME_ID", allocationSize = 1)
    @Column(name = "ID_GAME")
    private Integer idGame;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "YEAR_PUBLISHED")
    private Integer yearPublished; // Puede ser NULL

    @Column(name = "MIN_AGE")
    private Integer minAge; // Puede ser NULL

    @Column(name = "AVERAGE_RATING", precision = 3, scale = 2)
    private BigDecimal averageRating; // DECIMAL(3,2), Puede ser NULL

    @Column(name = "USERS_RATING")
    private Integer usersRating; // Puede ser NULL

    @Column(name = "MIN_PLAYERS")
    private Integer minPlayers; // Puede ser NULL

    @Column(name = "MAX_PLAYERS")
    private Integer maxPlayers; // Puede ser NULL

    // Relaciones @ManyToOne, OBLIGATORIAS (optional = false)

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_DESIGNER", foreignKey = @ForeignKey(name = "FK_BG_DESIGNER"))
    private Designer designer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_PUBLISHER", foreignKey = @ForeignKey(name = "FK_BG_PUBLISHER"))
    private Publisher publisher;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CATEGORY", foreignKey = @ForeignKey(name = "FK_BG_CATEGORY"))
    private Category category;

    // -----------------------------------------------------------------
    // Tarea 4: Validación de rango (Guía específica)
    // -----------------------------------------------------------------

    /**
     * Comprueba si el juego soporta un número determinado de jugadores.
     * Si MIN_PLAYERS/MAX_PLAYERS son NULL, se consideran sin restricción.
     */
    public boolean supportsPlayerCount(int players) {
        // Mínimo: si es null, no hay restricción mínima. Si no es null, players >= minPlayers
        boolean checkMin = (minPlayers == null) || (players >= minPlayers);
        
        // Máximo: si es null, no hay restricción máxima. Si no es null, players <= maxPlayers
        boolean checkMax = (maxPlayers == null) || (players <= maxPlayers);
        
        return checkMin && checkMax;
    }

    /**
     * Comprueba si el juego es apto para un conjunto de edades (ej. un grupo de niños).
     * El juego debe ser apto para TODAS las edades dadas.
     */
    public boolean isSuitableForAges(int[] ages) {
        // Si minAge es NULL, es apto para cualquier edad.
        if (minAge == null) {
            return true;
        }

        // El juego es adecuado si TODAS las edades son mayores o iguales al MIN_AGE.
        // Usamos un Stream para verificar si alguna edad incumple la condición (>= minAge).
        return java.util.Arrays.stream(ages)
            .allMatch(age -> age >= minAge);
    }
}