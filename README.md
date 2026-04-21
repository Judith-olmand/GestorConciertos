# 🎵 Sistema de Gestión de Conciertos y Venta de Entradas

Sistema CRUD completo desarrollado en Java con conexión a base de datos Oracle para gestionar artistas, conciertos y venta de entradas de manera profesional.

## 📋 Descripción

Aplicación de consola que permite administrar un sistema de eventos musicales, incluyendo registro de artistas, programación de conciertos, y control de ventas de entradas con validaciones de negocio y límites por comprador.

## 🎯 Funcionalidades Principales

### Gestión de Artistas 🎤
- ✅ Añadir nuevos artistas con validación de duplicados
- ✅ Eliminar artistas (con validación de integridad referencial)
- ✅ Listar todos los artistas ordenados por ID
- ✅ Búsqueda por nombre (case-insensitive)

### Gestión de Conciertos 🎸
- ✅ Crear nuevos conciertos vinculados a artistas existentes
- ✅ Eliminar conciertos (con validación de entradas vendidas)
- ✅ Listar conciertos ordenados por fecha
- ✅ Validación de fechas y precios

### Gestión de Ventas 🎫
- ✅ Registrar ventas de entradas
- ✅ Límite de 10 entradas por comprador por concierto
- ✅ Seguimiento de fecha de compra
- ✅ Listado completo de ventas

## 🏗️ Arquitectura del Proyecto

```
Sistema de Gestión de Conciertos/
│
├── org.example/
│   ├── Main.java                          # Punto de entrada y menú principal
│   ├── DBConfig.java                      # Configuración de la base de datos
│   │
│   ├── Objetos/                           # Modelos de datos (POJOs)
│   │   ├── Artista.java                  # Clase modelo para artistas
│   │   ├── Concierto.java                # Clase modelo para conciertos
│   │   └── Entrada.java                  # Clase modelo para entradas
│   │
│   ├── Listar/                           # Consultas SELECT
│   │   ├── Artistas.java                 # Listado de artistas
│   │   ├── Conciertos.java               # Listado de conciertos
│   │   └── VentaEntradas.java            # Listado de ventas
│   │
│   ├── CrearNew/                         # Lógica de creación
│   │   ├── NuevoArtista.java            # Alta de artistas
│   │   ├── NuevoConcierto.java          # Alta de conciertos
│   │   └── NuevaEntrada.java            # Registro de ventas
│   │
│   ├── InsertarBBDD/                    # Operaciones INSERT
│   │   ├── NuevoArtistaBBDD.java        # INSERT de artistas
│   │   ├── NuevoConciertoBBDD.java      # INSERT de conciertos
│   │   └── NuevaEntradaBBDD.java        # INSERT de entradas
│   │
│   ├── Elimina/                         # Operaciones DELETE
│   │   ├── Artista.java                 # DELETE de artistas
│   │   └── Concierto.java               # DELETE de conciertos
│   │
│   ├── Extraer/                         # Obtención de datos específicos
│   │   ├── IDArtista.java               # Obtener ID de artista por nombre
│   │   ├── IDArtistaDisponible.java     # Calcular siguiente ID artista
│   │   ├── IDConciertoDisponible.java   # Calcular siguiente ID concierto
│   │   ├── IDEntradaDisponible.java     # Calcular siguiente ID entrada
│   │   └── NombreArtista.java           # Obtener nombre por ID concierto
│   │
│   └── Busquedas/                       # Validaciones y búsquedas
│       ├── BuscarNombreArtista.java     # Verificar existencia de artista
│       ├── BuscarIDArtista.java         # Verificar existencia por ID
│       ├── BuscarIDConcierto.java       # Verificar existencia de concierto
│       ├── BuscarConciertosArtista.java # Conciertos de un artista
│       ├── BuscarEntradasConcierto.java # Entradas de un concierto
│       ├── BuscarCantidadEntradasComprador.java # Entradas por comprador
│       └── Consultas.java               # Método genérico de consultas
│
└── pom.xml / build.gradle                # Dependencias del proyecto
```

## 🗄️ Modelo de Base de Datos

### Diagrama ER

