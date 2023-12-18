# Práctica 3: Plantilla de una liga de fútbol con Springboot
By Luis Daniel Casais Mezquida, Pablo Montero Poyatos, Javier Moreno Yébenes & Alberto Urbano Ballesteros  
Tecnologías Informáticas para la Web 23/24  
Bachelor's Degree in Computer Science and Engineering  
Universidad Carlos III de Madrid


## Enunciado de la práctica

### Funcionalidad de la práctica
Una aplicación que gestione las plantillas de una liga formada por 20 equipos. 

Cada plantilla tiene las mismas limitaciones que [la práctica anterior](https://github.com/ldcas-uc3m/TIW-P2). Es decir, un jugador debe tener esta información:
- Nombre, Apellidos
- DNI
- Alias
- Posición. Exclusivamente delantero, defensa, medio y portero.
- Foto del jugador

Cada plantilla tiene 25 jugadores: 3 porteros, 8 defensas, 8 medios y 6 delanteros.


La aplicación tendrá un login de acceso, puede haber dos tipos de usuarios:
1. Administrador
2. Usuario de sólo visualización

Cada usuario podrá tener mínimo el nombre, apellidos y la clave primaria será el correo electrónico. Para los usuarios normales, también tendrá (como información opcional) su equipo favorito, y así después de logado se le mostrará la información de su equipo como página por defecto.

Los usuarios podrán intercambiar mensajes entre ellos a modo de chat.


### Componentes / microservicios de la aplicación

- Una aplicación que será el frontal que estará corriendo en el puerto 8020 ([`front`](front))
- Un microservicio de usuarios, estará corriendo en el puerto 8021 ([`loginWS`](loginWS))
- Un microservicio de equipos y plantilla, estará corriendo en el puerto 8022 ([`equiposWS`](equiposWS))
- Un microservicio de mensajes, estará corriendo en el puerto 8080 ([`chatWS`](chatWS))

Las restricciones por cada equipo son las siguientes:

- La aplicación será distribuida
- Deberá usarse MongoBD y MySQL como BBDD
- La aplicación frontal podrá realizarse en JEE, Angular o, preferiblemente, en Thymeleaf.



## Instalación y ejecución

Accede a la carpeta de cada componente ([`chatWS`](chatWS), [`equiposWS`](equiposWS), [`front`](front), [`loginWS`](loginWS)) y sigue las instrucciones para ejecutarlos todos.  
Una vez hecho eso, entra en http://localhost:8020 para acceder al frontal de la aplicación. No es bonito (ni por fuera ni por dentro), pero funciona.
