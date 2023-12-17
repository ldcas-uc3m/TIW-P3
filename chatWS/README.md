## Instalation
1. Install [Maven](https://maven.apache.org/install.html) and [Java SE 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) (or [OpenJDK 17](https://openjdk.org/projects/jdk/17/)).
2. Remember to set the `JAVA_HOME` environment variable.
3. Download and install [MongoDB 5.0](https://www.mongodb.com/) and [mongosh](https://www.mongodb.com/docs/mongodb-shell/install/), optionally, [MongoDB Compass](https://www.mongodb.com/products/tools/compass).  
   For Windows:
    1. Go to the following [link](https://www.mongodb.com/try/download/community), select the desired package (version and OS) and download it.
    2. Execute it. Select `Complete version` and `Run service as Network Service user`. Later make sure to select the option to download Compass.
    3. Copy the route to Mongo server. The default one should be the following depending on the version downloaded: `C:\Program Files\MongoDB\Server\5.0\bin`
    4. Go to `Este Equipo` > `Propiedades` > `Confifuración avanzada del sistema` > `Variables de entorno` and add a new path by copying the previous one.
4. [Launch MongoDB (`mongod`)](https://www.mongodb.com/docs/manual/tutorial/manage-mongodb-processes/).
5. Run the [`db_create.js`](db_create.js) script in MongoDB to create the `mensajes` collection inside the `mschat` database:
    ```
    mongosh --file db_create.js
    ```
6. (OPTIONAL) Install [Postman](https://www.postman.com/downloads/). If you're using Linux, make sure to also install `openssl`.


### VSCode extensions (optional)
- [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)
- [Spring Boot Extension Pack](https://marketplace.visualstudio.com/items?itemName=vmware.vscode-boot-dev-pack)



## Execution
1. Remember to launch MongoDB.
2. Run the WS with:
    - Linux
        ```bash
        mvn spring-boot:run
        ```
    - Windows (PowerShell)
        ```powershell
        & .\mvnw.cmd spring-boot:run
        ```

You can import the collection [`ChatWS.postman_collection.json`](ChatWS.postman_collection.json) into [Postman](https://www.postman.com) in order to run the predefined requests (you could also use `curl` to generate the requests).
