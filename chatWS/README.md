## Install MongoDB

First you must install Mongo.
- Go to the following [link](https://www.mongodb.com/try/download/community), select the desired package (version and OS) and download it.
- Execute it. Select the Complete version and Run service as Network Service user. Later make sure to select the option to download Compass.
- Copy the route to Mongo server. The default one should be the following depending on the version downloaded: C:\Program Files\MongoDB\Server\5.0\bin
- Go to "Este Equipo> Propiedades> Confifuración avanzada del sistema> variables de entorno" and add a new path by copying the previous one.
- After setting the path, open the terminal and run the following command in order to initialize the server
     ```
     mongod
    ```
- After having initialized the server you can access mongo with the following command:
    ```
     mongo
    ```

- Run the app using the springboot extension in your code editor
- Use postman to verify if the app is working correctly :
    ´´´
    1. Create a post in http://localhost:8080/mensaje and select >Body>Raw>JSON and introduce the provided ChatWS.postman.collectio.json
    2. Create a get with http://localhost:8080/mensajes/messielmejor@gmail.com
    3. Create a get with http://localhost:8080/mensajes
    4. Use the post to send a message and verify if it works cheching both gets 
    ´´´
