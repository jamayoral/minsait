## Instrucciones para Probar la Aplicación
Requisitos del sistema para poder probar la aplicación:
 - Java 17
 - Docker
 - Git
#### Clona el repositorio:
``` git clone https://github.com/jamayoral/minsait.git ```
#### Accede al directorio de tu aplicación:
``` cd minsait ```
#### Construye el proyecto con Maven:
``` ./mvnw clean install ```
#### Ejecutar el proyecto con Maven:
``` ./mvnw spring-boot:run ```
#### Construye la imagen Docker:
``` docker build -t minsait:latest . ```
#### Ejecuta el contenedor Docker:
``` docker run -p 8080:8080 -t minsait:latest ```
Abre tu navegador web y accede a ``` http://localhost:8080/swagger-ui.html ``` para verificar que la aplicación 
está en ejecución, en el enlace podrás ver una especificación de la API implementada donde se pueden hacer operaciones 
sobre la misma.
