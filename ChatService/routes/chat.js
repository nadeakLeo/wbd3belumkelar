var express = require('express');
var router = express.Router();
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var link = 'mongodb://localhost:27017/ojek_chat';

/* GET CHAT */
router.get('/', function(req, res, next) {

    console.log('test!');


});

/* POST CHAT */
router.post('/', function(req, res, next) {

    console.log("request POST chat");

    var idFrom = 100;
    var idTo = 200;
    var message = "hahaha";
    var error = 0;

    var insertChat = function(db, callback) {
        db.collection('chat').insertOne( {
            "from" : idFrom,
            "to" : idTo,
            "dateTime" : Date.now(),
            "message" : message
        }, function(err, result) {
            assert.equal(err, null);
            error = 1;
            //console.log("Inserted a chat to history.");
            callback();
        });
    };

    // AMBIL CHAT DARI MONGODB
    MongoClient.connect(link, function(err, db) {
        assert.equal(null, err);
        insertChat(db, function() {
            db.close();
        });
    });

    if (error !== 1) res.send("Chat inserted!");

    // MENAMBAHKAN DATA CHAT KE DATABASE MONGODB
});

module.exports = router;