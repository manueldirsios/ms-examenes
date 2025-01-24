# ms-examenes

## Prerequisitos

- Java (17)
- GRADLE(8.5+)
- LOOMBOK

## Configuracion

El proyecto esta desarrollado en arquitectura de microservicios implementa `Spring boot 3.2.0` `JNUIT`  `JWT`  `Jacoco` `Spring Security` `spring-boot-starter-web` y  `Core 6.1.1`

## Estructura de Proyecto

| Modulo                                         | Contenido                                                                                                                                                                               |
| ---------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Application.java**				 | Clase principal de arranque y escaneo del proyecto
| **SwaggerConfig.java**				 | Clase de configuracion para la ui y documentacion de servicios con de swagger http://localhost:8080/swagger-ui/index.html                           |
| **build.gradle**                                    | Dependencias, Compilacion y empaquetado                                                                                                                                                 |
| **TransaccionExceptionHandler.java**                          | Clase manejadora de excepciones




## Dependencias con otros aplicativos
| Satelite		                       | RECURSO                 | Tipo de recurso                                   |Estructura de la solicitud       |
| ------------------------------------ | -----------------------| --------------------------------------------------| --------------------------------|
| **POR DEFINIR**             	   |http://test    | SERVICIO REST  						     			    | JSON REQUEST|   
| **POR DEFINIR**             	   |https://tes    | SERVICIO REST                  |JSON REQUEST|						                                                          




## Despliegue de aplicacion

Se desplega en tomcat local o contenedores.

| Instancia             | Puerto | Build                                     | Run                                             |
| --------------------- | ------ | ----------------------------------------- | ----------------------------------------------- |
| **MS-EXAMENES**| 8080   | ./gradlew build       | tomcat embebido                            |