```
┌─────────────────┐
│    ARTISTA      │
├─────────────────┤
│ ID_A (PK)       │
│ NOMBRE_A        │
│ GENERO_MUSICAL  │
│ PAIS_ORIGEN     │
└────────┬────────┘
         │
         │ 1:N
         │
┌────────▼────────┐
│   CONCIERTO     │
├─────────────────┤
│ ID_C (PK)       │
│ ID_A (FK)       │
│ FECHA           │
│ LUGAR           │
│ PRECIO          │
└────────┬────────┘
         │
         │ 1:N
         │
┌────────▼────────┐
│    ENTRADA      │
├─────────────────┤
│ ID_E (PK)       │
│ ID_C (FK)       │
│ COMPRADOR       │
│ CANTIDAD        │
│ FECHA_COMPRA    │
└─────────────────┘
```

### Script de Creación de Tablas

```sql
-- Tabla ARTISTA
CREATE TABLE ARTISTA (
    ID_A NUMBER PRIMARY KEY,
    NOMBRE_A VARCHAR2(100) NOT NULL,
    GENERO_MUSICAL VARCHAR2(50),
    PAIS_ORIGEN VARCHAR2(50)
);

-- Tabla CONCIERTO
CREATE TABLE CONCIERTO (
    ID_C NUMBER PRIMARY KEY,
    ID_A NUMBER NOT NULL,
    FECHA DATE NOT NULL,
    LUGAR VARCHAR2(100),
    PRECIO NUMBER(10,2),
    CONSTRAINT FK_CONCIERTO_ARTISTA FOREIGN KEY (ID_A) 
        REFERENCES ARTISTA(ID_A)
);

-- Tabla ENTRADA
CREATE TABLE ENTRADA (
    ID_E NUMBER PRIMARY KEY,
    ID_C NUMBER NOT NULL,
    COMPRADOR VARCHAR2(100) NOT NULL,
    CANTIDAD NUMBER NOT NULL,
    FECHA_COMPRA DATE DEFAULT SYSDATE,
    CONSTRAINT FK_ENTRADA_CONCIERTO FOREIGN KEY (ID_C) 
        REFERENCES CONCIERTO(ID_C)
);

-- Índices para mejorar el rendimiento
CREATE INDEX IDX_CONCIERTO_ARTISTA ON CONCIERTO(ID_A);
CREATE INDEX IDX_ENTRADA_CONCIERTO ON ENTRADA(ID_C);
CREATE INDEX IDX_ENTRADA_COMPRADOR ON ENTRADA(COMPRADOR);
```

## 🚀 Configuración y Ejecución

### Requisitos Previos

- ☕ **Java JDK 8 o superior**
- 🗄️ **Oracle Database** (11g o superior) / **Oracle XE**
- 📦 **Maven** o **Gradle** (para gestión de dependencias)
- 🔌 **Oracle JDBC Driver** (ojdbc8.jar o superior)

### Configuración de la Base de Datos

#### 1. Crear archivo `DBConfig.java`

```java
package org.example;

public class DBConfig {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_password";

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
```

**⚠️ IMPORTANTE:** Ajusta los valores según tu configuración:
- `localhost`: IP del servidor Oracle
- `1521`: Puerto de Oracle (por defecto)
- `XE`: SID de tu base de datos
- `tu_usuario` y `tu_password`: Credenciales

#### 2. Añadir Dependencias

**Maven (pom.xml):**
```xml
<dependencies>
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <version>21.9.0.0</version>
    </dependency>
</dependencies>
```

**Gradle (build.gradle):**
```gradle
dependencies {
    implementation 'com.oracle.database.jdbc:ojdbc8:21.9.0.0'
}
```

### Compilación y Ejecución

#### Usando Maven:
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn exec:java -Dexec.mainClass="org.example.Main"
```

#### Usando Java directamente:
```bash
# Compilar
javac -d bin -cp ".:lib/ojdbc8.jar" org/example/**/*.java

# Ejecutar
java -cp "bin:lib/ojdbc8.jar" org.example.Main
```

#### Usando IDE (IntelliJ IDEA / Eclipse):
1. Importar el proyecto como proyecto Maven/Gradle
2. Configurar el JDK
3. Ejecutar `Main.java`

## 🎮 Uso del Sistema

### Menú Principal

```
Elija una opción:
1. Añadir un artista
2. Eliminar un artista
3. Listar todos los artistas
4. Añadir un concierto
5. Eliminar un concierto
6. Listar todos los conciertos
7. Registrar venta de entradas
8. Listar venta de entradas
0. Salir
```

### Flujo de Trabajo Típico

#### 1️⃣ Registrar Artistas

```
> 1
Introduce el nombre del artista
> Metallica
Introduce el genero musical
> Heavy Metal
Introduce el pais de origen
> Estados Unidos

