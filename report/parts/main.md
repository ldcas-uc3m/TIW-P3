# Introducción

La consiguiente práctica consistirá en la última versión de la aplicación desarrollada en la anterior entrega, la cual utilizaba una base de datos relacional, desarrollada en MySQL y con el uso de JPA o JDBC entre otras herramientas. En este caso, se utilizarán tecnologías como las bases de datos Not Only SQL (NoSQL) utilizando MongoDB y se cambiará la estructura interna de la aplicación para tener un producto más orientado el mercado actual. 

La aplicación web realizada está basada en la posibilidad de agregar, editar y eliminar jugadores que podrían formar parte de la plantillas de los clubes más prestigiosos de España (eg: Atlético de Madrid, _er_ Beti, Rayo Vallecano, F.C. Barcelona... etc).  Además de esto, se ha añadido la posibilidad de seleccionar un equipo favorito, la creación de un inicio de sesión (con cuentas con privilegios de administrador y de  usuario) y un pequeño sistema de chat con el objetivo de permitir a los usuarios interactuar entre sí.

El avance de esta aplicación respecto a la versión anterior es la implementación de los llamados “microservicios”, que nos permiten tener una aplicación más fácil de mantener, mejor encapsulada y más resistente a errores gracias a esta independencia entre funcionalidades.En estas estructuras, hay un módulo principal que utiliza las distintas funcionalidades que le proporcionan los demás servicios, de forma que este pueda desempeñar correctamente las tareas que se le requieren a la aplicación. 

En nuestro caso, este mödulo principal es el “front” (frontend)  es el que utilizará los distintos Web Services que ofrecen los otros módulos. Del módulo equiposWS utiliza las operaciones de gestión del banco de jugadores, del de loginWS todo aquello relacionado con el inicio de sesión y la gestión de usuarios y por último, a chatWs le solicita todas las funcionalidades que respectan al servicio de mensajería.

A rasgos generales, ya que en el proyecto aparecen numerosos archivos de configuración y propiedades (pom.xml, los .properties, los .jar, mvnw …etc) esta es la estructura general del proyecto, y estos son sus archivos más relevantes: 

#Front















# Conclusiones

La realización de esta práctica ha supuesto un reto para el equipo y en ciertas ocasiones una ocasión perfecta para aprender a enfrentarnos a problemas reales que pueden aparecer en el desarrollo de una aplicación real de misma naturaleza. Creemos que el hecho de incluir una base de datos real del tipo NoSQL nos prepara para la forma de trabajar del mercado laboral, ya que son tecnologías que están en el día a día de aplicaciones de este estilo. 

Además, ha sido un gran descubrimiento el aprender de la existencia de los microservicios, que de una forma muy simple (divide y venderás) es capaz de mejorar el mantenimiento, la encapsulación y la gestión de errores entre otros muchos aspectos, de forma verdaderamente significativa.


