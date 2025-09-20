# [Inventory (inv)]

Este componente basicamente es un Listener que se encarga de esuchar eventos que llegan a una cola RabbitMQ. Éste lee los mensajes y realiza la logica necesaria para osteriormente escribir sobre una base de datos mongo para tener actualizado el stock de productos o inventario de las diferentes tiendas.

## Tecnologías Utilizadas

Este proyecto fue desarrollado utilizando las siguientes herramientas y *frameworks*:

* **Java:** [OpenJDK 17]
* **Spring Boot:** [3.2.0]
* **Spring WebFlux:** Para desarrollo reactivo y asíncrono.
* **Spring Data MongoDB Reactive:** Persistencia no bloqueante.
* **Spring AMQP:** Para la comunicación basada en eventos con RabbitMQ.
* **Base de Datos:** MongoDB.
* **Mensajería:** RabbitMQ.
* **Build Tool:** Gradle

---

## Estructura del Proyecto

El proyecto sigue la arquitectura Hexagonal (Puertos y Adaptadores) para separar la lógica de negocio de la infraestructura:

* `application/`: Contiene la clase principal y los beans de configuracion.
* `domain/`: Contiene los modelos de negocio y los casos de uso.
* `infrastructure/`: Contiene los adaptadores de persistencia (MongoDB) y mensajería.
* `[model]/`: Módulos de dominio y modelos para el manejo de data.
* `[usecase]/`: Módulo de lógica de negocio.

Cada modulo tiene su carpeta test para realizar lso test unitarios con mockito. En ests caso solo se realizaron para el caso de uso ubicado en domain/usecase/main/test/java/com.sigeinv.storeinventory/StoreInventoryUseCaseTest

---

## ⚙️ Configuración y Ejecución

Para levantar este servicio de inventario, lo unico que se debe hacer es ubicarse en applications/app-service/main/java y dar clic derecho run InvApplication

1.   **[Docker]** para levantar MongoDB y RabbitMQ fácilmente.
2.   **[Build]** Gradle.

### 1. Levantar la Infraestructura (Docker)

Utilizamos Docker Compose para iniciar las dependencias de MongoDB y RabbitMQ. Utilizamos el comando docker compose up
