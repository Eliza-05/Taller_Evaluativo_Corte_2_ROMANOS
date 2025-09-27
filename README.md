# TallerEvaluativo_2025-2 - Segundo Corte  

### 👤Integrantes:
- Elizabeth Correa
- Sebastian Ortega
- Belén Quintero
- Nikolas Martinez
- Juan Pablo Contreras 

## 📂 Nombre del repositorio  
Taller_Evaluativo_Corte_2_ROMANOS

---

## 🎯 Objetivo del Taller  
El objetivo de este taller es implementar un **Gestor de Tareas Colaborativo** simplificado, aplicando buenas prácticas de desarrollo de software como **Gitflow**, **principios SOLID**, **patrones de diseño** y una **arquitectura en capas**.  
El sistema permitirá la creación, gestión y filtrado de tareas, persistiendo la información en **MongoDB**.  

---

## 📌 Requerimientos del cliente  
- Crear un gestor de tareas colaborativo (similar a Trello, pero simplificado).  
- Permitir registrar, consultar y filtrar tareas según estado.  
- Garantizar la persistencia de los datos en una base de datos MongoDB.  
- Implementar filtrados eficientes con **Streams en Java**.  

---

## 📌 Requerimientos funcionales  
1. Crear tareas con **título, descripción, fecha y estado** (`PENDIENTE`, `EN_PROGRESO`, `COMPLETADA`).  
2. Listar todas las tareas existentes en el sistema.  
3. Filtrar tareas **COMPLETADAS** utilizando **Java Streams**.  
4. Persistir los datos en **MongoDB** para asegurar su almacenamiento.  

---

## ⚙️ Pre-requisitos técnicos  
- **Java OpenJDK** 17.x.x  
- **Apache Maven** 3.9.x  
- **JUnit** 5.x.x  
- **Git** y cuenta de **GitHub**  
- **Docker**  
- **Jacoco**  
- **SonarQube**  

---

## 🏗️ Arquitectura (MVC)

El backend sigue la **arquitectura Modelo–Vista–Controlador (MVC)**. La estructura de carpetas se organizó para mantener separación de responsabilidades y buenas prácticas de ingeniería:


```
📁 src/main/java/edu/dosw/taller/
│
├── 📁 configs/          # ⚙️ Configuraciones globales (Swagger, seguridad, etc.)
├── 📁 controller/       # 🌐 Controladores REST (exposición de endpoints)
├── 📁 model/           # 📊 Entidades y modelos de datos
├── 📁 persistence/     # 🗄️ Repositorios (interfaces con la BD MongoDB)
├── 📁 services/        # 🔧 Servicios con la lógica de negocio
├── 📁 utils/           # 🛠️ Utilidades comunes
└── 📄 Application.java  # 🚀 Clase principal Spring Boot
``` 

### 📋 Descripción de capas:

| **Capa**         | **Responsabilidad**                                        | **Tecnologías / Anotaciones** |
|------------------|------------------------------------------------------------|--------------------------------|
| **Controller**   | Manejo de peticiones HTTP y exposición de endpoints REST.  | Spring MVC, `@RestController` |
| **Model**        | Contiene el modelo de dominio y submódulos relacionados.   | Java classes, Lombok, DTOs |
| ├─ **Entities**  | Definición de entidades principales (Task, User, etc.).    | `@Document`, `@Entity` |
| ├─ **Components**| Clases auxiliares/componentes dentro del dominio.          | Beans, helpers de dominio |
| ├─ **Persistence/Repository** | Interfaces de acceso a datos y consultas CRUD. | MongoDB, Spring Data |
| └─ **Services**  | Servicios asociados al modelo (lógica de negocio puntual). | `@Service` |
| **Configs**      | Configuración global de la aplicación.                     | Swagger, Security, CORS |

---

## 📊 Diagramas del sistema

Para la documentación y análisis del proyecto se generaron los siguientes diagramas UML:

- **Diagrama de Componentes Especifico**  
  ![](docs/imagenes/)

- **Diagrama de Clases**  
  ![](docs/imagenes/)





