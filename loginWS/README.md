# equiposWS
WebService for DB access.

## API specification

#### `registrarUsuario`
Stores new users on the database.

- **REQUEST**
    - **Method:** `POST`
    - **URL:** `/registrarUsuario`
        ```
        [
            {
                "userCorreo": string,
                "userEquipo": string|null,
                "userApellido": string,
                "userPassword": string,
                "userNombre": string
            },
            ...
        ]
        ```
- **RESPONSES**
    - `200`: OK. Returns list of _usuarios_:
        ```
        [
            {
                "userCorreo": string,
                "userEquipo": string | Atletico de Madrid,
                "userApellido": string,
                "userPassword": string,
                "userNombre": string
            },
            ...
        ]
        ```
    - `404`: There are missing values (nombre, correo, apellido, password).
        ```
        {
            "code": "CAMPOS FALTANTES",
            "message": "Uno de los campos obligatorios no ha sido rellenado"
        }
        ```

    - `404`: User already registered.
        ```
        {
            "code": "USER_ALREADY_REGISTERED",
            "message": "User 'string ' already registered"
        }
        ```
#### AdminLogin
Logs if the values correspond to those in _administrador_.

- **REQUEST**
    - **Method:** `POST`
    - **URL:** `/Adminlogin`
- **RESPONSES**
    - `200`: OK. Returns successful message:
        ```
        [
           Authentication successful
            
        ]
        ```
    - `400`: _Admin_ doesn't exist:
        ```
        {
            "code": "ADMIN_DOES_NOT_EXIST",
            "message": "Admin 'string' does not exist"
        }
        ```
    - `404`: wrong password:
        ```
        {
            "code": "WRONG_PASSWORD",
            "message": "Incorrect password"
        }
        ```
#### UserLogin
Logs if the values correspond to those in _administrador_.

- **REQUEST**
    - **Method:** `POST`
    - **URL:** `/Userlogin`
- **RESPONSES**
    - `200`: OK. Returns successful message:
        ```
        [
           Authentication successful
            
        ]
        ```
    - `400`: _User_ doesn't exist:
        ```
        {
            "code": "USER_DOES_NOT_EXIST",
            "message": "User 'mail' does not exist"
        }
        ```
    - `404`: wrong password:
        ```
        {
            "code": "WRONG_PASSWORD",
            "message": "Incorrect password"
        }
        ```



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
