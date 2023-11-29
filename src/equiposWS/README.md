# equiposWS
WebService for DB access.

## API specification
TODO


## Instalation
1. Install [Maven](https://maven.apache.org/install.html) and [Java SE 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (or [OpenJDK 17](https://openjdk.org/projects/jdk/17/)).
2. Remember to set the `JAVA_HOME` environment variable.
3. Download and install [MySQL Community Server 8](https://dev.mysql.com/downloads/mysql/) and, optionally, [MySQL Workbench](https://dev.mysql.com/downloads/workbench/).
4. Start MySQL server (if it's not started already):
    - Linux
        ```bash
        $ sudo systemctl start mysqld
        ```
    - Windows (MySQL path is usually `C:\Program Files\MySQL\MySQL Server 8.X`)
        ```powershell
        C:\> & <mysql-path>\bin\mysqld
        ```
5. If you haven't set up a password:
    1. Access the MySQL console
        - Linux
            ```bash
            $ mysql -u root
            ```
        - Windows
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
    - Windows
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
    ```
    mvn spring-boot:run
    ```

You can import the collection [`equiposWS.postman_collection.json`](equiposWS.postman_collection.json) into [Postman](https://www.postman.com/downloads/) in order to run the predefined requests (you could also use `curl` to generate the requests).
