TimeManager – Gestión de horas trabajadas
TimeManager es una aplicación web diseñada para gestionar proyectos y registrar horas trabajadas por parte de los usuarios. Permite administrar usuarios, asignarlos a proyectos y controlar las horas registradas, ofreciendo una experiencia diferenciada según el rol: usuario estándar o administrador.

El sistema está compuesto por un backend desarrollado en Spring Boot y un frontend implementado en Angular.


## 1. Tecnologías utilizadas
### Backend
* Java (a partir de la versión 17)
* Spring Boot
* Spring Security con JWT
* Hibernate / JPA
* Maven
* MySQL

### Frontend
* Angular (a partir de la versión 15)
* TypeScript
* HTML / CSS


## 2. Estructura del repositorio

/proyecto-horas-backend
    ├── src/main/java
    ├── src/main/resources
    ├── src/test/java
    └── pom.xml

/proyecto-horas-frontend
    ├── src/app
    ├── src/assets
    ├── angular.json
    └── package.json

    
## 3. Documentación técnica
El proyecto incluye documentación generada automáticamente:

### Frontend (TypeDoc)
Documentación generada automáticamente con TypeDoc.

* Ruta relativa desde el repositorio --> /docs/
* URL completa --> https://github.com/JessLar/PROYECTO_DAW_LarioJessica/tree/d49c97deeef0e18655d241a330ed547d88a6c972/docs/
* > **Recomendación:** Para visualizar correctamente la documentación, descarga la carpeta y abre el archivo `index.html` en tu navegador.

### Backend (JavaDoc)
Documentación generada automáticamente con JavaDoc.

* Ruta relativa desde el repositorio --> /backend-docs/
* URL completa --> https://github.com/JessLar/PROYECTO_DAW_LarioJessica/tree/d49c97deeef0e18655d241a330ed547d88a6c972/docs-backend/apidocs
* > **Recomendación:** Para visualizar correctamente la documentación, descarga la carpeta y abre el archivo `index.html` en tu navegador.


## 4. Cómo ejecutar el proyecto

### Backend
cd proyecto-horas-backend
mvn spring-boot:run

### Frontend
cd proyecto-horas-frontend
npm install (solo la primera vez)
ng serve

## 5. Pruebas

### Tests unitarios 
Los tests del backend están ubicados en: 
* Ruta relativa desde el repositorio --> /proyecto-horas-backend/src/test/java/com/proyecto/horas
* URL completa --> https://github.com/JessLar/PROYECTO_DAW_LarioJessica/tree/d49c97deeef0e18655d241a330ed547d88a6c972/proyecto-horas-backend/src/test/java/com/proyecto/horas
  
Son pruebas de caja blanca que validan la lógica interna del backend.


## 9. Autora
Proyecto desarrollado por Jessica, como parte del Trabajo Fin de Grado.
