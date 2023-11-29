# equiposWS
WebService for DB access.

## API specification

#### `getAllJugadores`
Obtains a list of all _jugadores_.

- **REQUEST**
    - **Method:** `GET`
    - **URL:** `/jugadores`
- **RESPONSES**
    - `200`: OK. Returns list of _jugadores_:
        ```json
        [
            {
                "dni": string,
                "nombre": string,
                "apellidos": string,
                "alias": string | null,
                "imagen": string | null,
                "posicionNombre": string,
                "equipoNombre": string
            },
            ...
        ]
        ```
    - `404`: There are no _jugadores_.


#### getJugadoresEquipo
Obtains a list of all _jugadores_ for an especific _equipo_.

- **REQUEST**
    - **Method:** `GET`
    - **URL:** `/jugadores/<nombre_equipo>`
- **RESPONSES**
    - `200`: OK. Returns _jugador_ data:
        ```json
        [
            {
                "dni": string,
                "nombre": string,
                "apellidos": string,
                "alias": string | null,
                "imagen": string | null,
                "posicionNombre": string,
                "equipoNombre": string
            },
            ...
        ]
        ```
    - `400`: _Equipo_ doesn't exist.
    - `404`: There are no _jugadores_ for that _equipo_.


#### getJugador
Obtains an specific _jugador_.

- **REQUEST**
    - **Method:** `GET`
    - **URL:** `/jugador/<dni>`
- **RESPONSES**
    - `200`: OK. Returns _jugador_ data:
        ```json
        {
            "dni": string,
            "nombre": string,
            "apellidos": string,
            "alias": string | null,
            "imagen": string | null,
            "posicionNombre": string,
            "equipoNombre": string
        }
        ```
    - `404`: _Jugador_ doesn't exist.


#### addJugador
Adds a new _jugador_.

- **REQUEST**
    - **Method:** `POST`
    - **URL:** `/jugador`
    ```json
    {
        "dni": string,
        "nombre": string,
        "apellidos": string,
        "alias": string | null,
        "imagen": string | null,
        "posicionNombre": string,
        "equipoNombre": string
    }
    ```
- **RESPONSES**
    - `201`: _Jugador_ created. Returns added _jugador_ data:
        ```json
        {
            "dni": string,
            "nombre": string,
            "apellidos": string,
            "alias": string | null,
            "imagen": string | null,
            "posicionNombre": string,
            "equipoNombre": string
        }
        ```
    - `400`: Error in request (see error message).
        -  _Equipo_/_posicion_ doesn't exist.
        -  _Jugador_ already exists.
        -  The _equipo_'s _posicion_ is full.
        -  Other error in request.



#### deleteJugador
Deletes a _jugador_.

- **REQUEST**
    - **Method:** `DELETE`
    - **URL:** `/jugador/<dni>`
- **RESPONSES**
    - `200`: _Jugador_ deleted. Returns deleted _jugador_ data:
        ```json
        {
            "dni": string,
            "nombre": string,
            "apellidos": string,
            "alias": string | null,
            "imagen": string | null,
            "posicionNombre": string,
            "equipoNombre": string
        }
        ```
    - `400`: Error in request (see error message).
    - `404`: _Jugador_ doesn't exist.
    - `500`: Reached lower limit for _posicion_.


#### updateJugador
Updates a _jugador_'s data. Overwrites everything.

- **REQUEST**
    - **Method:** `PUT`
    - **URL:** `/jugador`
    ```json
        {
            "dni": string,
            "nombre": string,
            "apellidos": string,
            "alias": string | null,
            "imagen": string | null,
            "posicionNombre": string,
            "equipoNombre": string
        }
    ```
- **RESPONSES**
    - `200`: _Jugador_ deleted. Returns deleted _jugador_ data:
        ```json
        {
            "dni": string,
            "nombre": string,
            "apellidos": string,
            "alias": string | null,
            "imagen": string | null,
            "posicionNombre": string,
            "equipoNombre": string
        }
        ```
    - `400`: Error in request (see error message).
        -  _Equipo_/_posicion_ doesn't exist.
        -  _Jugador_ already exists.
        -  The _equipo_'s _posicion_ is full.
        -  Other error in request.
    - `404`: _Jugador_ doesn't exist.
    - `500`: Reached lower limit for _posicion_.


#### getAllEquipos
Obtains all _equipos_.