✓ Artista añadido con éxito
```

#### 2️⃣ Crear Conciertos

```
> 4
Indica el nombre del artista del concierto
> Metallica
Indica la fecha del concierto (YYYY-MM-DD)
> 2026-06-15
Indica el lugar del concierto
> Estadio Santiago Bernabéu
Indica el precio de las entradas
> 85.50

✓ Concierto añadido con éxito
```

#### 3️⃣ Vender Entradas

```
> 7
Escriba su nombre
> Juan García

ID    NOMBRE ARTISTA     FECHA DEL CONCIERTO      LUGAR
-------------------------------------------------------------
1     Metallica          15-06-2026              Estadio Santiago Bernabéu

Introduce el número del concierto
> 1
Introduce el número de entradas
> 4

✓ Compra de entradas añadida con éxito
```

## 📊 Validaciones de Negocio

### Artistas
- ❌ No se puede añadir un artista con nombre duplicado (case-insensitive)
- ❌ No se puede eliminar un artista con conciertos programados
- ✅ Búsquedas case-insensitive para mejor UX

### Conciertos
- ❌ No se puede crear un concierto para un artista inexistente
- ❌ No se puede eliminar un concierto con entradas vendidas
- ✅ Validación de formato de fecha (YYYY-MM-DD)
- ✅ Validación de precio (no puede ser negativo)

### Entradas
- ❌ No se puede comprar para un concierto inexistente
- ❌ Máximo 10 entradas por comprador por concierto
- ✅ Control acumulativo (si ya compró 6, solo puede comprar 4 más)
- ✅ Validación de cantidad (debe ser mayor a 0)
- ✅ Registro automático de fecha de compra

## 🎯 Ejemplos de Salida

### Listado de Artistas

```
ID    NOMBRE           GÉNERO MUSICAL        PAIS DE ORIGEN
---------------------------------------------------------
1     Metallica        Heavy Metal          Estados Unidos
2     Coldplay         Pop Rock             Reino Unido
3     Shakira          Pop Latino           Colombia
```

### Listado de Conciertos

```
ID    NOMBRE ARTISTA     FECHA DEL CONCIERTO      LUGAR
-------------------------------------------------------------
3     Coldplay           10-05-2026              Estadio Olímpico
1     Metallica          15-06-2026              Santiago Bernabéu
2     Shakira            20-07-2026              WiZink Center
```

### Listado de Ventas

```
ID  CONCIERTO                           COMPRADOR     CANTIDAD DE ENTRADAS  FECHA DE COMPRA
-------------------------------------------------------------------------------------------------
1   Metallica / 15-06-2026             Juan García   4                     21-04-2026
2   Coldplay / 10-05-2026              María López   2                     21-04-2026
3   Metallica / 15-06-2026             Pedro Ruiz    6                     21-04-2026
```

## 🔧 Características Técnicas

### Patrones de Diseño Implementados

- **DAO (Data Access Object)**: Separación de lógica de negocio y acceso a datos
- **DTO (Data Transfer Object)**: Clases POJO para transferencia de datos
- **Separation of Concerns**: Organización por responsabilidades
- **Single Responsibility**: Cada clase tiene una única responsabilidad

### Buenas Prácticas

- ✅ **Try-with-resources** para cierre automático de conexiones
- ✅ **PreparedStatement** para prevenir SQL Injection
- ✅ **Validación de entrada** en todos los puntos de entrada de usuario
- ✅ **Manejo de excepciones** personalizado por tipo de error
- ✅ **Formateo de salida** con `printf` para tablas alineadas
- ✅ **Transacciones implícitas** con auto-commit
- ✅ **Case-insensitive comparisons** con `UPPER()`

### Seguridad

- 🔒 **SQL Injection**: Uso exclusivo de `PreparedStatement`
- 🔒 **Validación de datos**: Verificación de tipos y rangos
- 🔒 **Integridad referencial**: Validación antes de eliminar
- 🔒 **Control de entrada**: Try-catch en todas las lecturas de usuario

## ⚠️ Manejo de Errores

### Errores Comunes y Soluciones

#### 1. Error de Conexión
```
Error al conectar: Listener refused the connection
```
**Solución:**
- Verificar que Oracle esté ejecutándose
- Comprobar URL, usuario y contraseña en `DBConfig.java`
- Verificar que el puerto 1521 esté abierto

#### 2. Tabla No Encontrada
```
Error: ORA-00942: table or view does not exist
```
**Solución:**
- Ejecutar el script de creación de tablas
- Verificar que el usuario tenga permisos

#### 3. Driver No Encontrado
```
ClassNotFoundException: oracle.jdbc.driver.OracleDriver
```
**Solución:**
- Añadir `ojdbc8.jar` al classpath
- Verificar dependencias Maven/Gradle

## 🎓 Conceptos de Programación Aplicados

### JDBC (Java Database Connectivity)
- **Connection**: Gestión de conexiones a BD
- **Statement vs PreparedStatement**: Consultas dinámicas seguras
- **ResultSet**: Navegación por resultados
- **Try-with-resources**: Auto-close de recursos

### Programación Orientada a Objetos
- **Encapsulación**: Getters/Setters en POJOs
- **Organización en paquetes**: Modularidad
- **Separación de responsabilidades**: Alta cohesión, bajo acoplamiento

### Manejo de Fechas
- **LocalDate**: API moderna de fechas (Java 8+)
- **DateTimeFormatter**: Conversión de cadenas a fechas
- **TO_CHAR**: Formateo de fechas en SQL Oracle

## 📈 Mejoras Futuras

- [ ] Implementar transacciones explícitas
- [ ] Añadir sistema de reservas (sin pago inmediato)
- [ ] Reportes de ventas por artista/período
- [ ] Exportación a CSV/PDF
- [ ] Interfaz gráfica (JavaFX/Swing)
- [ ] Sistema de usuarios y roles
- [ ] Histórico de precios de entradas
- [ ] Notificaciones de conciertos próximos
- [ ] API REST para integración
- [ ] Migración a JPA/Hibernate

## 🐛 Troubleshooting

### El programa no compila
1. Verificar JDK instalado: `java -version`
2. Comprobar dependencias en pom.xml
3. Limpiar y recompilar: `mvn clean install`

### No se listan los datos
1. Verificar que existan datos en las tablas
2. Comprobar consultas SQL directamente en SQL Developer
3. Revisar logs de errores

### Error al insertar datos
1. Verificar constraints de la tabla
2. Comprobar tipos de datos
3. Revisar foreign keys

## 📚 Recursos Adicionales

- [Oracle JDBC Documentation](https://docs.oracle.com/en/database/oracle/oracle-database/21/jjdbc/)
- [Java PreparedStatement](https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html)
- [Oracle SQL Reference](https://docs.oracle.com/en/database/oracle/oracle-database/21/sqlrf/)
- [Maven Getting Started](https://maven.apache.org/guides/getting-started/)

## 🎯 Ejercicios Propuestos

1. **Básico**: Añadir campo "capacidad" a conciertos y validar ventas
2. **Intermedio**: Implementar búsqueda de conciertos por fecha/lugar
3. **Avanzado**: Sistema de descuentos por cantidad de entradas
4. **Experto**: Migrar a arquitectura REST con Spring Boot

## 📊 Estructura de Datos

### Objetos Principales

**Artista:**
```java
- int id
- String nombre
- String generoMusical
- String paisOrigen
```

**Concierto:**
```java
- int id
- String artista
- LocalDate fecha
- String lugar
- double precioEntrada
```

**Entrada:**
```java
- int id
- int idConcierto
- String artistaConcierto
- String comprador
- int cantidad
- LocalDate fechaCompra
```

## 💡 Notas del Desarrollador

### Decisiones de Diseño

1. **IDs Auto-incrementales**: Se calcula MAX(ID) + 1 en lugar de usar SEQUENCES
   - Ventaja: Portabilidad entre diferentes SGBD
   - Desventaja: No thread-safe en alta concurrencia

2. **Validaciones en Java vs BD**: Doble validación
   - Ventaja: Mejor UX con mensajes personalizados
   - La BD mantiene integridad como última línea de defensa

3. **Conexión por operación**: No se mantiene conexión persistente
   - Ventaja: Menor consumo de recursos
   - Adecuado para aplicación de consola con pocos usuarios

## 📄 Licencia

Proyecto educativo para aprendizaje de JDBC y gestión de bases de datos relacionales.

## 👤 Autor

Sistema de gestión desarrollado como proyecto de aprendizaje de Java JDBC, SQL y arquitectura de aplicaciones empresariales.

---

## 🎵 ¡Gestiona tus Conciertos Profesionalmente!

Un sistema completo para administrar eventos musicales, desde el artista hasta la venta de la última entrada. 🎸🎤🎫
