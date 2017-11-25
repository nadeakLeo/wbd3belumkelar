var express = require('express');
var router = express.Router();
var MongoClient = require('mongodb').MongoClient;

/* GET users listing. */
router.get('/', function(req, res, next) {

    var url = "mongodb://localhost:27017/chat2";

    MongoClient.connect(url, function(err, db) {
        if (err) throw err;
        db.createCollection("customers", function(err, res) {
            if (err) throw err;
            console.log("Collection created!");
            db.close();
        });
    });
});

module.exports = router;
