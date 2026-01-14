package parcial.backend.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DbInitializer {

    // URL de conexión OBLIGATORIA según la consigna
    private static final String JDBC_URL = "jdbc:h2:mem:boardgames;DB_CLOSE_DELAY=-1";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "";
    
    // Ruta del script dentro de resources. ¡Ajustada a tu estructura META-INF!
    private static final String SQL_SCRIPT_PATH = "/META-INF/ddl_board_games.sql";

    /**
     * Inicializa la base de datos H2 en memoria ejecutando el script DDL.
     */
    public void initialize() {
        System.out.println("-> Iniciando Base de Datos H2 y ejecutando script DDL...");
        String sqlScript = loadSqlScript();

        // Usamos try-with-resources para asegurar el cierre de la conexión y el statement
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement statement = connection.createStatement()) {
            
            // Ejecuta todo el script SQL
            statement.execute(sqlScript);
            
            System.out.println("-> Base de Datos H2 inicializada y script ejecutado con éxito.");

        } catch (Exception e) {
            System.err.println("!!! ERROR al inicializar la base de datos H2: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Fallo en la inicialización de la BD.", e);
        }
    }

    /**
     * Carga el contenido del script SQL desde el classpath.
     * @return Contenido completo del script como String.
     */
    private String loadSqlScript() {
        try {
            // Obtener el recurso desde el classpath (src/main/resources/META-INF)
            InputStream is = getClass().getResourceAsStream(SQL_SCRIPT_PATH);
            
            if (is == null) {
                // Si falla la carga, intentamos con el class loader de la aplicación
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(SQL_SCRIPT_PATH.substring(1));
            }
            
            if (is == null) {
                throw new IllegalArgumentException("No se encontró el archivo DDL en el classpath: " + SQL_SCRIPT_PATH);
            }
            
            // Leer todo el contenido del InputStream a un String
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                // Usar \n como separador, crucial para que el Statement.execute() funcione con scripts largos
                return reader.lines().collect(Collectors.joining("\n"));
            }
        } catch (Exception e) {
            System.err.println("!!! ERROR al cargar el script SQL: " + e.getMessage());
            throw new RuntimeException("Fallo al leer el archivo SQL.", e);
        }
    }
}