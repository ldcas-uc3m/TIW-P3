db = connect('mongodb://localhost/mschat');

// you need to insert one item in order to create db/collection

db.mensajes.insertOne(
    {
        emailori: "hello",
        emaildest: "world",
        mensaje: "hello, world"
    }
)