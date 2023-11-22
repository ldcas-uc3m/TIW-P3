


## Instalation
1. Install [Maven](https://maven.apache.org/install.html).
2. Remember to set JAVA_HOME.
3. Download and install [MySQL Community Server 8.1.0](https://dev.mysql.com/downloads/mysql/) and, optionally, [MySQL Workbench](https://dev.mysql.com/downloads/workbench/).
4. Start MySQL server (if it's not started already)
    - Linux
        ```bash
        $ sudo systemctl start mysqld
        ```
    - Windows (MySQL path is usually `C:\Program Files\MySQL\MySQL Server 8.X`)
        ```powershell
        C:\> & <mysql-path>\bin\mysqld
        ```
5. Create and import the database  
    If you haven't set up a password:
    1. Access the MySQL console
        - Linux
            ```bash
            $ mysql -u root
            ```
        - Windows
            ```powershell
            C:\> & <mysql-path>bin\mysql -u root
            ```
        If you have set a password for root on installation (here we'll assume the password is `root`), add the flag `-proot`.
    2. Set the password.
        ```sql
        mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
        mysql> FLUSH PRIVILEGES;
        ```
    3. Exit MySQL.
        ```sql
        mysql> exit;
        ```
    4. Use the [`db_create.sql`](db_create.sql) script to create the tables inside of the `usersdbSP` database.
        - Linux
            ```bash
            $ mysql -u root -proot < <path-to>/discos.sql
            ```
        - Windows
            ```powershell
            C:\> & <mysql-path>\bin\mysql -u root -proot
            ```
            ```sql
            mysql> source <path-to>\db_create.sql
            mysql> exit;
6. Install [Postman](https://www.postman.com/downloads/). If you're using Linux, make sure to also install `openssl`.


## Execution
```
mvn spring-boot:run
```

Import the collection [`equiposWS.postman_collection.json`](equiposWS.postman_collection.json) into [Postman](https://www.postman.com/downloads/) in order to run the requests (you could also use `curl` to generate the requests).


VsCode extensions:
- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)