# Simulacro Parcial

## Propósito

El objetivo de este simulacro es presentar el enunciado general que se debe plantear antes del "parcial" propiamente dicho. Para ellos se los llevará en un paso a paso que les permita crear todo como deberia estar ese dia. Para la clase del Jueves ustede deben tener este paso a paso completado, para poder desarrollar los puntos que siguen.

## Puntos a desarrollar

### Enunciado

1. Un gimnasio nos ha solicitado un programa manejado por menu de opciones, que le permita realizar una serie de informes y calculos estadisticos, para poder mejorar la atención de sus abonados. Para ello nos esta gestionando pero todavia no tienen listo un archivo csv que se llamara _abonados.csv_, 
   1. Sabemos que contrendrá  la siguiente estructura:
        ```
            DniAbonado (Str):  el dni del abonado
            NombreAbonado (Str): el nombre del abonado
            EmailAbonado (str): su email
            TelefonoAbonado (Str): su telefono
            PrecioCuota (Decimal): el precio de la cuota mensual 
            Sucursal (Str): el nombre del local al que va el abonado, es unico para ese abonado
            NombreMaquina (Str): el nombre de la maquina en la cual ejercita como parte de su plan
            DescripcionEjercicio (Str): representa que tipo de ejercicio va a hacer sobre la maquina como parte del plan
            CantidadSeries (Int): Cantidad de Series que va a realizar para ese ejercion
            CantidadRepeticiones (Int): Cantidad de veces que repetira el ejercicio cada serie
        ```
    2. De la estructura presentada, se desprenderán las siguientes entidades que usted deberá mapear, para poder guardar en la base de datos.

        ```mermaid
            classDiagram
                class EjercicioPlan {
                    Integer id,
                    String nombreMaquina,
                    String descripcion,
                    Integer series,
                    Integer repeticiones

                    + totalCaloriasQuemadas()
                }
                class Abonado {
                    Integer id,
                    String dni,
                    String nombre, 
                    String email,
                    String telefono
                    Double precio
                    List<EjercicioPlan>
                    Sucursal
                }
                class Sucursal {
                    Integer id,
                    String descricion
                }

                Abonado "*" --> "1" Sucursal
                Abonado "1" --> "*" EjercicioPlan
        ```

   3. Se considera que el totalCaloriasQuemas se calcula de la siguiente maneras:  series * repeticion * un factor random entre 1.75 y 2.85

            > factor = 1.75 + (Math.random() * (2.85 - 1.75));

2. Entrada de datos
   
   El programa debe leer un archivo CSV (el cual será provisto junto con el enunciado final), cuyo nombre puede pasarse como parámetro de entrada o estar definido por defecto.El CSV contendrá información sobre empleados.

3. Base de datos

   Se utilizará H2 en memoria como motor de base de datos. Se entregará un script SQL para crear las tablas necesarias junto con este enunciado. El programa deberá ejecutar este script al inicio para inicializar la base o bien se generará en base a los mapeos de JPA

    ```sql
        DROP TABLE IF EXISTS ejercicio_plan;
        DROP TABLE IF EXISTS abonado;
        DROP TABLE IF EXISTS sucursal;

        CREATE TABLE sucursal (
            id INT AUTO_INCREMENT PRIMARY KEY,
            descripcion VARCHAR(100) NOT NULL
        );

        CREATE TABLE abonado (
            id INT AUTO_INCREMENT PRIMARY KEY,
            dni VARCHAR(20) NOT NULL UNIQUE,
            nombre VARCHAR(100) NOT NULL,
            email VARCHAR(150) NOT NULL,
            telefono VARCHAR(30),
            precio DECIMAL(10,2) NOT NULL,
            sucursal_id INT NOT NULL,
            CONSTRAINT fk_abonado_sucursal
                FOREIGN KEY (sucursal_id)
                REFERENCES sucursal(id)
        );

        CREATE TABLE ejercicio_plan (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre_maquina VARCHAR(100) NOT NULL,
            descripcion VARCHAR(200) NOT NULL,
            series INT NOT NULL,
            repeticiones INT NOT NULL,
            abonado_id INT NOT NULL,
            CONSTRAINT fk_ejercicio_abonado
                FOREIGN KEY (abonado_id)
                REFERENCES abonado(id)
        );

    ```
   __nota__: 
    Aplicar en su _persistence.xml_ las siguientes configuraciones, si previamente se genero la base con el script provisto

    ```xml  
        <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect" />
        <property name="hibernate.hbm2ddl.auto" value="none" />
        <property name="hibernate.show_sql" value="true" />
        <property name="hibernate.format_sql" value="true" />
    ```

    Por el contrario, si no ejecuta el script de inicialización entonces sus properties deberían quedar de la siguiente manera

    ```xml
        <property name="hibernate.dialect" value="org.hibernate.dialect.H2Dialect" />
        <property name="hibernate.hbm2ddl.auto" value="ceate" />
        <property name="hibernate.show_sql" value="true" />
        <property name="hibernate.format_sql" value="true" />
    ```

4. Persistencia

    El programa debe leer el CSV y popular las tablas de la base de datos con sus registros.

5. Consultas

El programa deberá obtener y mostrar en consola resúmenes de los datos cargados. Las consignas específicas de consultas se entregarán junto con el enunciado final completo. Opcional para ver que se haya generado correctamente la importacion, agregar una opcion de menu que permita realizar un listado mostrando todos los datos del abonado, a que sucursal va y el total de caloria que quema en su plan (sin incluir los ids)
    

## Resultado esperado

Con la información provista, se espera que cada estudiante arme toda la estructura del proyecto, con las clases del modelo provistas utilizando JPA y permitiendo la conexión a la DB de la manera que crea más conveniente.

Se recomienda subir el proyecto inicial al GitLab propio para luego actualizar el repositorio con los cambios hechos durante el parcial.