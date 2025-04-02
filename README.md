
# 🎓 Sistema de Gestión Estudiantil - Universidad

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.4-green.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-23-blue.svg)](https://www.java.com/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue.svg)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Sistema completo para gestión académica con interfaz web (Thymeleaf) y API REST, implementando:
- CRUD de Estudiantes
- Patrón DTO
- Validaciones
- Persistencia con JPA/Hibernate
- Arquitectura MVC

## 🌟 Características
- **Frontend**: Templates Thymeleaf con Bootstrap 5
- **Backend**: API RESTful documentada
- **Base de Datos**: PostgreSQL con herencia de entidades
- **Patrones**: Repository, Service, DTO
- **Validaciones**: Campos obligatorios y únicos

## 🛠 Stack Tecnológico
| Capa           | Tecnologías                                                                 |
|----------------|----------------------------------------------------------------------------|
| **Frontend**   | Thymeleaf, Bootstrap 5, Bootstrap Icons                                   |
| **Backend**    | Java 23, Spring Boot 3.1.4                                                |
| **Persistencia** | JPA/Hibernate, PostgreSQL                                               |
| **Herramientas** | Maven, Lombok, Postman                                                  |

## 🏗 Estructura del Proyecto
src/
├── main/
│   ├── java/com/universidad/
│   │   ├── controller/      # Controladores REST y MVC
│   │   ├── dto/             # Objetos de Transferencia (EstudianteDTO, DocenteDTO)
│   │   ├── model/           # Entidades JPA (herencia de Persona)
│   │   ├── repository/      # Repositorios Spring Data
│   │   ├── service/         # Lógica de negocio
│   │   └── Application.java # Punto de entrada
│   └── resources/
│       ├── templates/       # Vistas Thymeleaf
│       │   ├── estudiantes/ # CRUD web
│       │   └── fragments/   # Componentes reutilizables
│       └── application.properties # Config DB


## 📌 Diagrama de Entidades
classDiagram
    class Persona {
        <<abstract>>
        +Long id
        +String nombre
        +String apellido
        +String email
        +LocalDate fechaNacimiento
    }
    
    class Estudiante {
        +String numeroInscripcion
    }
    
    class Docente {
        +String nroEmpleado
        +String departamento
    }
    
    Persona <|-- Estudiante
    Persona <|-- Docente


## 🚀 Instalación
1. **Requisitos**:
   - Java 23 JDK
   - PostgreSQL 16+
   - Maven 3.8+

2. **Configuración**:
  
   git clone https://github.com/BrandonAmezagaFCPN/CRUD-FINAL-ESTUDIANTES.git
   cd CRUD-FINAL-ESTUDIANTES
   

3. **Base de Datos**:
   - Crear DB `universidad` en PostgreSQL
   - Configurar credenciales en `application.properties`:
     
     spring.datasource.url=jdbc:postgresql://localhost:5433/universidad
     spring.datasource.username=postgres
     spring.datasource.password=tu_password
     

4. **Ejecución**:
   mvn spring-boot:run
   ▶ Acceder a: [http://localhost:8080](http://localhost:8080)

## 📚 API REST Endpoints
### Estudiantes (`/api/estudiantes`)
| Método | Endpoint               | Descripción                      | Ejemplo JSON |
|--------|------------------------|----------------------------------|--------------|
| GET    | `/`                    | Listar todos                    | [↓ Ver ejemplo](#) |
| GET    | `/{id}`                | Obtener por ID                  | - |
| POST   | `/`                    | Crear nuevo                     | ```json
                                         {
                                           "nombre": "Ana",
                                           "apellido": "Martínez",
                                           "email": "ana@universidad.edu",
                                           "fechaNacimiento": "2000-05-15",
                                           "numeroInscripcion": "EST2024001"
                                         }
                                         ``` |
| PUT    | `/{id}`                | Actualizar                      | - |
| DELETE | `/{id}`                | Eliminar                        | - |


**Características UI**:
- Notificaciones con códigos HTTP (201, 200, 204)
- Validaciones en formularios
- Confirmación para eliminación
- Diseño responsive con Bootstrap

## 🧪 Pruebas
1. **Postman**: Importar colección desde `docs/postman/`
2. **Pruebas Manuales**:
   # Crear estudiante
   curl -X POST -H "Content-Type: application/json" \
   -d '{"nombre":"Test","apellido":"User","email":"test@mail.com","fechaNacimiento":"2001-01-01","numeroInscripcion":"TEST001"}' \
   http://localhost:8080/api/estudiantes


## 📦 Dependencias Maven

<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- PostgreSQL -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>


## 🤝 Contribución
1. Haz fork del proyecto
2. Crea tu feature branch (`git checkout -b feature/nueva-funcionalidad`)
3. Commit cambios (`git commit -m 'Add some feature'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📜 Licencia
MIT © [Brandon Amezaga](https://github.com/BrandonAmezagaFCPN)