- **REQUEST**
    - **Method:** `GET`
    - **URL:** `/equipos`
- **RESPONSES**
    - `200`: OK. Returns list of _equipos_:
        ```json
        [
            {
                "nombre": string,
                "escudo": bytes | null
            },
            ...
        ]
        ```
    - `404`: There are no _equipos_.


#### addEquipo
Adds a new _equipo_, updating its _plantillas_.

- **REQUEST**
    - **Method:** `POST`
    - **URL:** `/equipo`
    ```json
    {
        "nombre": string,
        "escudo": bytes | null
    }
    ```
- **RESPONSES**
    - `201`: _Equipo_ created. Returns added _equipo_ data:
        ```json
        {
            "nombre": string,
            "escudo": bytes | null
        }
        ```
    - `400`: Error in request (see error message).
        -  _Equipo_ already exists.
        -  Other error in request.


#### getAllPosiciones
Obtains all _posiciones_.

- **REQUEST**
    - **Method:** `GET`
    - **URL:** `/posiciones`
- **RESPONSES**
    - `200`: OK. Returns list of _posiciones_:
        ```json
        [
            {
                "nombre": string,
                "maxJugadores": int
            },
            ...
        ]
        ```
    - `404`: There are no _posiciones_.


#### getPlantillaEquipo
Obtains all _plantillas_ for an specific _equipo_.

- **REQUEST**
    - **Method:** `GET`
    - **URL:** `/plantilla/<nombre_equipo>`
- **RESPONSES**
    - `200`: OK. Returns list of _plantillas_ for the specified _equipo_:
        ```json
        [
            {
                "nombre": string,
                "maxJugadores": int
            },
            ...
        ]
        ```
    - `404`: There are no _posiciones_.


#### coffee
Brews a cup of coffee.

- **REQUEST**
    - **Method:** `GET`
    - **URL:** `/coffee`
- **RESPONSES**
    - `418`: Server refuses to brew coffee because it is, permanently, a teapot.



## Instalation
1. Install [Maven](https://maven.apache.org/install.html) and [Java SE 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (or [OpenJDK 17](https://openjdk.org/projects/jdk/17/)).
2. Remember to set the `JAVA_HOME` environment variable.
3. Download and install [MySQL Community Server 8](https://dev.mysql.com/downloads/mysql/) and, optionally, [MySQL Workbench](https://dev.mysql.com/downloads/workbench/).
4. Start MySQL server (if it's not started already):
    - Linux
        ```bash
        $ sudo systemctl start mysqld
        ```
    - Windows (PowerShell) (MySQL path is usually `C:\Program Files\MySQL\MySQL Server 8.X`)
        ```powershell
        C:\> & <mysql-path>\bin\mysqld
        ```
5. If you haven't set up a password:
    1. Access the MySQL console
        - Linux
            ```bash
            $ mysql -u root
            ```
        - Windows (PowerShell)
            ```powershell
            C:\> & <mysql-path>bin\mysql -u root
            ```
    2. Set the password.
        ```sql
        mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
        mysql> FLUSH PRIVILEGES;
        ```
    3. Exit MySQL.
        ```sql
        mysql> exit;
        ```
6. Use the [`db_create.sql`](db_create.sql) script to create the `tiwp3` database and the tables inside the database.

    If your MySQL user/password is different from `root:root`, remember to set it up in [`application.properties`](src/main/resources/application.properties) (`spring.datasource.username` & `spring.datasource.password`), and double check the MySQL port in `spring.datasource.url`.
    - Linux
        ```bash
        $ mysql -u root -proot < db_create.sql
        ```
    - Windows (PowerShell)
        ```powershell
        C:\> & <mysql-path>\bin\mysql -u root -proot
        ```
        ```sql
        mysql> source db_create.sql
        mysql> exit;
        ```
8. (OPTIONAL) Install [Postman](https://www.postman.com/downloads/). If you're using Linux, make sure to also install `openssl`.


### VSCode extensions (optional)
- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)



## Execution
1. Remember to launch MySQL.
2. Run the WS with:
    - Linux
        ```bash
        mvn spring-boot:run
        ```
    - Windows (PowerShell)
        ```powershell
        & .\mvnw.cmd spring-boot:run
        ```

You can import the collection [`equiposWS.postman_collection.json`](equiposWS.postman_collection.json) into [Postman](https://www.postman.com/downloads/) in order to run the predefined requests (you could also use `curl` to generate the requests).
