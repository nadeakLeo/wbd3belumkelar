var express = require('express');
var router = express.Router();
var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/ojek_chat';

var insertChat = function(db, callback) {
    db.collection('chat').insertOne( {
        "from" : 1,
        "to" : 2,
        "dateTime" : Date.now(),
        "message" : "test 1 2 3..."
    }, function(err, result) {
        assert.equal(err, null);
        console.log("Inserted a chat to history.");
        callback();
    });
};

/* GET CHAT */
router.get('/', function(req, res, next) {

    console.log('test!');

    res.send("ting!");

    // AMBIL CHAT DARI MONGODB
    MongoClient.connect(url, function(err, db) {
        assert.equal(null, err);
        insertChat(db, function() {
            db.close();
        });
    });
});

/* POST CHAT */
router.post('/', function(req, res, next) {

    // MENAMBAHKAN DATA CHAT KE DATABASE MONGODB
    res.send('<p>sukses!</p>');
});

module.exports = router;